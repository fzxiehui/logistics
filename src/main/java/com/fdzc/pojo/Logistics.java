package com.fdzc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel("物流实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_logistics")
public class Logistics implements Serializable {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单")
    @TableField(value = "`order`")
    private String order;

    @ApiModelProperty("用户姓名")
    private String owner;

    @ApiModelProperty("用户手机号")
    @JsonProperty("owner_phone")
    private String ownerPhone;

    @ApiModelProperty("地理位置")
    private String position;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    @TableField(value = "deleted")
    private int deleted;

}
