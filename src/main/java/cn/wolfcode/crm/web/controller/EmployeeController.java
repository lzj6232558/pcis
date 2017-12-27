package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * created by king on 2017/11/27
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("view")
    public String view(Model model) throws Exception {
        List<Department> depts = departmentService.selectAll();
        model.addAttribute("depts", depts);
        return "employee/employee";
    }

    @ResponseBody
    @RequestMapping("dataGrid")
    public PageResult dataGrid(@ModelAttribute("qo") EmployeeQueryObject qo) throws Exception {
        try {
            PageResult result = employeeService.query(qo);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
    //查询所有的员工对象数据
    @ResponseBody
    @RequestMapping("selectAll")
    public List<Employee> selectAll() throws Exception {
           return employeeService.selectAll();
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Employee entity) throws Exception {
        try {
            if (entity.getId() != null) {
                employeeService.updateByPrimaryKey(entity);

            } else {
                employeeService.insert(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
        return new JsonResult(true,"保存成功!");
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) throws Exception {
        try {
            if (id != null) {
                employeeService.deleteByPrimaryKey(id);
            }
            return new JsonResult(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("删除失败");
        }
    }

    @ResponseBody
    @RequestMapping("changeState")
    public JsonResult changeState(Long id) throws Exception {
        try {
            if (id != null) {
                employeeService.changeState(id);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("删除失败");
        }
    }

    //导出excel
    @RequestMapping("export")
    @ResponseBody
    public JsonResult exporXls(HttpServletResponse response) throws Exception {
        try {
            employeeService.exporXls(response);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("上传失败");
        }
    }

    //导入excel 分析我们需要给用户先弄一个模板,用户弄好模板之后再导入,我们在取出用户输入的数据,去保存在数据库
    //也就是文件上传的功能
    @ResponseBody
    @RequestMapping("importXls")
    public JsonResult importXls(MultipartFile file) throws Exception {
        try {
            employeeService.importXls(file);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("上传失败");
        }
    }

    //下载模板
    @ResponseBody
    @RequestMapping("dowloadXls")
    public JsonResult dowloadXls(HttpServletResponse response) throws Exception {
        try {
            employeeService.dowloadXls(response);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("下载失败");
        }
    }


}





