package com.github.cnkeep.common.util.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码图片
 * Created in 2017-12-25 09:43.
 */
public class VerifyCodeImage {
    private static Logger logger = LoggerFactory.getLogger(VerifyCodeImage.class);

    /**
     * 生成验证码图片
     *
     * @param width        宽度
     * @param height       高度
     * @param outputStream 输出流
     * @param code         验证码
     */
    public void generateImage(int width, int height, OutputStream outputStream, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置边框色
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        // 设置背景色
        g2.setColor(new Color(238, 238, 238));
        g2.fillRect(0, 2, width, height - 4);

        // 绘制干扰线
        this.drawDisturbLine(width, height, g2);

        // 增加噪点
        this.addPointer(width, height, image);

        g2.setColor(getRandColor(50, 200));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 8 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize / 3, height / 4);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((width - 20) / verifySize) * i + 5, height / 2 + fontSize / 2 - 5);
        }

        g2.dispose();
        ImageIO.write(image, "jpg", outputStream);
    }


    /**
     * 扭曲图片
     *
     * @param g      绘图对象
     * @param width  宽度
     * @param height 高度
     * @param color  颜色
     */
    private void shear(Graphics g, int width, int height, Color color) {
        shearX(g, width, height, color);
        shearY(g, width, height, color);
    }

    /**
     * 扭曲X
     *
     * @param g      绘图对象
     * @param width  宽度
     * @param height 高度
     * @param color  颜色
     */
    private static void shearX(Graphics g, int width, int height, Color color) {
        Random random = new Random();
        int period = random.nextInt(2);
        int frames = 1;
        int phase = random.nextInt(2);
        for (int i = 0; i < height; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, width, 1, (int) d, 0);
            g.setColor(color);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + width, i, width, i);
        }

    }

    /**
     * 扭曲Y
     *
     * @param g      绘图对象
     * @param width  宽度
     * @param height 高度
     * @param color  颜色
     */
    private static void shearY(Graphics g, int width, int height, Color color) {
        Random random = new Random();
        int period = random.nextInt(40) + 10;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < width; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, height, 0, (int) d);
            g.setColor(color);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + height, i, height);

        }

    }

    /**
     * 增加噪点
     *
     * @param width  宽度
     * @param height 高度
     * @param image  图片对象
     */
    private void addPointer(int width, int height, BufferedImage image) {
        Random random = new Random();
        float yawpRate = 0.08f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
    }

    /**
     * 获取int值颜色
     *
     * @return
     */
    private int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    /**
     * 生成随机RGB值
     *
     * @return
     */
    private int[] getRandomRgb() {
        Random random = new Random();
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    /**
     * 绘制干扰线
     *
     * @param width  图片长度
     * @param height 图片高度
     * @param g2     绘制对象
     */
    private void drawDisturbLine(int width, int height, Graphics2D g2) {
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
    }

    /**
     * 生成随机颜色
     *
     * @param start 开始色值
     * @param end   结束色值
     * @return
     */
    private Color getRandColor(int start, int end) {
        Random random = new Random();
        if (start > 255) {
            start = 255;
        }
        if (end > 255) {
            end = 255;
        }
        int r = start + random.nextInt(end - start);
        int g = start + random.nextInt(end - start);
        int b = start + random.nextInt(end - start);
        return new Color(r, g, b);
    }
}