package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Enquiryitem;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@SuppressWarnings("all")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(Product record) {
        mapper.insert(record);

    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll(Long id) {
        return mapper.selectAll(id);
    }

    @Override
    public void updateByPrimaryKey(Product record) {
        mapper.updateByPrimaryKey(record);

    }

    @Override
    public PageResult query(ProductQueryObject qo) {
        int count = mapper.queryCount(qo);
        if (count < 0) {
            return new PageResult(0, Collections.emptyList());
        }
        List<Product> list = mapper.queryList(qo);
        return new PageResult(count, list);
    }

    @Override
    public List<Product> selectProductByItemId(Long id, Long enquiryId) {
        return mapper.selectProductByItemId(id, enquiryId);
    }

    @Override
    public void updateTotalMoney(Product product, Long[] productIds, Long enquiryId) {
        //查询
        if (product.getName().equals("不计免赔")) {

        } else if (product.getName().equals("车辆损失险")) {
            if (product.getTotalMoney() != null) {
                if (product.getSize() > 1) {
                    //6座一下
                    product.setAnnuafee(product.getTotalMoney().
                            multiply(new BigDecimal("0.009")).multiply(new BigDecimal("10000")).add(new BigDecimal(342)));
                } else {
                    product.setAnnuafee(product.getTotalMoney().
                            multiply(new BigDecimal("0.010")).multiply(new BigDecimal("10000")).add(new BigDecimal(342)));
                }
            }
        } else if (product.getName().equals("第三者责任险")) {
            if (product.getTotalMoney().equals(new BigDecimal(10))) {

                product.setAnnuafee(new BigDecimal("962").add(new BigDecimal("144.3")));
            } else if (product.getTotalMoney().equals(new BigDecimal(20))) {
                product.setAnnuafee(new BigDecimal("1191").add(new BigDecimal("144.3")));
            } else if (product.getTotalMoney().equals(new BigDecimal(30))) {
                product.setAnnuafee(new BigDecimal("1346").add(new BigDecimal("201.9")));
            } else if (product.getTotalMoney().equals(new BigDecimal(50))) {
                product.setAnnuafee(new BigDecimal("1615").add(new BigDecimal("242.25")));
            } else if (product.getTotalMoney().equals(new BigDecimal(100))) {
                product.setAnnuafee(new BigDecimal("2103").add(new BigDecimal("315.44")));
            }
        } else if (product.getName().equals("全年抢盗险")) {
            if (product.getSize() > 1) {
                //6座一下
                product.setAnnuafee(product.getTotalMoney().multiply(new BigDecimal("10000")).multiply(new BigDecimal("0.01")));
            } else {
                product.setAnnuafee(product.getTotalMoney().multiply(new BigDecimal("10000")).multiply(new BigDecimal("0.011")));
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("enquiryId", enquiryId);
        int count = mapper.selectByProductIdAndenquiryId(product.getId(), enquiryId);
        if (count > 0) {
            mapper.updateTotalMoney(map);

        } else {
            mapper.insertRelation(map);
        }

    }

    @Override
    public List<Enquiryitem> selectTotalMoney(Long enquiryId) {
        return mapper.selectTotalMoney(enquiryId);
    }

    @Override
    public void updateTotalMoney2(Product product) {
        //查询
        if (product.getName().equals("不计免赔")) {

        } else if (product.getName().equals("车辆损失险")) {
            if (product.getTotalMoney() != null) {
                if (product.getSize() > 1) {
                    //6座一下
                    product.setAnnuafee(product.getTotalMoney().
                            multiply(new BigDecimal("0.009")).multiply(new BigDecimal("10000")).add(new BigDecimal(342)));
                } else {
                    product.setAnnuafee(product.getTotalMoney().
                            multiply(new BigDecimal("0.010")).multiply(new BigDecimal("10000")).add(new BigDecimal(342)));
                }
            }
        } else if (product.getName().equals("第三者责任险")) {
            if (product.getTotalMoney().equals(new BigDecimal(10))) {
                product.setAnnuafee(new BigDecimal("962").add(new BigDecimal("144.3")));
            } else if (product.getTotalMoney().equals(new BigDecimal(20))) {
                product.setAnnuafee(new BigDecimal("1191").add(new BigDecimal("144.3")));
            } else if (product.getTotalMoney().equals(new BigDecimal(30))) {
                product.setAnnuafee(new BigDecimal("1346").add(new BigDecimal("201.9")));
            } else if (product.getTotalMoney().equals(new BigDecimal(50))) {
                product.setAnnuafee(new BigDecimal("1615").add(new BigDecimal("242.25")));
            } else if (product.getTotalMoney().equals(new BigDecimal(100))) {
                product.setAnnuafee(new BigDecimal("2103").add(new BigDecimal("315.44")));
            }
        } else if (product.getName().equals("全年抢盗险")) {
            if (product.getSize() > 1) {
                //6座一下
                product.setAnnuafee(product.getTotalMoney().multiply(new BigDecimal("10000")).multiply(new BigDecimal("0.01")));
            } else {
                product.setAnnuafee(product.getTotalMoney().multiply(new BigDecimal("10000")).multiply(new BigDecimal("0.011")));
            }
        }

        mapper.updateTotalMoney2(product);
    }

    @Override
    public List<Product> selectProductByItemId2(Long id) {
        return mapper.selectProductByItemId2(id);
    }

    @Override
    public void updateTotalMoney(Product product) {

    }

    @Override
    public List<Product> list() {
        return null;
    }

}

