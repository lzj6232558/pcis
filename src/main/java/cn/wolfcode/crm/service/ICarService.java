package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Car;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface ICarService {
    void deleteByPrimaryKey(Long id);

    void insert(Car record);

    Car selectByPrimaryKey(Long id);

    List<Car> selectAll();

    void updateByPrimaryKey(Car record);
    //分页的条件
    PageResult query(QueryObject qo);

}
