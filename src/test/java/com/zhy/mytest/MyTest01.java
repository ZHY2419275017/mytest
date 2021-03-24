package com.zhy.mytest;


import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.URLUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.awt.datatransfer.Clipboard;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author: zhy
 * @Date:2020/9/10
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest01 {

    @Test
    public void test() {
//        ZonedDateTime zdt = ZonedDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZZ");
//        System.out.println(formatter.format(zdt));
//
//        DateTimeFormatter zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
//        System.out.println(zhFormatter.format(zdt));
//
//        DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
//        System.out.println(usFormatter.format(zdt));
//
//        LocalDateTime now = LocalDateTime.now();
//        Console.log(DateTimeFormatter.ISO_DATE.format(now)) ;
//        Console.log(DateTimeFormatter.ISO_DATE_TIME.format(now)) ;

//        Instant instant = Instant.now();
//        //秒
//        Console.log(instant.getEpochSecond());
//        //毫秒
//        Console.log(instant.toEpochMilli());

//        Stream<String> stream = Stream.of("A", "B", "C", "D");
//        // forEach()方法相当于内部循环调用，
//        // 可传入符合Consumer接口的void accept(T t)的方法引用：
//        stream.forEach(System.out::println);

//        Stream<String> stream1 = Arrays.stream(new String[] { "A", "B", "C" });
//        Stream<String> stream2 = List.of("X", "Y", "Z").stream();
//        stream1.forEach(System.out::println);
//        stream2.forEach(System.out::println);


//
//        Stream<Integer> integerStream = Stream.of(1, 2, 3);
//        Stream<Integer> integerStream1 = integerStream.map(n -> n + 1);
//        integerStream1.forEach(System.out::println);
//
//        Stream<Integer> stream = Arrays.asList(1, 2, null).stream();
//        stream.forEach(System.out::println);


        String phone = "18738700810";
        Console.log(phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));

    }


    @Test
    public void test02(){
        String s= "3.000000";
        String s1 = s.substring(s.length()-4);
        Console.log(s1);
    }


    public static boolean isEmail(String email){
        if (null==email || "".equals(email)){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
    @Test
    public  void test11() {
        String msg = "1dasd23424@qq。cmn";
        System.out.println(isEmail(msg));
    }


    @Test
    public void test022(){
        String body = "366466 - 副本.jpg";
        String encode = URLUtil.encode(body);
        String path = URLUtil.getPath("http://www.aaa.bbb/search?scope=ccc&q=ddd");
        Console.log(encode);
        Console.log(path);

    }

    @Test
    public void test033(){
        Integer[] a = {1,2,3,4,5,6};
        Integer[] filter = ArrayUtil.filter(a, new Editor<Integer>(){
            @Override
            public Integer edit(Integer t) {
                return (t % 2 == 0) ? t : null;
            }});

        for (int i = 0; i < filter.length; i++) {
            Console.log(filter[i]);
        }

    }


    @Test
    public void test044(){

        String[] keys = {"a","b","c"};
        Integer[] values = {1,2,3};
        Map<String, Integer> map = ArrayUtil.zip(keys, values, true);
        for(String key : map.keySet()){
            Console.log(key+"---"+map.get(key));
        }

    }


    @Test
    public void test055(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s2 == s6);
        System.out.println(s5 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
    }

}
