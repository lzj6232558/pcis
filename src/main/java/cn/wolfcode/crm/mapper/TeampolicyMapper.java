package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Teampolicy;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeampolicyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teampolicy record);

    Teampolicy selectByPrimaryKey(Long id);

    List<Teampolicy> selectAll();

    int updateByPrimaryKey(Teampolicy record);

    int queryForCount(QueryObject qo);

    List<Teampolicy> queryForList(QueryObject qo);

    // 维护中间表关系
    void Relation(@Param("customId") Long customId,@Param("pid") Long pid);
}