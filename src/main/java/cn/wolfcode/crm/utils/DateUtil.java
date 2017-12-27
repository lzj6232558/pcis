package cn.wolfcode.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {

    /**
     * 得到某一天的最后一秒钟
     */
    public static Date getEndDate(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.SECOND, -1);
        now = calendar.getTime();
        return now;//ll
    }

    /**
     * 两个时间的间隔秒
     */
    public static int getBetweenTime(Date one, Date other) {
        return (int) (Math.abs(one.getTime() - other.getTime()) / 1000);
    }



    /**
     * 获取时间和日期
     * @param date 传入一个Date对象
     * @return 返回日期和时间
     * @throws Exception
     */
    public static Date getCurrentDateTime(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        return df.parse(format);
    }

    /**
     * 获取时间
     * @param date 传入一个Date对象
     * @return 返回日期
     * @throws Exception
     */
    public static Date getCurrentDate(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);
        return df.parse(format);
    }

    /**
     * 获取时间
     * @param date 传入一个Date对象
     * @return 返回日期
     * @throws Exception
     */
    public static Date getCurrentTime(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String format = df.format(date);
        return df.parse(format);
    }

}