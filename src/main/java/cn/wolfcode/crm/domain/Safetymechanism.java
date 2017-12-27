package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Safetymechanism {
    private Long id;
    //机构名称
    private String name;
    //机构编码
    private String sn;
    //法人代表
    private String legalperson;
    //法人证件号码
    private String dentitycard;
    //联系方式
    private String contactway;
    //地址
    private String address;
    //合作状态
    private Boolean cooperation;

}