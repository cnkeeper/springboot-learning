package com.github.cnkeep.common.util.file;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 描述: 获取真实请求来源地址
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/03/12
 */
public enum FileType {

    /**
     * .xls
     */
    Excel(".xls"),
    /**
     * .xlsx
     */
    ExcelX(".xlsx"),
    /**
     * .txt
     */
    Text(".txt"),
     /**
     * .csv
     */
    Csv(".csv"),
     /**
     * .zip
     */
    Zip(".zip"),
     /**
     * .bwf
     */
    Bwf(".bwf"),
    /**
     * .*
     */
    Media(".*");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    /**
     * 获取文件类型
     *
     * @return
     */
    public String getType() {
        return type;
    }

    private final static Map<String, FileType> FILE_TYPE_MAP = new HashMap<>(8);
    static {
        Stream.of(FileType.values()).forEach(fileType -> FILE_TYPE_MAP.put(fileType.type,fileType));
    }

    /**
     * 获取文件类型的枚举类型
     * @param fileName 文件名
     * @return
     */
    public static FileType getType(String fileName) {
        String typeSuffix = fileName.substring(fileName.lastIndexOf("."));
        return FILE_TYPE_MAP.getOrDefault(typeSuffix.toLowerCase(),Media);
    }
}
