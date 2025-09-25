package com.dev.sbWebapp.lib.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GitHub : https://github.com/ulisesbocchio/jasypt-spring-boot
 */

/**
 * JAVA의 단방향 암호화와 양방향 암복호화를 돕는 라이브러리.
 * 스프링과 연계되어 Properties를 암호화하고 Properties를 가져오는 @Bean 활용에 있어서
 * 암호화되어 있는 Properties 값을 자동으로 복호화하는 편리함을 제공한다.
 */

@Configuration
@EnableEncryptableProperties // 스프링 컨테이너 전체에서 프로퍼티 값을 가져올 때 복호화한 값을 가져온다.
public class JasyptConfig {

    // 비밀키 값은 운영체제 환경설정으로 등록하거나, 어플리케이션 기동시에 VMOption 으로 등록해야한다.
    // 여기서는 편의상 프로퍼티 파일에 저장한다.
    @Value("#{environment['jasypt.encryptor.password']}")
    private String encryptKey;

    final static String ALGORITHM = "PBEWithMD5AndDES";

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey); // 암호화 시 사용하는 키
        config.setAlgorithm(ALGORITHM); // 암호화 알고리즘
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
        config.setPoolSize(1); // 인스턴스 pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.setStringOutputType("base64"); // 인코디 방식

        encryptor.setConfig(config);
        return encryptor;
    }
}
