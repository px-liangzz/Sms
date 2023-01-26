package net.abjy.sms.controller;


import net.abjy.sms.entity.RolesPermissions;
import net.abjy.sms.entity.SysUserEntity;
import net.abjy.sms.service.RolesPermissionsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/9/5.
 */
public class UserShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserServiceImpl userService;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private RolesPermissionsService rolesPermissionsService;

    /**
     * 角色权限和对应权限添加
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

        if (userName == null || "".equals(userName)) {
            return null;
        }else{
            userName = userName.split("==")[1];
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            try{
                List<RolesPermissions> rolesPermissions = rolesPermissionsService.selectByAuserName(userName);
                //当前用户具有的权限
                List<String> roles = rolesPermissions.stream().map(RolesPermissions::getRolesName).collect(Collectors.toList());
                authorizationInfo.addRoles(roles);
            }catch(Exception e){
                e.printStackTrace();
            }
            return authorizationInfo;
        }
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String userName = authenticationToken.getPrincipal().toString();

        //只允许同一账户单个登录
        Subject subject = SecurityUtils.getSubject();
        Session nowSession = subject.getSession();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        if(sessions != null && sessions.size() > 0) {
            for (Session session : sessions) {
                if (!nowSession.getId().equals(session.getId()) && (session.getTimeout() == 0
                        || userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))))) {
                    sessionDAO.delete(session);
                }
            }
        }

        SysUserEntity user = userService.findByUserName(userName.contains("admin-pc==") ? userName.split("==")[1] : userName);
        if (user == null) {
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        }
    }
}