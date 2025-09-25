package com.dev.sbWebapp.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilLib {
        public static String lpad(String str, int len, String addStr) {
            StringBuilder result = new StringBuilder(str);
            int templen = len - result.length();

            result.append(String.valueOf(addStr).repeat(Math.max(0, templen)));

            return result.toString();
        }

        public static String currentDateTime() {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        }
}
