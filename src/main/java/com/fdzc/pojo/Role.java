package com.fdzc.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@ApiModel("角色")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_role")
public class Role implements Serializable {

  private Integer id;
  private String roleName;

}
