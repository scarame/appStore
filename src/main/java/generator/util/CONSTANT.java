package generator.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CONSTANT {
    //token私钥
    public final static String TOKEN_KEY=getAttribute("private_key");
    //token过期时间单位 和 单位数
    public final static int count=Integer.parseInt(getAttribute("time_count"));
    public final static TimeUnit C_TimeUnit=TimeUnit.MINUTES;
    public final static String portrait_storage_path=getAttribute("portrait_storage_path");
    public final static String app_storage_path=getAttribute("app_storage_path");
    public final static String app_icon_path=getAttribute("app_icon_path");
    //获取当前日期和时间
    public  static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private static String getAttribute(String key){
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(".\\appStoreConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key).toString();
    }
}
