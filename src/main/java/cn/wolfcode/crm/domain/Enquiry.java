package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
@Getter@Setter@ToString
public class Enquiry {
    private Long id;

    private String city;//城市信息

    private BigDecimal totalAmount;//总价
    private Car car;

    private Customer  customer;//客户信息

    private java.util.List<Product> product;//保险产品

    private Safetymechanism safetymechanism;//保险机构

    private String origin;//业务来源
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //后台传到前台格式化
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;//询价时间
    private Employee employee;
    private String offerNumber;

    private java.util.List<Enquiryitem> enquiryitems=new ArrayList<>();
    /**
     * 0 没有转投保
     * 1 转投保单
     */
    private int state;//询价单的状态,是否转投保单
}