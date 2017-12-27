package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * created by king on 2017/12/22
 */

@Getter
@Setter
@ToString
public class ProductQueryObject extends QueryObject {
    private String keywords;
    private int smId=-1;
    private int sStatus=-1;
}
