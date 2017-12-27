package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Teampolicy;
import cn.wolfcode.crm.mapper.TeampolicyMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ITeampolicyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeampolicyServiceImpl implements ITeampolicyService {
    @Autowired
    private TeampolicyMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(Teampolicy record,Long customId) {
       record.setBegindate(new Date());
       record.setEmployee((Employee) SecurityUtils.getSubject().getPrincipal());
       // 维护中间表的关系   保存明细的时候添加客户和保单的关系
        mapper.insert(record); // 必须要先保存   之后才能得到它返回的主键ID
        mapper.Relation(customId,record.getId());
    }

    @Override
    public Teampolicy selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Teampolicy> selectAll() {
        return mapper.selectAll();
    }


    @Override
    public void updateByPrimaryKey(Teampolicy record) {
        mapper.updateByPrimaryKey(record);

    }


    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Teampolicy> data = mapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

}
