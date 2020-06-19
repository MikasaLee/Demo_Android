package com.example.coursedesign.utils;


public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param input
     * @return 为空返回true, 否则false
     */
    public static boolean isEmpty(String input) {
        if (input != null && !input.equals("")) {
            return false;
        }
        return true;
    }

}
