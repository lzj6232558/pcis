package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentQueryObject extends QueryObject {
    private int state = 2;
    private String applicant;//投保人
    private String policyNo;//保单编号
}
