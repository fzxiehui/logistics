package com.fdzc.vo;


import com.fdzc.pojo.Person;
import com.fdzc.pojo.Trip;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripVo extends Person {

    private List<Trip> info;

}
