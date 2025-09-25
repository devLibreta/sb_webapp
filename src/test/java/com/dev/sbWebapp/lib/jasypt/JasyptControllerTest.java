package com.dev.sbWebapp.lib.jasypt;

import com.dev.sbWebapp.global.config.ApiTestConfig;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * restassurred 라이브러리를 활용한 jasypt api 테스트
 */

@Slf4j
class JasyptControllerTest extends ApiTestConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(JasyptControllerTest.class.getName());

    @Test
    @DisplayName("@EnableEncryptableProperties 테스트")
    // jasypt 라이브러리가 인코딩 프로퍼티를 자동 디코딩해주는지 테스트
    void jasyptTest(){
        ExtractableResponse<Response> res =
            RestAssured
                .given().log().all()
                .when().get("/jasypt/property")
                .then().log().all()
                .extract();
        assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(ContentType.fromContentType(res.contentType())).isEqualTo(ContentType.JSON);
        assertThat(res.body().jsonPath().get("id").toString()).isEqualTo("암호화 전 아이디");
        assertThat(res.body().jsonPath().get("pwd").toString()).isEqualTo("암호화 전 비밀번호");
        assertThat(res.body().jsonPath().get("key").toString()).isEqualTo("devrun");

    }
}