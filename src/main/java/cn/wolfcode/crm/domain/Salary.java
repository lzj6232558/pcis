package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class Salary {

    private Long id;            //id
    private Employee employeeId;    //员工
    private Department departmentId;  //部门
    private String tel;         //电话
    private String email;       //邮箱
    private Long baseSalary;    //基本工资
    private Long bonus;         //奖金
    //前台往后台:日期格式化;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //后台往前台:日期格式化;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date month;         //日期
    private Long totalSalary;   //总薪资
}