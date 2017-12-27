package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Teambills {
    private Long id;
    private String sn;  // 单号唯一标识

    private Product product;  //  商品  险种


    private Safetymechanism safety;  // 机构

    private Employee inputuser;  // 录入人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date inputdate; // 录入时间

    private String remark;  // 备注

    private Customer customer;  // 客户姓名

    private List<Policy> policies = new ArrayList<>();   // 一个客户来填写团单
}