package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

//保单的信息
@Getter
@Setter
@ToString
public class Policy {
    private Long id;

    private String sn;
    /**
     * 0 表示暂存
     * 1 已提交待审核
     * 2 已审核待缴费
     * 3.待修改
     * 4. 已经缴费
     * 5.拒保
     */
    private int state;//保单状态

    private Customer customer;//客户信息

    private Car car;//车辆信息

    private BigDecimal totalAmount;//保单的总价格
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date applyDate;//申请保单的时间
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;//核保日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date beginDate;//保单生效时间
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;//保单结束时间

    private int duration;//持续时间

    private Employee inputUser;//录入人

    private Employee auditor;//审核保单的人

    private Safetymechanism safetymechanism;//机构的名称

    private String remark;//备注
    //产品明细
    private java.util.List<Product> products =new ArrayList<>();
    //总价
}