package com.zhy.mytest;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.file.Tailer;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@RunWith(SpringRunner.class)
@SpringBootTest
class MytestApplicationTests {

    @Test
    public void test() {

    //        int a = 1;
    //        //aStr为"1"
    //        String aStr = Convert.toStr(a);
    //        System.out.println(aStr);
    //        long[] b = {1,2,3,4,5};
    //        //bStr为："[1, 2, 3, 4, 5]"
    //        String bStr = Convert.toStr(b);
    //        System.out.println(bStr);
    //        System.out.println(SystemUtil.getRuntimeInfo());

    //        //定义图形验证码的长和宽
    //        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
    //        //图形验证码写出，可以写出到文件，也可以写出到流
    //        lineCaptcha.write("D:/line.png");
    //        //输出code
    //        Console.log(lineCaptcha.getCode());
    //        // 验证图形验证码的有效性，返回boolean值
    //        System.out.println(lineCaptcha.verify("1234"));
    //        //重新生成验证码
    //        lineCaptcha.createCode();
    //        lineCaptcha.write("D:/line.png");
    //         //新的验证码
    //        Console.log(lineCaptcha.getCode());
    //          //验证图形验证码的有效性，返回boolean值
    //        lineCaptcha.verify("1234");

    //        String dateStr = "2017-03-01";
    //        Date date = DateUtil.parse(dateStr);
    //        String format = DateUtil.format(date, "yyyy/MM/dd");
    //        Console.log(format);

    //          Date date = DateUtil.nextMonth();
    //          Console.log(DateUtil.year(date));
    //          Console.log(DateUtil.month(date)+1);
    //          Console.log(DateUtil.monthEnum(date));
    //          Console.log(DateUtil.dayOfMonth(date));

    //            String dateStr1 = "2017-03-01 22:33:23";
    //            Date date1 = DateUtil.parse(dateStr1);
    //
    //            String dateStr2 = "2017-04-01 23:33:23";
    //            Date date2 = DateUtil.parse(dateStr2);
    //
    //            //相差一个月，31天
    //            long betweenDay = DateUtil.between(date1, date2, DateUnit.SECOND);
    //            Console.log(betweenDay);

    //        TimeInterval timer = DateUtil.timer();
    //        try {
    //            Thread.sleep(3000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //        //花费毫秒数
    //        Console.log(timer.interval());
    //        //返回花费时间，并重置开始时间
    //        //Console.log(timer.intervalRestart());
    //        //花费分钟数
    //        Console.log(timer.intervalMinute());

    //       //属性
    //       Console.log(DateUtil.getChineseZodiac(1998));
    //       //星座
    //       Console.log(DateUtil.getZodiac(Month.SEPTEMBER.getValue(), 1));

    //        BufferedInputStream in = FileUtil.getInputStream("d:/line.png");
    //        BufferedOutputStream out = FileUtil.getOutputStream("e:/zhy.png");
    //        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
    //        Console.log(copySize);

    //        // 默认UTF-8编码，可以在构造中传入第二个参数做为编码
    //        FileReader fileReader = new
    // FileReader("E:\\ideaWorkSpace\\mytest\\src\\main\\resources\\application.properties");
    //        String result = fileReader.readString();
    //        Console.log(result);

    //        //写入文件
    //        FileWriter fileWriter = new FileWriter("E:\\ideaWorkSpace\\mytest\\src\\main\\resources\\application.properties");
    //        //写入到文件 会覆盖
    //        fileWriter.write("zhy=zhy");
    //        //追加到文件
    //        fileWriter.write("zhy=zhy",true);
    //        fileWriter.append("zhy=zhy");


//          文件追加
//        File file = new File("D:\\zhy.txt");
//        FileAppender appender = new FileAppender(file, 16, true);
//        appender.append("123");
//        appender.append("abc");
//        appender.append("xyz");
//        appender.flush();
//        Console.log(appender.toString());

//        //监听文件  类似于linux  tail -f
//        Tailer tailer = new Tailer(FileUtil.file("D:\\zhy.txt"), Tailer.CONSOLE_HANDLER, 2);
//        tailer.start();
//        /**
//         * 命令行打印的行处理器
//         * */
//        class ConsoleLineHandler implements LineHandler {
//            @Override
//            public void handle(String line) {
//                Console.log(line);
//            }
//        }

//
//        String str = ResourceUtil.readUtf8Str("application.properties");
//        Console.log(str);


//        //读取classpath项目配置文件
//        ClassPathResource resource = new ClassPathResource("application.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(resource.getStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Console.log("Properties: {}", properties);

        //命令行工具
        String str = RuntimeUtil.execForStr("ipconfig");
        Console.log(str);
        FileWriter fileWriter = new FileWriter("d:/zhy.txt");
        fileWriter.write(str);









    }

}
