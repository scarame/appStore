package generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CONSTANT {
    public final static String TOKEN_KEY="AppStore!!";
    public final static int count=15;
    public  static TimeUnit C_TimeUnit=TimeUnit.MINUTES;

    public  static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
