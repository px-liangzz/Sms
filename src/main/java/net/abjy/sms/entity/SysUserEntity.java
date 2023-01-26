package net.abjy.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ����������
 * </p>
 *
 * @author liangzz
 * @since 2023-01-16
 */
@TableName("SYS_USER")
@ApiModel(value = "SysUserEntity对象", description = "����������")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("����")
    @TableId(value = "ID", type = IdType.NONE)
    private BigDecimal id;

    @ApiModelProperty("������")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty("����")
    @TableField("PASSWORD")
    private String password;


    private List<SysRoleEntity> roles;

    public SysUserEntity(String username, String password, List<SysRoleEntity> roles)
    {
        this.username   =   username;
        this.password   =   password;
        this.roles      =   roles;
    }


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleEntity> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "SysUserEntity{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
