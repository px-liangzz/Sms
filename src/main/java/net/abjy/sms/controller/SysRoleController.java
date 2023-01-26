package net.abjy.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * ���������� 前端控制器
 * </p>
 *
 * @author liangzz
 * @since 2023-01-16
 */
@RestController
@Controller
@RequestMapping("/sysRoleEntity")
public class SysRoleController {


    @Autowired
    private SysPermissionServiceImpl sysPermissionService;

    @GetMapping("/findRoles")
    public String findAllRole() {
        List<SysPermissionEntity> list =   sysPermissionService.findByAdminUserId(62);
        System.out.println(list);
        return list.toString();
    }

}
