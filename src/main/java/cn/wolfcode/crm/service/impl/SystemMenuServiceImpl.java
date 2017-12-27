package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.SystemMenu;
import cn.wolfcode.crm.mapper.SystemMenuMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemMenuService;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/11/27
 */
@Service
@SuppressWarnings("all")
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        systemMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(SystemMenu record) {
        systemMenuMapper.insert(record);
    }

    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(SystemMenu record) {
        systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = systemMenuMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<SystemMenu> data = systemMenuMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    @Override
    public void deleteChildByParentId(Long parentId) {
        systemMenuMapper.deleteChildByParentId(parentId);
    }

    @Override
    public List<Map<String, Object>> selectMenuByParentId(String parentSn) {
        if (!StringUtils.isEmpty(parentSn)) {
            return systemMenuMapper.selectMenuByParentId(parentSn);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> selectParentMenu() {
        List<Map<String, Object>> list = systemMenuMapper.selectParentMenu();
        for (Map<String, Object> map : list) {
            map.put("state","closed");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectChildByParentId(Long parentId) {
        List<Map<String, Object>> list=systemMenuMapper.selectChildByParentId(parentId);
        return list;
    }

    @Override
    public List<SystemMenu> getMenu() {
        return systemMenuMapper.getMenu();
    }

    @Override
    public List<SystemMenu> getChildrenMenu(Long parent_id) {
        return systemMenuMapper.getChildrenMenu(parent_id);
    }
}