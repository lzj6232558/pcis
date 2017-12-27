package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter
@Getter
public class AccidentQueryObject extends QueryObject{
    private String keyword;     //报案人姓名,电话,保单号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;   //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;     //结束时间
}
