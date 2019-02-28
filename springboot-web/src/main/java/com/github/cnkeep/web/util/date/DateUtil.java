package com.github.cnkeep.web.util.date;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 日期处理工具类, base on JDK1.8
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/27
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String TIME_PATTERN = "HH:mm:ss";

    private static Map<DateType, DateTimeFormatter> FORMATTER_MAP;

    static {
        FORMATTER_MAP = new HashMap<>(8);
        Arrays.asList(DateType.values())
                .stream()
                .forEach(type -> FORMATTER_MAP.put(type, DateTimeFormatter.ofPattern(type.pattern)));
    }

    @RequiredArgsConstructor
    @AllArgsConstructor
    public enum DateType {
        /**
         * 仅包含日期：yyyy-MM-dd
         */
        DATE("yyyy-MM-dd"),
        /**
         * 仅包含时间：HH:mm:ss
         */
        TIME("HH:mm:ss"),
        /**
         * 包含日期和时间：yyyy-MM-dd HH:mm:ss
         */
        DATE_TIME("yyyy-MM-dd HH:mm:ss"),
        /**
         * 年月：yyyy-MM
         */
        YEAR_MONTH("yyyy-MM"),
        /**
         * 月日：MM:dd
         */
        MONTH_DAY("MM:dd");
        /**
         * 日期格式
         */
        @Getter
        private String pattern;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDay() {
        return LocalDate.now().format(FORMATTER_MAP.get(DateType.DATE));
    }
}
