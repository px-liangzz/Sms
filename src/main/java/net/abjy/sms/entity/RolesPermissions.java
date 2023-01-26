package net.abjy.sms.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolesPermissions implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @备注:
     * @字段:id BIGINT(19)
     */
    private Long id;


    /**
     * @备注:创建时间
     * @字段:create_time DATETIME(19)
     */
    private java.util.Date createTime;


    /**
     * @备注:更新时间
     * @字段:update_time DATETIME(19)
     */
    private java.util.Date updateTime;

    /**
     * @备注:角色id
     * @字段:roles_id BIGINT(19)
     */
    private Long rolesId;


    /**
     * @备注:权限id
     * @字段:permissions_id BIGINT(19)
     */
    private Long permissionsId;


    /**
     * @备注:角色名称
     * @字段:roles_name VARCHAR(100)
     */
    private String rolesName;


    /**
     * @备注:api接口
     * @字段:url VARCHAR(512)
     */
    private String url;


}