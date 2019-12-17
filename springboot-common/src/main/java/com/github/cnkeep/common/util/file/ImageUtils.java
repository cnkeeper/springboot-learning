package com.github.cnkeep.common.util.file;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 描述: 图片工具</br>
 * * 获取图片的真实类型</br>
 * * 获取图片的宽高
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/03/12
 */
public class ImageUtils {

    public static final String[] IMAGES_SUFFIX = {"jpg", "jpg", "jpeg", "gif", "png"};

    private static final byte[] jpg = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 0x00, 0x10};

    private static final byte[] jpg1 = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xDB};

    private static final byte[] jpeg = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE1};

    private static final byte[] gif = {0x47, 0x49, 0x46, 0x38, 0x39, 0x61};

    private static final byte[] png = {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A};

    private static final byte[][] imageMimeType = {jpg, jpg1, jpeg, gif, png};


    /**
     * Byte数组转换成图片对象
     *
     * @param buf
     * @return
     * @throws IOException
     */
    public static BufferedImage getImage(byte[] buf) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        return ImageIO.read(in);
    }

    /**
     * 通过检查图片文件的格式来获取图片文件的真实后缀。可能为null
     *
     * @param buf
     * @return
     */
    public static String getSuffix(byte[] buf) {
        if (buf.length < 4)
            return null;

        String suffix = null;
        for (int i = 0; i < imageMimeType.length; i++) {
            boolean found = true;
            for (int j = 0; j < imageMimeType[i].length; j++) {
                if (imageMimeType[i][j] != buf[j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                suffix = IMAGES_SUFFIX[i];
                break;
            }
        }

        return suffix;
    }

    /**
     * 图片文件处理为Image
     *
     * @param srcfile
     * @return
     */
    public static BufferedImage getImage(File srcfile) {
        BufferedImage src = null;
        try {
            src = ImageIO.read(srcfile);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return src;
    }
}
