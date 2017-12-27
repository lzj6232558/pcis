package cn.wolfcode.crm.query;

import cn.wolfcode.crm.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class ClaimChartQueryObject extends QueryObject {
    //时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //业务人员
    private Long eId=-1L;
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
