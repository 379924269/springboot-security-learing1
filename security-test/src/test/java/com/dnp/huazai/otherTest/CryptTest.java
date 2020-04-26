package com.dnp.huazai.otherTest;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author huazai
 * @description 测试一下spring的密码加密
 * @date 2020/4/2
 */
public class CryptTest {

    @Test
    public void passwordCrypt() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String cryptPassword = passwordEncoder.encode("123456");
        System.out.println("cryptPassword = " + cryptPassword);
    }
}
