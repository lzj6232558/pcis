package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter@Setter
public class Product {
    private Long id;

    //保险编号
    private String sn;
    //保险名称
    private String name;
    //保险机构
    private Safetymechanism safetyMechanism;
    //保障年限
    private Long safeguardYear;
    //保额
    private BigDecimal totalMoney;
    //基本年费
    private BigDecimal annuafee;
    //销售状态
    private boolean salesStatus;
    //不计免赔
    private boolean undeduction;
    //简介
    private String intro;

    private int size;//车辆的型号
    private java.util.List<Enquiryitem> enquiryitems=new ArrayList<>();

    private BigDecimal currenAnnufee;
    private BigDecimal currentTotalMoney;
}