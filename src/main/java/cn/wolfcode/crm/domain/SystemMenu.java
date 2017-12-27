package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SystemMenu {
    private Long id;

    private String text;

    private String url;

    private String sn;

    //由于系统菜单表中,子菜单和父菜单再一张表中,所以这里用到自连接,
    private List<SystemMenu> children;

    //菜单和权限关联
    private Permission permission;

    //父菜单
    private SystemMenu parentMenu;
}