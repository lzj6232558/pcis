package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customerlost;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */

public interface ICustomerlostService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Customerlost record);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Customerlost selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Customerlost> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Customerlost record);



    PageResult query(QueryObject qo);
}
