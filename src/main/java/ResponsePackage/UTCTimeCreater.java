package ResponsePackage;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by iot on 10/12/16.
 */
public class UTCTimeCreater {
    public static String getUTCTime(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date date = calendar.getTime();
        return Integer.toHexString((int) date.getTime());
    }
}
