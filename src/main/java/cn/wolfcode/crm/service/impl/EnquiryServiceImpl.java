package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.*;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SaveBillQueryObject;
import cn.wolfcode.crm.service.IEnquiryService;
import cn.wolfcode.crm.utils.DateUtil;
import cn.wolfcode.crm.utils.SerialNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class EnquiryServiceImpl implements IEnquiryService {
    @Autowired
    private EnquiryMapper enquiryMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private PolicyDetailMapper policyDetailMapper;

    @Autowired
    private EnquiryitemMapper enquiryitemMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        enquiryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Enquiry record, Long[] productIds, BigDecimal[] totalMoney, BigDecimal[] annuafe) {
        //插入到客户表中
        Customer customer = new Customer();
        customer.setName(record.getCustomer().getName());
        customer.setGender(record.getCustomer().isGender());
        customer.setIdno(record.getCustomer().getIdno());
        customer.setTel(record.getCustomer().getTel());
        customerMapper.insert(customer);
        //车辆信息插入到car表中
        Car car = new Car();
        car.setCustomer(record.getCar().getCustomer());
        car.setBrand(record.getCar().getBrand());
        car.setModel(record.getCar().getModel());
        car.setPlateNumber(record.getCar().getPlateNumber());
        car.setGasDisplacement(record.getCar().getGasDisplacement());
        car.setCategory(record.getCar().isCategory());
        car.setPurchaseDate(record.getCar().getPurchaseDate());
        car.setValuation(record.getCar().getValuation());
        car.setSize(record.getCar().getSize());
        car.setVIN(record.getCar().getVIN());
        car.setEngineNumber(record.getCar().getEngineNumber());
        carMapper.insert(car);
        //设置关联的车的id,;客户的id
        record.setCar(car);
        record.setCustomer(customer);
        record.setRegisterDate(new Date());
        //-------
        record.setOfferNumber(SerialNumber.Getnum());
        enquiryMapper.insert(record);//
        //维护关系
        if (productIds != null) {
            //再插入数据到数据库中
            for (int index = 0; index < productIds.length; index++) {
                enquiryMapper.insertRelation(record.getId(), productIds[index], totalMoney[index], annuafe[index]);
            }
        }
    }

    @Override
    public Enquiry selectByPrimaryKey(Long id) {
        return enquiryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Enquiry> selectAll() {
        return enquiryMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Enquiry record, Long[] productIds, BigDecimal[] totalMoney, BigDecimal[] annuafee) {
        System.out.println();
        //先打破关系,再插入到中间明细表中
        enquiryMapper.deleteEnquiryProductRelationByEnqId(record.getId());
        //enquiryMapper.updateByPrimaryKey(record);
        //再插入数据到数据库中
        for (int index = 0; index < productIds.length; index++) {
            enquiryMapper.insertRelation(record.getId(), productIds[index], totalMoney[index], annuafee[index]);
        }
        enquiryMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = enquiryMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Enquiry> data = enquiryMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    /**
     * 转存保单
     *
     * @param id
     */
    @Override
    public void saveBill(Long id) {
        SaveBillQueryObject saveBillQueryObject = new SaveBillQueryObject();

        saveBillQueryObject.setId(id);
        List<Enquiry> list = enquiryMapper.queryForList2(id);
        List<Enquiryitem> enquiryitems = enquiryitemMapper.selectItemsByEnqId(id);
        List<Long> annuefees=new ArrayList<>();
        List<Long> totalmoneys=new ArrayList<>();
        for (Enquiryitem enquiryitem : enquiryitems) {
            annuefees.add(enquiryitem.getCurrentAnnuefee());
            totalmoneys.add(enquiryitem.getCurrentTotalmoney());
        }
        Long[] annuefeeArr=new Long[annuefees.size()];
        Long[] totalmoneyArr=new Long[totalmoneys.size()];
        for(int index=0;index<annuefees.size();index++){
            annuefeeArr[index]=annuefees.get(index);
            totalmoneyArr[index]=totalmoneys.get(index);
        }

        Policy policy = new Policy();
        //System.out.println(list);
        Long[] productIds = new Long[10];
        Car car = null;
        Customer customer = null;
        BigDecimal totalAmount = null;
        Safetymechanism safetymechanism=null;
        for (Enquiry enquiry : list) {
            car = enquiry.getCar();
            customer = enquiry.getCustomer();
            totalAmount = enquiry.getTotalAmount();
            safetymechanism = enquiry.getSafetymechanism();
            List<Product> product = enquiry.getProduct();
            for (int index = 0; index < product.size(); index++) {
                productIds[index] = product.get(index).getId();
            }
        }
        customerMapper.updateState2(customer.getId());
        policy.setSafetymechanism(safetymechanism);
        policy.setTotalAmount(totalAmount);
        policy.setSn(SerialNumber.Getnum());
        policy.setState(1);//以提交待审核
        String s = totalAmount.toString();
        int i = s.lastIndexOf(".");
        String substring = s.substring(0, i);
        //System.out.println(policyService);

       //policyService.insert(policy, productIds, Long.valueOf(substring));
        insert(policy,productIds,Long.valueOf(substring),car,customer,annuefeeArr,totalmoneyArr,id);

    }

    private void insert(Policy policy, Long[] productIds, Long totalAmount,Car car,Customer customer, Long[] annuefeeArr,Long[] totalmoneyArr,Long id) {
        policy.setApplyDate(new Date());
        car.setCustomer(customer);
        policy.setCar(car);
        policy.setCustomer(customer);
        policy.setBeginDate(new Date());
        Employee employee =(Employee) SecurityUtils.getSubject().getPrincipal();
        policy.setInputUser(employee);
        //int duration = policy.getDuration();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.getEndDate(new Date()));
        calendar.add(Calendar.YEAR,1);
        //插入保单的结束时间
        policy.setEndDate(calendar.getTime());
        policyMapper.insert(policy);
        //插入到投保单明细表中
        PolicyDetail policyDetail = new PolicyDetail();
        policyDetail.setPolicy(policy);
        policyDetail.setAmount(totalAmount);
        policyDetail.setRemark(policy.getRemark());
        //插入到订单明细表中
        //policyDetailMapper.insert(policyDetail);
        //插入到保单表和产品表的中间表中
        /*if (productIds!=null){
            for (Long pId : productIds) {
                if (pId==null){
                    break;
                }
                //policyMapper.insertPolicyProductRelation(pId,policy.getId());
            }
        }*/
        if (productIds!=null){
            for (int index=0;index<productIds.length;index++){
                if (productIds[index]==null){
                    break;
                }
                policyMapper.insertPolicyProductRelation(productIds[index],policy.getId(),new BigDecimal(totalmoneyArr[index]),new BigDecimal(annuefeeArr[index]));
            }
        }
        //更新询价表的状态为1,也就是已经转承保单
        enquiryMapper.updateState(id);
    }

}
