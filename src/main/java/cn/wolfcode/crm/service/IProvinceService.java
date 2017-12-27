package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Province;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface IProvinceService {
    void deleteByPrimaryKey(Long id);

    void insert(Province record);

    Province selectByPrimaryKey(Long id);

    List<Province> selectAll();

    void updateByPrimaryKey(Province record);

    /**
     * 查询所有的省份
     * @return
     */
    List<Province> selectAllProvince();

    /**
     * 根据省份的id区查询城市
     * @param provinceId
     * @return
     */
    List<Province> selectAllCity(Long provinceId);

    /**
     * 查询热门城市
     * @return
     */
    List<Province> selectHotCity();
}
