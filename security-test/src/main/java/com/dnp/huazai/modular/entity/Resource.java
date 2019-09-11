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
 * 资源
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Resource对象", description="资源")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源路径")
    private String url;

    @ApiModelProperty(value = "打开方式 ajax,iframe")
    private String openMode;

    @ApiModelProperty(value = "资源介绍")
    private String description;

    @ApiModelProperty(value = "资源图标")
    private String icon;

    @ApiModelProperty(value = "父级资源id")
    private Long pid;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "打开状态")
    private Integer opened;

    @ApiModelProperty(value = "资源类别")
    private Integer resourceType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "请求方法：get、post......")
    private String method;


}
