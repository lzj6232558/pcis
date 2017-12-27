package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController {
    @Autowired
    private IDictionaryService DictionaryService;


    //视图:
    @RequestMapping("view")
    public String view() {
        return "/dictionary/dictionary";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(QueryObject qo) {
        return DictionaryService.query(qo);
    }

    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Dictionary entity) {
        try {
            if (entity.getId() == null) {
                DictionaryService.insert(entity);
                return new JsonResult(true, "保存成功!");
            } else {
                DictionaryService.updateByPrimaryKey(entity);
                return new JsonResult(true, "更新成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) throws Exception {
        try {
            if (id != null) {
                DictionaryService.deleteByPrimaryKey(id);
            }
            return new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
    }
    //查询数据库中所有的字典目录 id:  text
    @ResponseBody
    @RequestMapping("selectDictionary")
    public List<Map<String,Object>> selectDictionary(){

        return DictionaryService.selectDictionary();
    }

}
