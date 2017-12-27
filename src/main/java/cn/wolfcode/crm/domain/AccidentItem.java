package cn.wolfcode.crm.domain;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class AccidentItem {
    private Long id;
    private String imagePath;           //事故现场图片
    private String introduction;        //事故描述
    private BigDecimal totalDamage;       //赔偿总金额
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date handDate;              //处理日期
    private Accident accidentId;        //报案表
    private Employee employeeId;        //处理员工

    //维护赔偿单的关系:
    List<BadTable> badTables = new ArrayList<>();

    public AccidentItem(){

    }

    //压缩之后的图片:
    public String getImagePathSmall() {
        if(!StringUtils.isEmpty(imagePath)){
            int indexOf = imagePath.lastIndexOf(".");
            return imagePath.substring(0,indexOf)+"_small"+imagePath.substring(indexOf);
        }
        return "";
    }
}