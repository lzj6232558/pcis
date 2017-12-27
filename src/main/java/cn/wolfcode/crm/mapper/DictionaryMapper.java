package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);
    int queryForCount(QueryObject qo);

    List<Dictionary> queryForList(QueryObject qo);

    /**
     * 查询数据库中的字典目录回显到字典明细的下拉框中
     * @return
     */
    List<Map<String,Object>> selectDictionary();
}