package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Salary;
import cn.wolfcode.crm.domain.SalaryItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SalaryQueryObject;
import jxl.Sheet;
import jxl.write.WritableSheet;

import java.util.List;

public interface ISalaryService {
    void deleteByPrimaryKey(Long id);

    void insert(Salary record);

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    void updateByPrimaryKey(Salary record);

    PageResult query(QueryObject qo);

    /**
     * 根据工资表查询明细:
     * @param id
     * @return
     */
    SalaryItem getItemBySalaryId(Long id);

    /**
     * 工资表导出
     * @param qo
     */
    WritableSheet exportXls(WritableSheet sheet,SalaryQueryObject qo);

    /**
     * 下载模板:
     * @param sheet
     * @return
     */
    WritableSheet dowloadTemplate(WritableSheet sheet);

    /**
     * 导入工资表
     * @param sheet
     */
    void improtXls(Sheet sheet);
}
