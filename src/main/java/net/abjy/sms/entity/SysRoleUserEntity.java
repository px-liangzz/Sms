package net.abjy.sms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ��������������
 * </p>
 *
 * @author liangzz
 * @since 2023-01-16
 */
@TableName("SYS_ROLE_USER")
@ApiModel(value = "SysRoleUserEntity对象", description = "��������������")
public class SysRoleUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("��������ID")
    @TableField("SYS_USER_ID")
    private BigDecimal sysUserId;

    @ApiModelProperty("��������ID")
    @TableField("SYS_ROLE_ID")
    private BigDecimal sysRoleId;


    public BigDecimal getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(BigDecimal sysUserId) {
        this.sysUserId = sysUserId;
    }

    public BigDecimal getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(BigDecimal sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @Override
    public String toString() {
        return "SysRoleUserEntity{" +
        "sysUserId=" + sysUserId +
        ", sysRoleId=" + sysRoleId +
        "}";
    }
}
