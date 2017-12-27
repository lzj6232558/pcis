package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.BadTable;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.BadTableQueryObject;

import java.util.List;

public interface IBadTableService {
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
    void insert(BadTable record);

    /**
     * 查询
     * @param id
     * @return
     */
    BadTable selectByPrimaryKey(Long id);

    /**
     * 查询所有:
     * @return
     */
    List<BadTable> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    void updateByPrimaryKey(BadTable record);

    /**
     * 高级查询
     * @param qo
     * @return
     */
    PageResult query(BadTableQueryObject qo);

    /**
     * 根据明细id,查询是赔偿单:
     * @param id
     * @return
     */
    List<BadTable> getBadListById(Long id);
}
