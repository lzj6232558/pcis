package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Teampolicy;
import cn.wolfcode.crm.query.TeamQueryObject;
import cn.wolfcode.crm.service.ITeampolicyService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("teampolicy")
public class TeampolicyController {
    @Autowired
    private ITeampolicyService teampolicyService;

    //视图跳转
    @RequestMapping("view")
    public String input() {
        return "teampolicy/view";
    }

    //查询
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("query")
    public Object query(TeamQueryObject qo) {

        return teampolicyService.query(qo);
    }
///
    //添加或修改
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("saveOrUpdate")
    @RequiresPermissions("teampolicy:saveOrUpdate")
    @PermissionAnnotation("明细新增/编辑")
    public JsonResult saveOrUpdate(Teampolicy entity,Long customId) {
            teampolicyService.insert(entity,customId);
        return new JsonResult(true,"保存成功!");
    }

    //添加或修改
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("delete")
    @RequiresPermissions("teampolicy:delete")
    @PermissionAnnotation("明细删除")
    public JsonResult delete(Long id) {
        teampolicyService.deleteByPrimaryKey(id);
        return new JsonResult(true,"删除成功!");
    }

}

