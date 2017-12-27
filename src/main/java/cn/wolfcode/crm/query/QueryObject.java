package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryObject {
	private int page=1;//当前页
	private int rows=3;//页面大小

	public int getBeginIndex(){
		return (page-1)*rows;
	}


}
