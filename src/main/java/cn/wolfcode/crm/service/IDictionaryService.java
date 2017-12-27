package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/12/17
 */
public interface IDictionaryService {
    void deleteByPrimaryKey(Long id);

    void insert(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    void updateByPrimaryKey(Dictionary record);
    //分页的条件
    PageResult query(QueryObject qo);

    /**
     * 查询数据库中的字典目录回显到字典明细的下拉框中
     * @return
     */
    List<Map<String,Object>> selectDictionary();
}
