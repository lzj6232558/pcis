package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Enquiryitem;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ProductQueryObject;

import java.util.List;

public interface IProductService {
    void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll(Long id);

    void updateByPrimaryKey(Product record);

    PageResult query(ProductQueryObject qo);

    /**
     * 根据机构id查询id
     * @param id
     * @return
     */
    List<Product> selectProductByItemId(Long id,Long enquiryId);

    void updateTotalMoney(Product product,Long[] productIds,Long enquiryId);

    /**
     * 根据询价单的id,查询出商品的保额和费用
     * @param enquiryId
     * @return
     */
    List<Enquiryitem> selectTotalMoney(Long enquiryId);

    void updateTotalMoney2(Product product);

    List<Product> selectProductByItemId2(Long id);
    void updateTotalMoney(Product product);

    List<Product> list();
}
