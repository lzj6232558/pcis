package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Car;
import cn.wolfcode.crm.mapper.CarMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class CarServiceImpl implements ICarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Car record) {

        carMapper.insert(record);
    }

    @Override
    public Car selectByPrimaryKey(Long id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Car> selectAll() {
        return carMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Car record) {
        carMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = carMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Car> data = carMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

}
