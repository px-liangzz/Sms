package net.abjy.sms.shiro;

import net.abjy.sms.entity.RolesPermissions;
import net.abjy.sms.redis.RedisServiceImpl;
import net.abjy.sms.service.RolesPermissionsService;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomRolesAuthorizationFilter extends AuthorizationFilter {
    //项目名-用于多项目共用redis缓存时区分项目
    private static final String PROJECTNAME = "shiro5";
    private RolesPermissionsService rolesPermissionsService;
    private RedisServiceImpl redisCache;

    public CustomRolesAuthorizationFilter(RolesPermissionsService rolesPermissionsService, RedisServiceImpl redisCache) {
        this.rolesPermissionsService = rolesPermissionsService;
        this.redisCache = redisCache;
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        PrincipalCollection principals = subject.getPrincipals();

        //未登录情况
        if (null == principals) {
            return false;
        }

        String userName = principals.toString();
        System.out.println(userName);
        String userName0 = userName.split("==")[0];

        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        //当前请求URI所需要的角色
        List<String> rolesList = Arrays.asList(rolesArray);

        String userName1 = "";
        //redis缓存中当前登录用户的角色(注:更改用户角色时需要更新此缓存)
        Object cache = redisCache.get(PROJECTNAME + "==isAccessAllowed==" + userName);

        if ("admin-pc".equals(userName0)) {
            if (null != cache) {
                Set<String> cache1 = (Set<String>) cache;
                boolean disjoint = Collections.disjoint(cache1, rolesList);
                return !disjoint;
            }
            userName1 = userName.split("==")[1];
        }

        List<RolesPermissions> rolesPermissions;
        if ("admin-pc".equals(userName0)) {
            rolesPermissions = rolesPermissionsService.selectByAuserName(userName1);
        } else {
            return true;
        }

        //当前用户具有的权限
        Set<String> roles = rolesPermissions.stream().map(RolesPermissions::getRolesName).collect(Collectors.toSet());
        System.out.println(roles.toString());
        //往redis缓存中存储当前登录用户的角色(注:更改用户角色时需要更新此缓存)
        redisCache.set(PROJECTNAME + "==isAccessAllowed==" + userName, roles, 86400); //24小时过期

        boolean disjoint = Collections.disjoint(roles, rolesList);

        return !disjoint;

    }

}