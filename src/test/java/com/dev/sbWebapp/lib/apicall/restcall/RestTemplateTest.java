package com.dev.sbWebapp.lib.apicall.restcall;

import com.dev.sbWebapp.global.config.ApiTestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class RestTemplateTest extends ApiTestConfig {
    // 통합테스트용. 실제 서버를 띄워서 호출.

    @MockitoBean // 실제 서비스 빈이 아니라 이 빈으로 교체.
    private ApiCallService apiCallService;

    @Test
    void testGet() {
        // GET 호출
        String url = "http://localhost:" + port + "/apicall/get";

        // 실제 서비스 호출 방지 (Mockito)
        doNothing().when(apiCallService).testWithVoid();
        doReturn("Hello").when(apiCallService).testWithReturn();

        String response = restTemplate.getForObject(url, String.class);
        assertThat(response).isEqualTo("ApiCallController.get() Success");
    }

    @Test
    void testPost() {
        // POST 호출
        String url = "http://localhost:" + port + "/apicall/post";
        String requestBody = "테스트문자";

        // 실제 서비스 호출 방지
        doNothing().when(apiCallService).testWithVoid();
        doReturn("Hello").when(apiCallService).testWithReturn();

        String response = restTemplate.postForObject(url, requestBody, String.class);
        assertThat(response).isEqualTo("ApiCallController.post() Success : 테스트문자");
    }
}
