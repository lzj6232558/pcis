package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * created by king on 2017/12/22
 */
@Getter@Setter
public class DictionaryItemQueryObject  extends QueryObject{
    //前台页面穿过来的sn-->  通过字典目录的sn 查询字典目录的id,然后再根据id曲字典明细中查询对应的信息
    private String sn;
}
