package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class Accident {
    private Long id;                //报案表id
    private String reporterName;    //报案人
    private String reporterSex;     //报案人性别
    private String reporterIphon;   //报案人电话
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reporterTime;      //报案时间
    private String reporterPlace;   //事发地点
    private String policySn;        //保单号
    private String plateNumber;     //车牌号
    private Employee employeeId;    //员工客服

    private String name;             //车主;
}