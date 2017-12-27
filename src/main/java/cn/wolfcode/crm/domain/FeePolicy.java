package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class FeePolicy {
    private Long id;
    //投保人
    private Customer customer;
    //总金额
    private BigDecimal totalAmount;
    /**
     * 缴费方式
     * 0现金
     * 1刷卡
     * 2支票
     */
    private Integer paymentWay;
    /**
     * 缴费状态
     * 0未缴
     * 1已缴
     */
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date beginDate;//保单生效日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;//保单结束日期
    //录入人
    private Employee inputUser;
    //保险公司
    private Safetymechanism safetymechanism;
    //保单流水号
    private String policySn;
    //收费流水号
    private String serialNumber;
}