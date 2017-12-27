package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
@ToString
public class Customertransfer {
    private Long id;

    private Customer customer;  // 客户

    private Employee transuser;   //  操作人

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transdate;

    private Employee oldseller;

    private Employee newseller; // 新市场专员

    private String transreason;  // 移交原因

}