package com.dev.sbWebapp.lib.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class JasyptTest {

    // jasypt 빈 : jasyptStringEncryptor(개발용), lazyJasyptStringEncryptor(운영용, lazy 방식)
    // lazyJasyptStringEncryptor 도 같이 등록되기에 @Qualifier 가 필요하다.
    @Qualifier("jasyptStringEncryptor")
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    @DisplayName("jasypt encrypt,decrypt 테스트")
    void encryptAndDecryptTest(){
        // given
        String id = "암호화 전 아이디";
        String pwd = "암호화 전 비밀번호";

        // when
        // 암호화
        String encryptId = stringEncryptor.encrypt(id);
        String encryptPwd = stringEncryptor.encrypt(pwd);
        // 복호화
        String decryptId = stringEncryptor.decrypt(encryptId);
        String decryptPwd = stringEncryptor.decrypt(encryptPwd);

        // then
        Assertions.assertEquals("암호화 전 아이디", decryptId);
        Assertions.assertEquals("암호화 전 비밀번호", decryptPwd);
    }
}