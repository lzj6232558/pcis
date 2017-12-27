package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class PolicyDetail {
    private Long id;

    private Policy policy;

    private Long amount;

    private String remark;

}