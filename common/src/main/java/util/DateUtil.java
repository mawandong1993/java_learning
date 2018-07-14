package util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author mawandong
 * @date 2018/3/1 0:02
 */
public class DateUtil {

    public static final String DATA_FORM ="yyyy-MM-dd";
    public static final String TIME_FORM ="yyyy-MM-dd HH:mm:ss";

    public static String format(Date date){
        try {
            return DateFormatUtils.format(date, DateUtil.TIME_FORM);
        } catch (Exception e) {
            return null;
        }
    }
    public static String format(Date date,String pattern){
        try {
            return DateFormatUtils.format(date, pattern);
        } catch (Exception e) {
            return null;
        }
    }


}
