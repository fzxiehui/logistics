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

@ApiModel("用户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_person")
public class Person implements Serializable {

    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户电话")
    private String phone;

    @ApiModelProperty("用户类型")
    private Integer type;

    @ApiModelProperty("是否高危")
    private Integer danger;

    @ApiModelProperty("核酸")
    private Integer nucleic;

    @ApiModelProperty("疫苗")
    private Integer vaccine;

    @ApiModelProperty("审核状态")
    @TableField(value = "`check`")
    private Integer check;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    @TableField(value = "deleted")
    private int deleted;


}
