package com.fdzc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel("行程表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_trip")
public class Trip implements Serializable {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("地点")
    private String place;
    @ApiModelProperty("同行方式")
    private String traffic;

    @ApiModelProperty("车牌")
    private String plate;

    @ApiModelProperty("关联id")
    private Integer personId;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    @TableField(value = "deleted")
    private int deleted;

}
