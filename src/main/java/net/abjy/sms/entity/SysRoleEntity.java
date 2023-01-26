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
@TableName("SYS_ROLE")
@ApiModel(value = "SysRoleEntity对象", description = "����������")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("����")
    @TableId(value = "ID", type = IdType.NONE)
    private BigDecimal id;

    @ApiModelProperty("����")
    @TableField("NAME")
    private String name;

    private List<SysPermissionEntity> permissions;

    public List<SysPermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermissionEntity> permissions) {
        this.permissions = permissions;
    }

    @TableField("DEPT_ID")
    private String DEPT_ID;

    public String getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(String DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "SysRoleEntity{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
