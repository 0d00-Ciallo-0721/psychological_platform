package sasu.platform.mhm.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成工具类
 */
public class CaptchaUtil {

    // 验证码字符集（26位大小写英文字母 + 数字0-9）
    private static final char[] CHAR_ARRAY = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final Random RANDOM = new Random();

    /**
     * 生成验证码图片
     * @param code 验证码字符串
     * @param width 图片宽度
     * @param height 图片高度
     * @return 图片字节数组
     * @throws IOException IO异常
     */
    public static byte[] generateCaptchaImage(String code, int width, int height) throws IOException {
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取Graphics2D对象
        Graphics2D g2d = image.createGraphics();

        // 设置背景色为白色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//        // 绘制干扰线
//        drawInterferenceLines(g2d, width, height, 5);
//
//        // 绘制噪点
//        drawNoise(image, 0.02f);

        // 绘制验证码字符
        drawCaptchaCode(g2d, code, width, height);

        // 释放资源
        g2d.dispose();

        // 将图片转换为字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);

        return baos.toByteArray();
    }

    /**
     * 绘制验证码字符
     */
    private static void drawCaptchaCode(Graphics2D g2d, String code, int width, int height) {
        int codeLength = code.length();
        int charWidth = width / (codeLength + 1);

        // 使用多种字体增加难度
        String[] fontNames = {
                "Arial", "Times New Roman", "Courier New", "Verdana", "Georgia"
        };

        // 使用多种颜色
        Color[] colors = {
                Color.BLACK, Color.BLUE, Color.RED, Color.GREEN,
                Color.MAGENTA, Color.ORANGE, Color.DARK_GRAY
        };

        for (int i = 0; i < codeLength; i++) {
            // 随机选择字体
            String fontName = fontNames[RANDOM.nextInt(fontNames.length)];
            int fontSize = 24 + RANDOM.nextInt(8); // 字体大小24-32
            int fontStyle = RANDOM.nextBoolean() ? Font.BOLD : Font.PLAIN;

            // 设置字体
            Font font = new Font(fontName, fontStyle, fontSize);
            g2d.setFont(font);

            // 设置颜色
            g2d.setColor(colors[RANDOM.nextInt(colors.length)]);

            // 计算字符位置（加入随机偏移）
            int x = charWidth * i + 10 + RANDOM.nextInt(10);
            int y = height / 2 + RANDOM.nextInt(height / 4);

            // 旋转字符（增加难度）
            double rotation = (RANDOM.nextDouble() - 0.5) * 0.3; // -0.15到0.15弧度
            g2d.rotate(rotation, x, y);

            // 绘制字符
            g2d.drawString(String.valueOf(code.charAt(i)), x, y);

            // 恢复旋转
            g2d.rotate(-rotation, x, y);
        }
    }

    /**
     * 绘制干扰线
     */
    private static void drawInterferenceLines(Graphics2D g2d, int width, int height, int lineCount) {
        for (int i = 0; i < lineCount; i++) {
            // 随机设置线条颜色（浅色）
            g2d.setColor(new Color(
                    RANDOM.nextInt(200),
                    RANDOM.nextInt(200),
                    RANDOM.nextInt(200)
            ));

            // 设置线条粗细
            g2d.setStroke(new BasicStroke(0.5f + RANDOM.nextFloat()));

            // 随机生成线条的起点和终点
            int x1 = RANDOM.nextInt(width);
            int y1 = RANDOM.nextInt(height);
            int x2 = RANDOM.nextInt(width);
            int y2 = RANDOM.nextInt(height);

            // 绘制线条
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 绘制噪点
     */
    private static void drawNoise(BufferedImage image, float noiseRatio) {
        int width = image.getWidth();
        int height = image.getHeight();
        int noiseCount = (int) (width * height * noiseRatio);

        for (int i = 0; i < noiseCount; i++) {
            int x = RANDOM.nextInt(width);
            int y = RANDOM.nextInt(height);
            int rgb = image.getRGB(x, y);

            // 随机改变像素颜色
            Color color = new Color(rgb);
            Color newColor = new Color(
                    255 - color.getRed(),
                    255 - color.getGreen(),
                    255 - color.getBlue()
            );

            image.setRGB(x, y, newColor.getRGB());
        }
    }

    /**
     * 生成随机验证码字符串
     * @param length 验证码长度
     * @return 验证码字符串
     */
    public static String generateCaptchaCode(int length) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(CHAR_ARRAY[RANDOM.nextInt(CHAR_ARRAY.length)]);
        }
        return code.toString();
    }

    /**
     * 生成数学表达式验证码（可选功能）
     * @return [表达式, 结果]
     */
    public static String[] generateMathCaptcha() {
        int num1 = RANDOM.nextInt(10) + 1;
        int num2 = RANDOM.nextInt(10) + 1;
        int operator = RANDOM.nextInt(3); // 0:+, 1:-, 2:*

        String expression;
        int result;

        switch (operator) {
            case 0:
                expression = num1 + " + " + num2;
                result = num1 + num2;
                break;
            case 1:
                if (num1 < num2) {
                    // 确保结果为正数
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                expression = num1 + " - " + num2;
                result = num1 - num2;
                break;
            case 2:
                expression = num1 + " × " + num2;
                result = num1 * num2;
                break;
            default:
                expression = num1 + " + " + num2;
                result = num1 + num2;
        }

        return new String[]{expression, String.valueOf(result)};
    }
}
