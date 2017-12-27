package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 潜在客户开发计划
 */
@Getter
@Setter
@ToString
public class Plan {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    private String subject;
    //计划详情
    private String detail;
    //实施方式
    private String type;
    //执行结果
    private Integer result;
    //备注
    private String remark;
    //潜在客户
    private Customer customer;
    //计划制定人
    private Employee inputuser;

    private Boolean status;

}