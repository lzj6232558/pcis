package cn.wolfcode.crm.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询的结果
 *
 * @author include
 */
@Getter
@Setter
@ToString
@SuppressWarnings("all")
public class PageResult {
    public static final PageResult EMPTY_PAGE = new PageResult(0, Collections.EMPTY_LIST);

    private int total;//总条数
    private List<?> rows;//数据集

    public PageResult(int totalCount, List<?> rows) {

        this.total = totalCount;
        this.rows = rows;
    }

}



