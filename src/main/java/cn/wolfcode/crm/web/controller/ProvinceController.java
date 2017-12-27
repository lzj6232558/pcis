package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Province;
import cn.wolfcode.crm.service.IProvinceService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Province entity){
        if(entity.getId() == null){
            provinceService.insert(entity);
        }else{
            provinceService.updateByPrimaryKey(entity);
        }
        return new JsonResult(true,"保存成功!");
    }
    @ResponseBody
    @RequestMapping("selectAllProvince")
    public List<Province> selectAllProvince(){
       return provinceService.selectAllProvince();
    }
    @ResponseBody
    @RequestMapping("selectAllCity")
    public List<Province> selectAllCity(Long provinceId){
       return provinceService.selectAllCity(provinceId);
    }
    @ResponseBody
    @RequestMapping("selectHotCity")
    public List<Province> selectHotCity(){
       return provinceService.selectHotCity();
    }
}
