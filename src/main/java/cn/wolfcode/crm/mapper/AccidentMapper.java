package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Accident;
import cn.wolfcode.crm.query.AccidentQueryObject;

import java.util.List;

public interface AccidentMapper {
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
    int insert(Accident record);

    /**
     * 查询
     * @param id
     * @return
     */
    Accident selectByPrimaryKey(Long id);

    /**
     * 查询
     * @return
     */
    List<Accident> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Accident record);

    /**
     * 高级查询:总条数
     * @param qo
     * @return
     */
    int queryCount(AccidentQueryObject qo);

    /**
     * 高级查询:总数据
     * @param qo
     * @return
     */
    List<Accident> queryList(AccidentQueryObject qo);

    /**
     * 根据报案单查询车主
     * @param accidentId
     * @return
     */
    Accident getNameByAccidentId(Long accidentId);
}
