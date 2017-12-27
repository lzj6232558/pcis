package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Province;
import java.util.List;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    Province selectByPrimaryKey(Long id);

    List<Province> selectAll();

    int updateByPrimaryKey(Province record);

    List<Province> selectAllProvince();

    List<Province> selectAllCity(Long provinceId);

    List<Province> selectHotCity();
}