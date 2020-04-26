package com.dnp.huazai.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", position = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登陆名", position = 2)
    private String loginName;

    @ApiModelProperty(value = "用户名", position = 3)
    private String username;

    @ApiModelProperty(value = "密码", position = 4)
    private String password;

    @ApiModelProperty(value = "密码加密盐", position = 5)
    private String salt;

    @ApiModelProperty(value = "性别", position = 6)
    private Integer sex;

    @ApiModelProperty(value = "年龄", position = 7)
    private Integer age;

    @ApiModelProperty(value = "手机号", position = 8)
    private String phone;

    @ApiModelProperty(value = "用户类别", position = 9)
    private Integer userType;

    @ApiModelProperty(value = "用户状态" , position = 10)
    private Integer status;

    @ApiModelProperty(value = "所属机构", position = 11)
    private Integer organizationId;

    @ApiModelProperty(value = "创建时间", position = 12)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "登录错误次数",position = 13)
    private Integer loginErrorCount;

    @ApiModelProperty(value = "锁定，0：为锁定，1：锁定", position = 14)
    private Integer locked;

    @ApiModelProperty(value = "锁定时间，时间毫秒数", position = 15)
    private Long lockedTime;

}
