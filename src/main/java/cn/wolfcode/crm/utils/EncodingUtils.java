package cn.wolfcode.crm.utils;

import java.io.UnsupportedEncodingException;

/**
 * created by king on 2017/12/24
 */
public abstract class EncodingUtils {


    public static String encoding(String str) {
        try {
            return new String(str.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
