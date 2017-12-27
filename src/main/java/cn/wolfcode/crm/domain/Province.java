package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Province {
    private Integer id;

    private String name;

    private Province parent;
}