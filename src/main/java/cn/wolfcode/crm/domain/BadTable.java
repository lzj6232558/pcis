package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BadTable {
    private Long id;
    private String badPath;     //损坏位置
    private String badDegree;   //损坏程度
    private MultipartFile badImage;    //损坏图片
    private Long badMoney;      //损坏单个赔偿
    private AccidentItem accidentItemId;      //损坏单个赔偿

    private String employee;        //处理员工

}