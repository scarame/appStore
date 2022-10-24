package generator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CONSTANT {
    //token私钥
    public final static String TOKEN_KEY="AppStore!!";
    //token过期时间单位 和 单位数
    public final static int count=15;
    public  static TimeUnit C_TimeUnit=TimeUnit.MINUTES;
    //获取当前日期和时间
    public  static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
