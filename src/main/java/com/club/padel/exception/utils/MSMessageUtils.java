package com.club.padel.exception.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSMessageUtils {
    protected static String PATTERN = "(^[a-zA-Z]{3}\\d{4})#(.*)$";
    public static int CODE_LIST_POSITION = 0;
    public static int DETAILS_LIST_POSITION = 1;

    public static String getMessageCode(String propertyMessage) {
        List<String> result = getCodeAndDetails(propertyMessage);
        if (!result.isEmpty()) {
            return result.get(CODE_LIST_POSITION);
        }
        return null;
    }

    public static String getMessageDetails(String propertyMessage) {
        List<String> result = getCodeAndDetails(propertyMessage);
        if (!result.isEmpty()) {
            return result.get(DETAILS_LIST_POSITION);
        }
        return null;
    }

    public static List<String> getCodeAndDetails(String propertyMessage) {
        List<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(propertyMessage);
        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                result.add(CODE_LIST_POSITION, matcher.group(1));
                result.add(DETAILS_LIST_POSITION, matcher.group(2));
                return result;
            }
        }
        return result;
    }
}
