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
import java.util.List;

@ApiModel("用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User implements Serializable {
    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("加密盐值")
    private String salt;
    @ApiModelProperty("jwt盐值")
    private String jwtSecret;
    @ApiModelProperty("激活状态")
    private Integer status;
    @ApiModelProperty("激活状态")
    private String realname;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    private int deleted;

}
