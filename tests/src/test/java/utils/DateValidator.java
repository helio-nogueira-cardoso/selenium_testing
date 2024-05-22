package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    public static boolean isDataInFormatDayMonthYear(String date) {
        String regex = "^(0?[1-9]|[12][0-9]|3[01]) (January|February|March|April|May|June|July|August|September|October|November|December) \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean isDataInFormatMonthDayYear(String date) {
        String regex = "^(January|February|March|April|May|June|July|August|September|October|November|December) (0?[1-9]|[12][0-9]|3[01]), \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static String extractDate(String input) {
        String regex = "This page was last edited on (.+?), at ";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
