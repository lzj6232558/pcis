package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customertransfer;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CustomertransferMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomertransferService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */
@Service
public class CustomertransferServiceImpl implements ICustomertransferService{
    @Autowired
    private CustomertransferMapper customertransferMapper;


    //删除
    public int deleteByPrimaryKey(Long id) {
        return customertransferMapper.deleteByPrimaryKey(id);
    }

    //添加
    public int insert(Customertransfer record) {
        // 新增操作
        // 添加操作人
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        record.setTransuser(employee);
        //添加 操作时间
        record.setTransdate(new Date());
        //
        return customertransferMapper.insert(record);
    }

    //查询
    public Customertransfer selectByPrimaryKey(Long id) {
        return customertransferMapper.selectByPrimaryKey(id);
    }

    //查询所有
    public List<Customertransfer> selectAll() {
       // 其实移交客户
        return customertransferMapper.selectAll();
    }

    //修改
    public int updateByPrimaryKey(Customertransfer record) {
        return customertransferMapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject qo) {
        int totalCount = customertransferMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Customertransfer> data = customertransferMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }
}
