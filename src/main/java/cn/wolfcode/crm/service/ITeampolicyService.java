package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Teampolicy;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ITeampolicyService {
    void deleteByPrimaryKey(Long id);

    void insert(Teampolicy record,Long customId);

    Teampolicy selectByPrimaryKey(Long id);

    List<Teampolicy> selectAll();

    void updateByPrimaryKey(Teampolicy record);

    PageResult query(QueryObject qo);

}
