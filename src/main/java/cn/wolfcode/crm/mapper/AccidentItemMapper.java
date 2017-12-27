package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.AccidentItem;
import cn.wolfcode.crm.query.AccidentItemQueryObject;

import java.util.List;

public interface AccidentItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccidentItem record);

    AccidentItem selectByPrimaryKey(Long id);

    List<AccidentItem> selectAll();

    int updateByPrimaryKey(AccidentItem record);

    /**
     * 高级查询
     * @param qo
     * @return
     */
    int queryCount(AccidentItemQueryObject qo);

    /**
     * 高级查询:
     * @param qo
     * @return
     */
    List<AccidentItem> queryList(AccidentItemQueryObject qo);
}