package net.abjy.sms.controller;

import net.abjy.sms.shiro.FilterChainDefinitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/test")
public class TestController {
    @Autowired
    private FilterChainDefinitions filterChainDefinitions;


    @GetMapping("/test")
    public void test() {
        //设置用户权限
        //...
        //刷新权限
        filterChainDefinitions.reloadFilterChains();
    }
}