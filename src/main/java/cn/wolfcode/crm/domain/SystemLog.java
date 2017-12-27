package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SystemLog {
    private Long id;

    private Employee opuser;

    private Date opTime;

    private String opIp;

    private String function;

    private String params;


}