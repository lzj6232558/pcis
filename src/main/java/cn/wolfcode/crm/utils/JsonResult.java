package cn.wolfcode.crm.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * created by king on 2017/11/29
 * json工具集合
 */
@Getter
@Setter
@ToString

public class JsonResult {
    private boolean success = true;
    private String message;
    private String mark;

    /**
     * 没有异常,直接返回调用空构造器
     */
    public JsonResult() {
        this.message = message;
    }

    /**
     * 有异常,调用该构造器
     *
     * @param message 异常信息
     */
    public JsonResult(String message) {
        this.success = false;
        this.message = message;
    }

    /**
     * 自定义
     *
     * @param success
     * @param message
     */
    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
