package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.domain.FeePolicy;
import cn.wolfcode.crm.mapper.FeePolicyMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.FeePolicyQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IFeePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;import java.util.regex.Matcher;import java.util.regex.Pattern;

@Service
public class FeePolicyServiceImpl implements IFeePolicyService {
    @Autowired
    private FeePolicyMapper feePolicyMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return feePolicyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FeePolicy record) {
        return feePolicyMapper.insert(record);
    }

    @Override
    public FeePolicy selectByPrimaryKey(Long id) {
        return feePolicyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FeePolicy> selectAll() {
        return feePolicyMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(FeePolicy record) {
        return feePolicyMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = feePolicyMapper.queryForyCount(qo);
        if (count == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<FeePolicy> list = feePolicyMapper.queryForList(qo);
        return new PageResult(count, list);
    }

    @Override
    public List<String> autoSearchPolicySn(String param) {
        return feePolicyMapper.autoSearchPolicySn(param);
    }

    @Override
    public List<String> autoSearchKeywords(String param) {
        if(!isNumeric(param)){
            return feePolicyMapper.autoSearchName(param);
        }else {

            return feePolicyMapper.autoSearchIdno(param);
        }
    }


    /**
     * 判断字符串是否有数字组成
     * @param str 要判断的字符串
     * @return true为是数字
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
