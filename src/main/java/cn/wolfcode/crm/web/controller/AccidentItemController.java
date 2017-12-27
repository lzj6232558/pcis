package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.AccidentItem;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentItemQueryObject;
import cn.wolfcode.crm.service.IAccidentItemService;
import cn.wolfcode.crm.utils.JsonResult;
import cn.wolfcode.crm.utils.UploadUtil;
import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("accidentItem")
public class AccidentItemController {
    @Autowired
    private IAccidentItemService service;
    @Autowired
    private ServletContext ctx;

    @RequestMapping("view")
    public String view(Model model) throws Exception {
        return "accident/accidentItem";
    }

    @ResponseBody
    @RequestMapping("query")
    public PageResult query(AccidentItemQueryObject qo) throws Exception {
        return service.query(qo);
    }


    @ResponseBody
    @RequestMapping("save")
    public JsonResult save(AccidentItem accidentItem) throws Exception {
        try {
            service.insert(accidentItem);
            return new JsonResult(true,"生成成功!");
        }catch (Exception e){
            return new JsonResult("生成失败!");
        }

    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(MultipartFile imageFile,AccidentItem accidentItem) {
        //01:设置事故明细单处理员工:
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        accidentItem.setEmployeeId(employee);

        //02:拿到图片对象之后,调用工具类处理:
        //因为entity.getImagePath()可能为"",所以要用StringUtils工具类的方法去判断是否为Null;
        //删除图片
       if(imageFile != null && !StringUtils.isEmpty(accidentItem.getImagePath())){
            UploadUtil.deleteFile(ctx, accidentItem.getImagePath());
        }
       //添加图片：
        if(imageFile != null){
            String realPath = ctx.getRealPath("/static/upload");
            String imagePath = UploadUtil.upload(imageFile, realPath);
            accidentItem.setImagePath(imagePath);
        }
        //03:修改数据：
        try {
            service.updateByPrimaryKey(accidentItem);
            return new JsonResult(true,"保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败!");
        }
    }
    //删除
    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult("删除失败!");
        }
    }

}
