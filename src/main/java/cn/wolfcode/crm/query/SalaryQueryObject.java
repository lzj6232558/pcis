package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter
@Getter
public class SalaryQueryObject extends QueryObject{
    private String keyword;     //姓名查询
    private Long dept = -1L;        //部门
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;   //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;     //结束时间
}
