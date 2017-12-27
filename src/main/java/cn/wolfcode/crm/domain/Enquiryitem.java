package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//询价单明细
@Getter @Setter @ToString
public class Enquiryitem {
    private Long id;

    private Enquiry enquiry;

    private Product product;

    private Long currentTotalmoney;

    private Long currentAnnuefee;


}