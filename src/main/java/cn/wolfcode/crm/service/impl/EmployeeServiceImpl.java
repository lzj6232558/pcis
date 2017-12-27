package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jxl.*;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/12/17
 */
@Service
@SuppressWarnings("all")
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        employeeMapper.deleteEmpRoleRelation(id);
    }

    @Override
    public void insert(Employee record) {
        //插入员工的时候,对员工的密码进行MD5的加密
        String md5Password = new SimpleHash("MD5", record.getPassword(), record.getUsername(), 2).toString();
        record.setPassword(md5Password);
        //----------加密操作结束------
        employeeMapper.insert(record);
        //维护关系
        List<Role> roles = record.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                employeeMapper.insertEmpRoleRelation(record.getId(),role.getId());
            }
        }
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Employee record) {
        //先打破关系
        employeeMapper.deleteEmpRoleRelation(record.getId());
        employeeMapper.updateByPrimaryKey(record);
        //维护关系
        List<Role> roles = record.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                employeeMapper.insertEmpRoleRelation(record.getId(),role.getId());
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = employeeMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = employeeMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    @Override
    public void checkLogin(String name, String password) {
        Employee employee = employeeMapper.checkLogin(name, password);
        if (employee == null) {
            //throw new LogicException("账号或者密码错误");
        }
        //否者放到共享作用域中
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getSession().setAttribute("EMPLOYEE_IN_SESSION", employee);
        //登录之后就把用户所对应的权限全部查询出来
       /* Set<String> permissions=employeeMapper.selectPermissionByEmpId(employee.getId());
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getSession().setAttribute("PERMISSION_IN_SESSION",permissions);*/
    }

    @Override
    public void changeState(Long id) {
        employeeMapper.changeState(id);
    }

    @Override
    public void exporXls(HttpServletResponse response) throws Exception{
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("employee.xls".getBytes("UTF-8"), "ISO8859-1"));
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿 sheet
        WritableSheet sheet = workbook.createSheet("first", 1);
        //添加标题
        sheet.addCell(new Label(0, 0, "用户名"));
        sheet.addCell(new Label(1, 0, "真实姓名"));
        sheet.addCell(new Label(2, 0, "电话号码"));
        sheet.addCell(new Label(3, 0, "邮箱"));
        sheet.addCell(new Label(4, 0, "入职时间"));
        sheet.addCell(new Label(5, 0, "在职状态"));
        sheet.addCell(new Label(6, 0, "所在部门名称"));
        //查询数据库中所有的员工
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0, j = 1; i < employees.size(); i++, j++) {
            Employee employee = employees.get(i);
            sheet.addCell(new Label(0, j, employee.getUsername()));
            sheet.addCell(new Label(1, j, employee.getRealname()));
            sheet.addCell(new Label(2, j, employee.getTel()));
            sheet.addCell(new Label(3, j, employee.getEmail()));
            Date inputTime = employee.getInputTime();
            if (inputTime != null) {
                DateTime dateTime = new DateTime(4, j, inputTime);
                sheet.addCell(dateTime);
            }
            sheet.addCell(new Label(5, j, employee.getUsername()));
            Department dept = employee.getDept();
            if (dept != null) {
                sheet.addCell(new Label(6, j, dept.getId().toString()));
            }
            sheet.addCell(new Label(7, j, String.valueOf(employee.isAdmin())));
        }
        //写入数据
        workbook.write();
        workbook.close();
    }

    @Override
    public void importXls(MultipartFile file) throws Exception{
        //获取需要导入的xls文件,然后根据文件上传的类得到输入流
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //得到sheet(工作薄)
        Sheet sheet = workbook.getSheet(0);
        //获取总行数
        int rows = sheet.getRows();
        //读取数据,不读取标题,插入到数据库中
        for (int i = 1; i < rows; i++) {
            Employee employee = new Employee();
            employee.setUsername(sheet.getCell(0, i).getContents());
            employee.setRealname(sheet.getCell(1, i).getContents());
            employee.setTel(sheet.getCell(2, i).getContents());
            employee.setEmail(sheet.getCell(3, i).getContents());
            Cell cell = sheet.getCell(4, i);
            DateCell dc = (DateCell) cell;
            String cellcon = "";
            if (cell.getType() == CellType.DATE) {
                Date date = dc.getDate();
                SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                cellcon = ds.format(date);
                Date newDate = ds.parse(cellcon);
                employee.setInputTime(newDate);
            }
            employee.setState(true);
            Department dept = new Department();
            dept.setId(new Long(sheet.getCell(5, i).getContents()));
            employee.setDept(dept);
            //得到admin
            employee.setAdmin(new Boolean(sheet.getCell(6, i).getContents()));
            employeeMapper.insert(employee);
        }
        //关闭资源
        workbook.close();
    }
    //下载模板
    @Override
    public void dowloadXls(HttpServletResponse response) throws Exception{
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("employeeTemplate.xls".getBytes("UTF-8"), "ISO8859-1"));
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿 sheet
        WritableSheet sheet = workbook.createSheet("first", 1);
        //添加标题
        sheet.addCell(new Label(0, 0, "用户名"));
        sheet.addCell(new Label(1, 0, "真实姓名"));
        sheet.addCell(new Label(2, 0, "密码"));
        sheet.addCell(new Label(3, 0, "电话号码"));
        sheet.addCell(new Label(4, 0, "邮箱"));
        sheet.addCell(new Label(5, 0, "入职时间"));
        sheet.addCell(new Label(6, 0, "在职状态"));
        sheet.addCell(new Label(7, 0, "所在部门编号"));
        sheet.addCell(new Label(8, 0, "是否是管理员"));
        //打印出格式的注意事项
        List<Map<String,Object>> departments = departmentMapper.findIdAndName();
        //部门的编号对应的部门名称
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(departments);
        sheet.addCell(new Label(0, 1, "请注意:所有内容必须按照第一行提供的样式填写,不能有错别字,除支付时间中间有一个空格外所有数据不得存在空格月份的格式必须按照yyyy-MM-dd的格式填写" +"部门填写的对照表"+json+
                "第二行为填写示例,请不要改动,以上规范请严格遵守,否则会导致工资表上传失败!"));
        sheet.addCell(new Label(0, 2, "king"));
        sheet.addCell(new Label(1, 2, "王健林"));
        sheet.addCell(new Label(2, 2, "ty34578"));
        sheet.addCell(new Label(3, 2, "17671285698"));
        sheet.addCell(new Label(4, 2, "xxx.@163.com"));
        //-------格式化日期--------
        SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
        String cellcon = ds.format(new Date());
        Date newDate = ds.parse(cellcon);
        DateTime dateTime = new DateTime(5, 2, newDate);
        sheet.addCell(dateTime);
        //---------
        sheet.addCell(new Label(6, 2, "0"));
        sheet.addCell(new Label(7, 2, "2"));
        sheet.addCell(new Label(8, 2, "0"));
        //写入数据
        workbook.write();
        workbook.close();
    }

    public Employee selectByUsername(String principal) {
        return employeeMapper.selectByUsername(principal);
    }

   // public Employee selectByUsername(String principal) {
       // return employeeMapper.selectByUsername(principal);
    //}
}
