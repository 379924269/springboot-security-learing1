package com.dnp.huazai.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动态网关配置信息
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZuulRoute对象", description="动态网关配置信息")
public class ZuulRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "代理ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "匹配请求路径")
    private String path;

    @ApiModelProperty(value = "路由到的服务id")
    private String serviceId;

    @ApiModelProperty(value = "路由到的url")
    private String url;

    @ApiModelProperty(value = "是否有前缀，1：是，0：否")
    private Integer stripPrefix;

    @ApiModelProperty(value = "是否重新请求，1：是，0：否")
    private Integer retryable;

    @ApiModelProperty(value = "是否禁用，1：是，0：否")
    private Integer enabled;

    @ApiModelProperty(value = "描述")
    private String description;


}
