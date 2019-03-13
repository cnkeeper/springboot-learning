package com.github.cnkeep.common.util.file;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @Description: 文件操作工具类
 * @Author <a href="hw86xll@163.com">Wei.Huang</a>
 * @Date 2012-9-13
 * @Version 1.0.0
 */
public class FileUtil {

    public static final FastDateFormat fileFormat = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");

    final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    static {
        /**
         * ==========  image =========
         */
        // JPEG (jpg)
        FILE_TYPE_MAP.put("jpg", "FFD8FF");
        // JPEG (jpg)
        FILE_TYPE_MAP.put("jpeg", "FFD8FF");
        // PNG (png)
        FILE_TYPE_MAP.put("png", "89504E47");
        // GIF (gif)
        FILE_TYPE_MAP.put("gif", "47494638");
        // Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("bmp", "424D");

        /**
         * ========== document =========
         */
        // MS Word
        FILE_TYPE_MAP.put("xls", "D0CF11E0");
        // MS Excel 2007+
        FILE_TYPE_MAP.put("xlsx", "504B03");
        // MS Excel (csv)
        FILE_TYPE_MAP.put("csv", "EFBBBF");

        /**
         * =========== audio ============
         */
        // AMR
        FILE_TYPE_MAP.put("amr", "2321414D");
        // MIDI (mid)
        FILE_TYPE_MAP.put("mid", "4D546864");
    }

    /**
     * 根据文件名和文件内容检查文件的有效性， 如果文件名和文件内容格式不一致，则判断为非法文件
     *
     * @param fileName
     * @param bytes
     * @return
     */
    public static boolean isValidFile(String fileName, byte[] bytes) {
        String fileType = FileUtil.getTypeName(fileName);
        // txt/csv文件没有文件头
        if ("txt".equals(fileType) || "csv".equals(fileType)) {
            return true;
        }

        String fileContentFragment = FILE_TYPE_MAP.get(fileType);
        if (StringUtils.isNotBlank(fileContentFragment)) {
            String fileContent = getFileHexString(bytes);
            return fileContent.toUpperCase().startsWith(fileContentFragment);
        }
        return false;
    }

    /**
     * 获取文件头格式
     *
     * @param b
     * @return
     */
    private final static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件后缀,不带小数点
     *
     * @param fileName
     * @return
     */
    private static String getTypeName(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx < 0) {
            return "";
        }
        return fileName.substring(idx + 1);
    }

    /**
     * 获取文件类型
     *
     * @param fileName
     * @return
     */
    public static FileType getFileType(String fileName) {
        return FileType.getType(fileName);
    }

    /**
     * 获取文件后缀,第一位是小数点
     *
     * @param fileName
     * @return
     */
    public static String getType(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx < 0) {
            return "";
        }
        return fileName.substring(idx);
    }

    /**
     * 获取文件名字，去掉文件后缀
     *
     * @param fileName
     * @return
     */
    public static String subFileName(String fileName) {
        if (StringUtils.isBlank(fileName))
            throw new NullPointerException("File name is null");
        int idx = fileName.lastIndexOf('.');
        if (idx < 0) {
            return "";
        }
        return fileName.substring(0, idx);
    }

    /**
     * 创建一个临时文件
     *
     * @param rootFolder
     * @param fileName   为空则自动生成UUID
     * @return
     */
    public static File createTmpFile(String rootFolder, String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sb = new StringBuffer();
        sb.append("temp");
        sb.append("/");
        sb.append(sdf.format(new Date()));
        sb.append("/");

        String folder = rootFolder + sb.toString();
        File temp = new File(folder);
        if (!temp.exists()) {
            boolean mkdirsSuccess = temp.mkdirs();
            throw new RuntimeException("Fail to create dir"+temp);
        }

        if (StringUtils.isBlank(fileName)) {
            fileName = UUID.randomUUID().toString();
        }

        File file = new File(folder + fileName);
        return file;
    }


    public static String getCharset(File file) {
        String code = "GBK";
        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] head = new byte[3];
            fis.read(head);
            code = getCharset(head);
        } catch (Exception e) {
            //
            e.printStackTrace();
        }
        return code;
    }

    public static String getCharset(byte[] head) {
        String code = "GBK";
        if (head[0] == (byte) 0xFF && head[1] == (byte) 0xFE) {
            code = "UTF-16LE";
        } else if (head[0] == (byte) 0xFE && head[1] == (byte) 0xFF) {
            code = "UTF-16BE";
        } else if (head[0] == (byte) 0xEF && head[1] == (byte) 0xBB && head[2] == (byte) 0xBF) {
            code = "UTF-8";
        }
        return code;
    }
}
