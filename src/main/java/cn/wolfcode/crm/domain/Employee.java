package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //后台传递到前台的格式化
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;
    //状态
    private boolean state=true;

    private Department dept;

    private boolean admin;
    //角色
    List<Role> roles=new ArrayList<>();

}