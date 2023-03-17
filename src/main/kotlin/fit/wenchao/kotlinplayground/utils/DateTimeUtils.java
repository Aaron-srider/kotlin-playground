package fit.wenchao.kotlinplayground.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    /**
     * Constants represent common date format string
     */
    public enum DateFormatEnum {
        UGLY_DEFAULT_ONE("EEE MMM dd HH:mm:ss zzz yyyy"),

        DATE_TIME("yyyy-MM-dd hh:mm:ss"),
        DATE_ONLY("yyyy-MM-dd");

        String formatString;

        DateFormatEnum(String formatString) {
            this.formatString = formatString;
        }

        @Override
        public String toString() {
            return formatString;
        }
    }

    public static String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

}
