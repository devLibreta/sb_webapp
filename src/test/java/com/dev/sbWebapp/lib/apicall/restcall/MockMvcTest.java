package com.dev.sbWebapp.lib.apicall.restcall;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dev.sbWebapp.global.config.SecurityTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ApiCallController.class)
@Slf4j
public class MockMvcTest extends SecurityTestConfig {

    // MockMvcTest : spring 내장도구. 서버를 띄우지 않고 컨트롤러(서비스, db 등은 스텁 처리) 테스트.
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // 실제 서비스 빈이 아니라 이 빈으로 교체.
    private ApiCallService apiCallService;

    @Test
    @DisplayName("RestController 에서 get 방식 호출")
    void getTest() throws Exception {
        // void 메서드일 경우, 호출 무시.
        doNothing().when(apiCallService).testWithVoid();
        // return 값이 있는 경우, 대체.
        when(apiCallService.testWithReturn()).thenReturn("Mocked Response");

        mockMvc.perform(get("/apicall/get"))
                .andExpect(status().isOk())
                .andExpect(content().string("ApiCallController.get() Success"));
    }

    @Test
    @DisplayName("RestController 에서 post 방식 호출")
    void postTest() throws Exception {
        // void 메서드일 경우, 호출 무시.
        doNothing().when(apiCallService).testWithVoid();
        // return 값이 있는 경우, 대체.
        when(apiCallService.testWithReturn()).thenReturn("Mocked Response");

        mockMvc.perform(post("/apicall/post")
                        .content("테스트 문자")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("ApiCallController.post() Success : 테스트 문자"));
    }
}
