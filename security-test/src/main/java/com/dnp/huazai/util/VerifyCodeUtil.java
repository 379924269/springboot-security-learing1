package com.dnp.huazai.util;

import com.dnp.huazai.constant.KaptchaConst;
import com.dnp.huazai.exception.BusinessException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author huazai
 * @description 验证码工具
 * @date 2019/9/16
 */
@Slf4j
public class VerifyCodeUtil {
    /**
     * description: 输出验证码
     *
     * @param resp    : HttpServletResponse
     * @param req     : HttpServletRequest
     * @param capText : 验证码
     * @param config  : 验证码配置，可以通过VerifyCodeUtil.getConfig获得
     */
    public static void outputVerifyImage(HttpServletResponse resp, HttpServletRequest req, String capText, Config config) {
        resp.setDateHeader("Expires", 0L);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");

        DefaultKaptcha producer = new DefaultKaptcha();
        producer.setConfig(config);

        req.getSession().setAttribute(KaptchaConst.KAPTCHA_SESSION_KEY, capText);
        req.getSession().setAttribute(KaptchaConst.SESSION_KAPTCH_CODE_KEY_CREATE_TIME, System.currentTimeMillis());
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out;
        try {
            out = resp.getOutputStream();
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            log.error("getVerficationCode exception errorMsg:{}", e.getLocalizedMessage());
            throw new BusinessException("生成验证码异常");
        }
    }


    public static String getVerifyCode() {
        DefaultKaptcha producer = new DefaultKaptcha();
        producer.setConfig(getConfig());
        return producer.createText();
    }

    public static Config getConfig() {
        return getConfig(200, 50, 40, "black", 4);
    }

    private static Config getConfig(int width, int height, int fontSize, String fontColor, int kaptchLength) {
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", fontColor);
        // 图片宽
        properties.setProperty("kaptcha.image.width", String.valueOf(width));
        // 图片高
        properties.setProperty("kaptcha.image.height", String.valueOf(height));
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", String.valueOf(fontSize));
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", String.valueOf(kaptchLength));
//        存放的字符串
        properties.setProperty("kaptcha.textproducer.char.string","acdefhkmnprtwxy2345678");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        return new Config(properties);
    }
}
