package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadTableQueryObject extends QueryObject {
    private String policySn;//保单号
    private String employeeName;//处理员工
}
