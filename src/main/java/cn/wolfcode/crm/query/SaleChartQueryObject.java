package cn.wolfcode.crm.query;

import cn.wolfcode.crm.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class SaleChartQueryObject extends QueryObject {
    //时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    //产品
    private Long pId;
    //机构
    private Long sId;
    //分组
    private String groupType="e.username";

    //高级查询时候会取enddate,00:00:00,所以要重写
    //使用util,加一天  减一秒
    public Date getEndDate() {
        if (endDate != null) {
           return DateUtil.getEndDate(endDate);
        }
        return null;
    }

}
