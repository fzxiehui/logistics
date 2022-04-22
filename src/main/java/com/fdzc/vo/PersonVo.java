package com.fdzc.vo;


import com.fdzc.pojo.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVo extends Person {


    private String place;

    private String traffic;

    private String plate;
}
