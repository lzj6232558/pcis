package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.BadTable;
import cn.wolfcode.crm.query.BadTableQueryObject;

import java.util.List;

public interface BadTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BadTable record);

    BadTable selectByPrimaryKey(Long id);

    List<BadTable> selectAll();

    int updateByPrimaryKey(BadTable record);

    /**
     * 高级查询数据总数
     * @param qo
     * @return
     */
    int queryCount(BadTableQueryObject qo);

    /**
     * 高级查询总数据
     * @param qo
     * @return
     */
    List<BadTable> queryList(BadTableQueryObject qo);

    /**
     * 根据事故表id,查询赔偿单:
     * @param id
     * @return
     */
    List<BadTable> getBadListById(Long id);
}