package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 报案表
 */
@Getter@Setter@ToString
public class Cases {
    private Long id;

    private String name;//报案人姓名
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //后台传递到前台的格式化
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime;//受理时间

    private Employee employee;//受理人

    private String sex;//性别

    private String plateNumbers;//车牌

    private Integer tel;//电话

    private String address;//地址

    private Policy policy;//保单

}