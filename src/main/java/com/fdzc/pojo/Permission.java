package com.fdzc.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

@ApiModel("权限")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_permission")
public class Permission implements Serializable {

  private Integer id;
  private String permissionName;
  private String resourceUrl;


}
