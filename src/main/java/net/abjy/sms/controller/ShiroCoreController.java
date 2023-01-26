package net.abjy.sms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/9/5.
 */
@RestController
public class ShiroCoreController {
    @GetMapping("/loginUnAuth")
    public String loginUnAuth() {
        return "{\n" +
                "    \"codeEnum\": \"OVERTIME\",\n" +
                "    \"code\": 0,\n" +
                "    \"data\": null,\n" +
                "    \"msg\": \"未登陆/登陆超时\",\n" +
                "    \"success\": false\n" +
                "}";
    }
    @GetMapping("/authorUnAuth")
    public String authorUnAuth() {
        return "{\n" +
                "    \"codeEnum\": \"ERR_PERMISSIONS\",\n" +
                "    \"code\": -2,\n" +
                "    \"data\": null,\n" +
                "    \"msg\": \"无此权限\",\n" +
                "    \"success\": false\n" +
                "}";
    }
}

