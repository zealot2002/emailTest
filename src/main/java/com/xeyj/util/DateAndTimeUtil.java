package com.xeyj.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeUtil {
	public static String DateToString(Date time) {
        String dateStr = "";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");
        try {
            dateStr = dateFormat.format(time);
            System.out.println(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
	
	public static String timestampToString(Timestamp ts){
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try {  
            tsStr = sdf.format(ts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println(tsStr);
        return tsStr;
    }
}
