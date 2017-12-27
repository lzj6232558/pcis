package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SalaryItem {
    private Long id;            //工资明细表id
    private String hire;        //雇佣方式
    private String pay;         //计薪方式
    private String tax;         //计税方式
    private Long postsalary;    //岗位薪资
    private Boolean ifpay;      //是否缴纳基金
    private String programme;   //缴纳方案
    private Long salaryId;      //总薪资
}