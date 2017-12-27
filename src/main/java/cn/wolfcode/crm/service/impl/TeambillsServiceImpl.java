package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Teambills;
import cn.wolfcode.crm.mapper.TeambillsMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ITeambillsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mm on 2017/12/21.
 */
@Service
public class TeambillsServiceImpl implements ITeambillsService{
    @Autowired
    private TeambillsMapper teambillsMapper;


    //删除
    public int deleteByPrimaryKey(Long id) {
        return teambillsMapper.deleteByPrimaryKey(id);
    }

    //添加
    public int insert(Teambills record) {
       // 设置时间
        Employee employee= (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputuser(employee);
        record.setInputdate(new Date());
        // 设置随机数
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        record.setSn(s);
        return teambillsMapper.insert(record);
    }

    //查询
    public Teambills selectByPrimaryKey(Long id) {
        return teambillsMapper.selectByPrimaryKey(id);
    }

    //查询所有
    public List<Teambills> selectAll() {
        return teambillsMapper.selectAll();
    }

    //修改
    public int updateByPrimaryKey(Teambills record) {
        return teambillsMapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject qo) {
        int totalCount = teambillsMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Teambills> data = teambillsMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    @Override
    public List<Long> selectCustomer() {
        return teambillsMapper.selectCustomer();
    }
}
