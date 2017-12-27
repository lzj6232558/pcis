package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customerfail;
import cn.wolfcode.crm.mapper.CustomerfailMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerfailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */
@Service
public class CustomerfailServiceImpl implements ICustomerfailService{
    @Autowired
    private CustomerfailMapper customerfailMapper;


    //删除
    public int deleteByPrimaryKey(Long id) {
        return customerfailMapper.deleteByPrimaryKey(id);
    }

    //添加
    public int insert(Customerfail record) {
       // 设置时间
        record.setFaildate(new Date());
        return customerfailMapper.insert(record);
    }

    //查询
    public Customerfail selectByPrimaryKey(Long id) {
        return customerfailMapper.selectByPrimaryKey(id);
    }

    //查询所有
    public List<Customerfail> selectAll() {
        return customerfailMapper.selectAll();
    }

    //修改
    public int updateByPrimaryKey(Customerfail record) {
        return customerfailMapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject qo) {
        int totalCount = customerfailMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Customerfail> data = customerfailMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }
}
