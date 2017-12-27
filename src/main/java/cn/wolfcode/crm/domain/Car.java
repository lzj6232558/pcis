package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Getter@Setter@ToString
public class Car {
    private Long id;

    private Customer customer;//客户

    private String brand;//品牌

    private String model;//车辆型号

    private String plateNumber;//牌照

    private Long gasDisplacement;//排气量
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //后台传到前台格式化
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;//购买日期

    private String remarks;//备注

    private BigDecimal valuation=new BigDecimal(0);//市场估价

    private boolean category;////车的分类  载客/载物

    private Integer size;//车在大小  1:大型,2:小型3:中型

    private String VIN;//车架号
    private String engineNumber;//发动机号


}