package com.zhy.mytest;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhy
 * @Date:2021/1/15
 * @Description:
 **/
public class Test {
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获取当前时间与给定的时间之间相差的天数
     * @param date
     * @return
     */
    private static Integer getDaysFromCurrentDate(Date date){
        Date currentData = new Date();
        if(date.getTime()>=currentData.getTime()){
            return 0;
        }
        return  (int) ((currentData.getTime() - date.getTime()) / (1000*3600*24));
    }

    public static boolean isPhoneNum(String mobiles){
        Pattern p = Pattern.compile("^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$");
         Matcher m = p.matcher(mobiles);
         return m.matches();
    }

    public static void main(String[] args) throws ParseException {
    //        String msg = "1dasd23424@163.con";
    //        System.out.println(isEmail(msg));
    //        String dataStr = "2021-01-20";
    //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //        Date parse = simpleDateFormat.parse(dataStr);
    //        System.out.println(getDaysFromCurrentDate(parse));
    System.out.println(isPhoneNum("13812345678"));
    }
}
