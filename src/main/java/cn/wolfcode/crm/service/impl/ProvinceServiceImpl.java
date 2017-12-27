package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Province;
import cn.wolfcode.crm.mapper.ProvinceMapper;
import cn.wolfcode.crm.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class ProvinceServiceImpl implements IProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;


    @Override
    public void deleteByPrimaryKey(Long id) {

    }

    @Override
    public void insert(Province record) {

        provinceMapper.insert(record);
    }

    @Override
    public Province selectByPrimaryKey(Long id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Province> selectAll() {
        return provinceMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Province record) {
        provinceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Province> selectAllProvince() {
        return provinceMapper.selectAllProvince();
    }

    @Override
    public List<Province> selectAllCity(Long provinceId) {
        return provinceMapper.selectAllCity(provinceId);
    }

    @Override
    public List<Province> selectHotCity() {
        return provinceMapper.selectHotCity();
    }


}
