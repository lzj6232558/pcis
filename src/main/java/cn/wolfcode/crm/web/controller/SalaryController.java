package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Salary;
import cn.wolfcode.crm.domain.SalaryItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SalaryQueryObject;
import cn.wolfcode.crm.service.ISalaryService;
import cn.wolfcode.crm.utils.JsonResult;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("salary")
public class SalaryController {
    @Autowired
    private ISalaryService service;

    @RequestMapping("view")
    public String view() throws Exception {
        return "salary/salary";
    }

    //查询工资表:
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(SalaryQueryObject qo) throws Exception {
        return service.query(qo);
    }
    //查询工资表明细:
    @ResponseBody
    @RequestMapping("getItemBySalaryId")
    public SalaryItem getItemBySalaryId(Long id) throws Exception {
        return service.getItemBySalaryId(id);
    }


    //导出工资表:有高级查询
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response,SalaryQueryObject qo,String fileName) {
        try {
            //设置文件下载响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            //01:创建xls文件:
            WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
            //02:创建工作溥:
            WritableSheet sheet = workbook.createSheet("sheet", 0);
            //03:生成工作溥:
            sheet = service.exportXls(sheet,qo);
            //04:写入数据
            workbook.write();
            //05:关闭流:
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载模板
    @RequestMapping("dowloadTemplate")
    public void dowloadTemplate(HttpServletResponse response) {
        try {
            //设置文件下载响应头
            response.setHeader("Content-Disposition", "attachment;filename=salaryTemplate.xls");
            //01:创建xls文件:
            WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
            //02:创建工作溥:
            WritableSheet sheet = workbook.createSheet("sheet", 0);
            //03:生成工作溥:
            sheet = service.dowloadTemplate(sheet);
            //04:写入数据
            workbook.write();
            //05:关闭流:
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //工资表导入
    @RequestMapping("improtXls")
    public String improtXls(MultipartFile file) {
        try {
            //01:获取文件:读入流的方式
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            //02:获取工作溥:
            Sheet sheet = workbook.getSheet(0);
            //03:把数据插入到数据库中:
            service.improtXls(sheet);
            //04:关闭流:
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/salary/view.do";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Salary salary) {
        Long id = salary.getId();
        try {
            if (id == null) {
                service.insert(salary);
            } else {
                service.updateByPrimaryKey(salary);
            }
            return new JsonResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败!");
    }
}
