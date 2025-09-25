package com.dev.sbWebapp.lib.apicall.restcall;

import com.dev.sbWebapp.global.config.ApiTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.*;

public class WebTestClientTest extends ApiTestConfig {
    // spring webFlux or rest api test
    // 비동기/논블로킹 환경 테스트.

    @MockitoBean
    private ApiCallService apiCallService;

    @Test
    void testGet() {
        // 서비스 메서드 무시
        doNothing().when(apiCallService).testWithVoid();
        doReturn("Hello").when(apiCallService).testWithReturn();

        webTestClient.get()
                .uri("/apicall/get")
                .exchange() // 실제 호출
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("ApiCallController.get() Success");
    }

    @Test
    void testPost() {
        String requestBody = "테스트문자";

        doNothing().when(apiCallService).testWithVoid();
        doReturn("Hello").when(apiCallService).testWithReturn();

        webTestClient.post()
                .uri("/apicall/post")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("ApiCallController.post() Success : 테스트문자");
    }
}
