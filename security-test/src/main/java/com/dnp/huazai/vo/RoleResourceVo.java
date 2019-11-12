package com.dnp.huazai.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author huazai
 * @description 角色资源信息
 * @date 2019/9/16
 */
@Data
public class RoleResourceVo implements Serializable {
    @XmlAttribute
    @ApiModelProperty(value = "资源路径", dataType = "String")
    private String url;

    @XmlAttribute
    @ApiModelProperty(value = "请求方法：get、post......", dataType = "String")
    private String method;

    @XmlAttribute
    @ApiModelProperty(value = "角色名称", dataType = "String")
    private String roleName;
}
