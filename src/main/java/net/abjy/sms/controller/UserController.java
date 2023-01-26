package net.abjy.sms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/5.
 */
@RestController
@RequestMapping("/core/user")
public class UserController {
    @Autowired
//    private SysUserServiceImpl userService;

    /**
     * 登录
     * @param
     * @return
     */
    @GetMapping("/login")
    public String login(String userName, String password) {
        System.out.println("登录" + userName);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin-pc==" + userName, password);
        subject.login(token);

        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        System.out.println("登录成功 -> " + sessionId);

        return userName + "[" + sessionId + "]";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "退出登录成功";
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @GetMapping("/findUser")
    public String findUser() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();
        if (null != collection && !collection.isEmpty()) {
            String userName = (String) collection.iterator().next();
            System.out.println("获取当前登录用户" + userName);
//            return userService.findByUserName(userName).toString();
        }
        return "{\n" +
                "    \"codeEnum\": \"OVERTIME\",\n" +
                "    \"code\": 0,\n" +
                "    \"data\": null,\n" +
                "    \"msg\": \"未登陆/登陆超时\",\n" +
                "    \"success\": false\n" +
                "}";
    }
}