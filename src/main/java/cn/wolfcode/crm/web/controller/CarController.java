package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Car;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.CarQueryObject;
import cn.wolfcode.crm.service.ICarService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("car")
public class CarController {
    @Autowired
    private ICarService carService;

    //视图:
    @RequiresPermissions("car:view")
    @PermissionAnnotation("车辆视图")
    @RequestMapping("view")
    public String view() {
        return "/car/view";
    }

    //查询数据:

    @ResponseBody
    @RequestMapping("query")
    public PageResult query(CarQueryObject qo) {
        return carService.query(qo);
    }

    //添加或修改数据:
    @RequiresPermissions("car:saveOrUpdate")
    @PermissionAnnotation("车辆保存/修改")
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Car entity) {
        if (entity.getId() == null) {
            carService.insert(entity);
        } else {
            carService.updateByPrimaryKey(entity);
        }
        return new JsonResult(true, "保存成功!");
    }


}
