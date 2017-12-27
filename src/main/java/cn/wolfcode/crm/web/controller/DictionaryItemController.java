package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
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
@RequestMapping("dictionaryItem")
public class DictionaryItemController {
    @Autowired
    private IDictionaryItemService DictionaryItemService;


    //视图:
    @RequestMapping("view")
    public String view(){
        return "/dictionaryItem/view";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(DictionaryItemQueryObject qo){
        System.out.println(qo.getSn());
        return DictionaryItemService.query(qo);
    }

    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(DictionaryItem entity){
        try {
            if(entity.getId() == null){
                DictionaryItemService.insert(entity);
                return new JsonResult(true,"保存成功");
            }else{
                DictionaryItemService.updateByPrimaryKey(entity);
                return new JsonResult(false,"更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"保存或者修改失败");
        }
    }
    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) throws Exception {
        try {
            if (id != null) {
                DictionaryItemService.deleteByPrimaryKey(id);
            }
            return new JsonResult(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
    }


    @ResponseBody
    @RequestMapping("select")
    public List<DictionaryItem> select(String sn) throws Exception {
            return DictionaryItemService.select(sn);
        }
    }
