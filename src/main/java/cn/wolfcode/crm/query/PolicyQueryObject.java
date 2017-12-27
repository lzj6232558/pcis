package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * created by king on 2017/12/23
 */
@Getter
@Setter
@ToString
public class PolicyQueryObject extends QueryObject {
    private int state=-1;
    private String applicant;//投保人
    private String policyNo;//保单编号
}
