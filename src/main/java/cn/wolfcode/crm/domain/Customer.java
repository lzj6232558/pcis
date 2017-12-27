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
public class Customer {
    private Long id;

    private String idno;  // 身份证

    private String name;  //姓名

    private Integer age;

    private boolean gender; // 性别

    private String tel;

    private String email;

    private String address; // 住址

    private String qq;

    private String job;  // 工作

    private String salary;  // 收入

    private String source;   //来源

    private Employee changeuser;  // 改变的人的 id  关联的是员工表

    private Employee inputuser;  // 录入人

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  //  后台到前台显示用
    @DateTimeFormat(pattern = "yyyy-MM-dd")     // 前台提交到后台用
    private Date inputdate;  // 录入时间

    private Integer status; // 状态
    // -2 流失
    // -1 开发失败
    //  0 资源池客户
    //  1 潜在客户
    //  2 正式客户

}