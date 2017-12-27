package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Salary;
import cn.wolfcode.crm.domain.SalaryItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SalaryMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Salary record);

    /**
     * 查询
     * @param id
     * @return
     */
    Salary selectByPrimaryKey(Long id);

    /**
     * 查询
     * @return
     */
    List<Salary> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Salary record);

    /**
     * 高级查询:总条数
     * @param qo
     * @return
     */
    int queryCount(QueryObject qo);

    /**
     * 高级查询:总数据
     * @param qo
     * @return
     */
    List<Salary> queryList(QueryObject qo);

    /**
     * 根据工资表查询名字:
     * @param id
     * @return
     */
    SalaryItem getItemBySalaryId(Long id);

    /**
     * 保存工资明细
     * @param salaryItem
     */
    void insertSalaryItem(SalaryItem salaryItem);
}