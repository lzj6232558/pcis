package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.AccidentItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentItemQueryObject;

import java.util.List;

public interface IAccidentItemService {
    /**
     * 删除
     * @param id
     * @return
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    void insert(AccidentItem record);

    /**
     * 查询
     * @param id
     * @return
     */
    AccidentItem selectByPrimaryKey(Long id);

    /**
     * 查询所有:
     * @return
     */
    List<AccidentItem> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    void updateByPrimaryKey(AccidentItem record);

    /**
     * 高级查询
     * @param qo
     * @return
     */
    PageResult query(AccidentItemQueryObject qo);
}
