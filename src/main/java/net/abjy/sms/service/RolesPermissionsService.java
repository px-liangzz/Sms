package net.abjy.sms.service;

import net.abjy.sms.entity.RolesPermissions;

import java.util.List;

public interface RolesPermissionsService {
    List<RolesPermissions> selectList();
    List<RolesPermissions> selectByAuserName(String auserName);
}
