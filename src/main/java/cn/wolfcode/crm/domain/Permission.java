package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Permission {
    private Long id;

    private String name;

    private String resource;
}