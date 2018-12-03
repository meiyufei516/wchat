package com.myf.wchat.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

    /**
     * 将Long型的毫秒级时间转换为yyyy-MM-dd HH:mm:ss的Timestamp时间
     * @param createTime
     * @return
     */
    public static Timestamp formatTime(String createTime) {
        // 将微信传入的CreateTime转换成long类型，再乘以1000
        long msgCreateTime = Long.parseLong(createTime) * 1000L;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  Timestamp.valueOf(format.format(new Date(msgCreateTime)));
    }

    /**
     * 获取日志时间
     * @return
     */
    public static String getLogTime(){
        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loggerTime = sdf.format(d);
        return loggerTime;
    }

    /**
     * 将指定日期往前加五天
     * @param time
     * @return
     * @throws ParseException
     */
    public static String turnTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime=sdf.parse(time);                     //将时间转换为Date格式
        Calendar Date = Calendar.getInstance();
        Date.setTime(dateTime);
        int inputDayOfYear = Date.get(Calendar.DAY_OF_YEAR);
        Date.set(Calendar.DAY_OF_YEAR , inputDayOfYear+5 );//往前加5天
        return sdf.format(Date.getTime());
    }

    /**
     * string转换为Timestamp
     * @param time
     * @return
     */
    public static Timestamp strToTimestamp(String time){
        Timestamp timestamp = Timestamp.valueOf(time);
        return timestamp;
    }

    /**
     * Date转string
     * @param time
     * @return
     */
    public static String dataToStr(Date time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }
}
