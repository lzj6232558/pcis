package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class DictionaryItem {
    private Long id;

    private String sn;

    private String name;

    private String intro;

    private Dictionary dictionary;


}