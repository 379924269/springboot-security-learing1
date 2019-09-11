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
 * 系统日志
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysLog对象", description="系统日志")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登陆名")
    private String loginName;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "内容")
    private String optContent;

    @ApiModelProperty(value = "客户端ip")
    private String clientIp;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
