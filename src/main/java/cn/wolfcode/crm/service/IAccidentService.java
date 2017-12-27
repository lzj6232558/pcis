package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Accident;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentQueryObject;

import java.util.List;

public interface IAccidentService {
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
    void insert(Accident record);

    /**
     * 查询
     * @param id
     * @return
     */
    Accident selectByPrimaryKey(Long id);

    /**
     * 查询所有:
     * @return
     */
    List<Accident> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    void updateByPrimaryKey(Accident record);

    /**
     * 高价查询
     * @param qo
     * @return
     */
    PageResult query(AccidentQueryObject qo);
}
