package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Salary;
import cn.wolfcode.crm.domain.SalaryItem;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.SalaryMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SalaryQueryObject;
import cn.wolfcode.crm.service.ISalaryService;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private SalaryMapper mapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(Salary record) {
        mapper.insert(record);

    }

    @Override
    public Salary selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Salary> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Salary record) {
        mapper.updateByPrimaryKey(record);

    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = mapper.queryCount(qo);
        if (count < 0) {
            return new PageResult(0, Collections.emptyList());
        }
        List<Salary> list = mapper.queryList(qo);
        return new PageResult(count, list);
    }

    //根据工资表查询明细:
    public SalaryItem getItemBySalaryId(Long id) {
        return mapper.getItemBySalaryId(id);
    }

    //工资表导出
    public WritableSheet exportXls(WritableSheet sheet, SalaryQueryObject qo) {
        //查询工资数据和工资明细:
        List<Salary> salarys = mapper.queryList(qo);

        //开始生成工作溥:
        try {
            //先设置工作溥标题:
            sheet.addCell(new Label(0, 0, "姓名"));
            sheet.addCell(new Label(1, 0, "部门(例:总部)"));
            sheet.addCell(new Label(2, 0, "电话"));
            sheet.addCell(new Label(3, 0, "邮箱"));
            sheet.addCell(new Label(4, 0, "基本工资"));
            sheet.addCell(new Label(5, 0, "奖金"));
            sheet.addCell(new Label(6, 0, "日期(例:2000-01-01)"));
            sheet.addCell(new Label(7, 0, "总薪资"));
            sheet.addCell(new Label(8, 0, "雇佣方式"));
            sheet.addCell(new Label(9, 0, "计薪方式"));
            sheet.addCell(new Label(10, 0, "计税方式"));
            sheet.addCell(new Label(11, 0, "岗位薪资"));
            sheet.addCell(new Label(12, 0, "是否缴纳薪资(是/否)"));
            sheet.addCell(new Label(13, 0, "缴纳方案"));
            //用于格式化日期:
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            for (int row = 0, i = 1; row < salarys.size(); row++, i++) {
                //01:工资
                Salary salary = salarys.get(row);
                //02:工资明细:
                SalaryItem itemBySalaryId = mapper.getItemBySalaryId(salary.getId());
                //03:生成工作溥:
                sheet.addCell(new Label(0, i, salary.getEmployeeId().getUsername()));
                sheet.addCell(new Label(1, i, salary.getDepartmentId().getName()));
                sheet.addCell(new Label(2, i, salary.getTel()));
                sheet.addCell(new Label(3, i, salary.getEmail()));
                sheet.addCell(new Label(4, i, salary.getBaseSalary().toString()));
                sheet.addCell(new Label(5, i, salary.getBonus().toString()));
                //格式化日期,在存放:
                String month = date.format(salary.getMonth());
                sheet.addCell(new Label(6, i, month));
                sheet.addCell(new Label(7, i, salary.getTotalSalary().toString()));
                sheet.addCell(new Label(8, i, itemBySalaryId.getHire()));
                sheet.addCell(new Label(9, i, itemBySalaryId.getPay()));
                sheet.addCell(new Label(10, i, itemBySalaryId.getTax()));
                sheet.addCell(new Label(11, i, itemBySalaryId.getPostsalary().toString()));
                //处理是否缴纳薪资:
                sheet.addCell(new Label(12, i, itemBySalaryId.getIfpay().equals("1") ? "是" : "否"));
                sheet.addCell(new Label(13, i, itemBySalaryId.getProgramme()));
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return sheet;
    }


    //下载模板:
    public WritableSheet dowloadTemplate(WritableSheet sheet) {
        //先设置工作溥标题:
        try {
            sheet.addCell(new Label(0, 0, "姓名"));
            sheet.addCell(new Label(1, 0, "部门(例:总部)"));
            sheet.addCell(new Label(2, 0, "电话"));
            sheet.addCell(new Label(3, 0, "邮箱"));
            sheet.addCell(new Label(4, 0, "基本工资"));
            sheet.addCell(new Label(5, 0, "奖金"));
            sheet.addCell(new Label(6, 0, "日期(例:2000-01-01)"));
            sheet.addCell(new Label(7, 0, "总薪资"));
            sheet.addCell(new Label(8, 0, "雇佣方式"));
            sheet.addCell(new Label(9, 0, "计薪方式"));
            sheet.addCell(new Label(10, 0, "计税方式"));
            sheet.addCell(new Label(11, 0, "岗位薪资"));
            sheet.addCell(new Label(12, 0, "是否缴纳薪资(是/否)"));
            sheet.addCell(new Label(13, 0, "缴纳方案"));
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    //导入工资表:
    public void improtXls(Sheet sheet) {
        //01:获取所有的行数:
        int rows = sheet.getRows();
        //日期格式化：
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=1;i<rows;i++){
            //创建工资对象:
            Salary salary = new Salary();
            //创建工资明细对象:
            SalaryItem salaryItem = new SalaryItem();

            //根据员工名查询员工对象:
           Employee employee = employeeMapper.getEmployeeByUsername(sheet.getCell(0,i).getContents());
           salary.setEmployeeId(employee);
           //根据部门名查询部门对象
           Department dept = departmentMapper.getDepartmentByDept(sheet.getCell(1,i).getContents());
           salary.setDepartmentId(dept);
           salary.setTel(sheet.getCell(2,i).getContents());
           salary.setEmail(sheet.getCell(3,i).getContents());
           salary.setBaseSalary(Long.valueOf(sheet.getCell(4, i).getContents()));
           salary.setBonus(Long.valueOf(sheet.getCell(5, i).getContents()));
           //格式化日期:
            Date parse = null;
            try {
                parse = date.parse(sheet.getCell(6, i).getContents());
            } catch (Exception e) {
                e.printStackTrace();
            }
            salary.setMonth(parse);
            salary.setTotalSalary(Long.valueOf(sheet.getCell(7, i).getContents()));
            //先保存工资,拿到主键id:
            mapper.insert(salary);
            salaryItem.setHire(sheet.getCell(8, i).getContents());
            salaryItem.setPay(sheet.getCell(9, i).getContents());
            salaryItem.setTax(sheet.getCell(10, i).getContents());
            salaryItem.setPostsalary(Long.valueOf(sheet.getCell(11, i).getContents()));
            salaryItem.setIfpay("是".equals(sheet.getCell(12, i).getContents())?true:false);
            salaryItem.setProgramme(sheet.getCell(13, i).getContents());
            salaryItem.setSalaryId(salary.getId());
            //在保存工资明细:
            mapper.insertSalaryItem(salaryItem);
        }
    }
}
