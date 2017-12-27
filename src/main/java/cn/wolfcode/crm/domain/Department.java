package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class Department {
    private Long id;                //部门id
    private String sn;              //部门编号
    private String name;            //部门名称
    private boolean state;          //状态:(小boolean)
    private Employee managerId;     //管理经理名称(关联员工表)
    private Department parentId;    //上级部门
}