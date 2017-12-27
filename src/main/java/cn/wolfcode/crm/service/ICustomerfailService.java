package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customerfail;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */

public interface ICustomerfailService {
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
    int insert(Customerfail record);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Customerfail selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Customerfail> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Customerfail record);



    PageResult query(QueryObject qo);
}
