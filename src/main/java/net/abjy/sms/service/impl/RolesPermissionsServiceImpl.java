package net.abjy.sms.service.impl;


import net.abjy.sms.entity.RolesPermissions;
import net.abjy.sms.service.RolesPermissionsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RolesPermissionsServiceImpl implements RolesPermissionsService {
    @Override
    public List<RolesPermissions> selectList() {
        List<RolesPermissions> rolesPermissionsList = new LinkedList<>();

        RolesPermissions rolesPermissions = new RolesPermissions();
        rolesPermissions.setId(1L);
        rolesPermissions.setPermissionsId(1L);
        rolesPermissions.setRolesId(1L);
        rolesPermissions.setRolesName("超级管理员");
        rolesPermissions.setUrl("/index.html");
        rolesPermissionsList.add(rolesPermissions);

        RolesPermissions rolesPermissions2 = new RolesPermissions();
        rolesPermissions2.setId(2L);
        rolesPermissions2.setPermissionsId(1L);
        rolesPermissions2.setRolesId(2L);
        rolesPermissions2.setRolesName("管理员");
        rolesPermissions2.setUrl("/test.html");
        rolesPermissionsList.add(rolesPermissions2);

        RolesPermissions rolesPermissions3 = new RolesPermissions();
        rolesPermissions3.setId(3L);
        rolesPermissions3.setPermissionsId(1L);
        rolesPermissions3.setRolesId(2L);
        rolesPermissions3.setRolesName("超级管理员");
        rolesPermissions3.setUrl("/test.html");
        rolesPermissionsList.add(rolesPermissions3);

        RolesPermissions rolesPermissions4 = new RolesPermissions();
        rolesPermissions4.setId(4L);
        rolesPermissions4.setPermissionsId(3L);
        rolesPermissions4.setRolesId(1L);
        rolesPermissions4.setRolesName("超级管理员");
        rolesPermissions4.setUrl("/core/user/findUser");
        rolesPermissionsList.add(rolesPermissions4);

        return rolesPermissionsList;
    }

    @Override
    public List<RolesPermissions> selectByAuserName(String auserName) {
        List<RolesPermissions> rolesPermissionsList = new LinkedList<>();
        RolesPermissions rolesPermissions = new RolesPermissions();
        rolesPermissions.setId(1L);
        rolesPermissions.setPermissionsId(1L);
        rolesPermissions.setRolesId(1L);
        rolesPermissions.setRolesName("运营管理员");
        rolesPermissions.setUrl("/index.html");
        rolesPermissionsList.add(rolesPermissions);
        return rolesPermissionsList;
    }
}