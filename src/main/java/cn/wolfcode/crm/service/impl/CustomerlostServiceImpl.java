package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customerlost;
import cn.wolfcode.crm.mapper.CustomerlostMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerlostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */
@Service
public class CustomerlostServiceImpl implements ICustomerlostService{
    @Autowired
    private CustomerlostMapper customerlostMapper;


    //删除
    public int deleteByPrimaryKey(Long id) {
        return customerlostMapper.deleteByPrimaryKey(id);
    }

    //添加
    public int insert(Customerlost record) {
       // 设置时间
        record.setLostdate(new Date());
        return customerlostMapper.insert(record);
    }

    //查询
    public Customerlost selectByPrimaryKey(Long id) {
        return customerlostMapper.selectByPrimaryKey(id);
    }

    //查询所有
    public List<Customerlost> selectAll() {
        return customerlostMapper.selectAll();
    }

    //修改
    public int updateByPrimaryKey(Customerlost record) {
        return customerlostMapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject qo) {
        int totalCount = customerlostMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Customerlost> data = customerlostMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }
}
