package com._520it.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lyhzzz
 * @date 2017/11/9
 */
public class DateUtil {

    public static  Date getEndDate(Date now){

        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }
    public static  Date getMonthPlus(Date now){

        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.MONTH,1);

        return c.getTime();
    }

    public static Date getStart(Date now) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(now);
        Date date = simpleDateFormat.parse(format);
        return date;
    }

    public static Date getTime(String date)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = simpleDateFormat.parse(date);
        return time1 ;

    }

}
