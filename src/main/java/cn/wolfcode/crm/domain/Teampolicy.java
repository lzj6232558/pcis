package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class Teampolicy {
    private Long id;

    private String carplate;  // 车牌

    private String name;  //投保人

    private Employee employee;  //业务员

    private Employee audit;  // 审核人

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  //  后台到前台显示用
    @DateTimeFormat(pattern = "yyyy-MM-dd")     // 前台提交到后台用
    private Date begindate;  //开始日期

}