package com.dev.sbWebapp.global.config;



import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // 웹서버 실행 시 포트 번호를 랜덤으로 바꾼다.
public class ApiTestConfig extends SecurityTestConfig{

    @LocalServerPort // 실행 중인 서버 포트 번호를 주입해 준다.
    protected int port;

    private final String baseUrl = "http://localhost";

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected WebTestClient webTestClient;

    @BeforeEach
    protected void setup(){
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .build()
                .toUriString();

        // restAssured setup
        RestAssured.baseURI = baseUrl;
        RestAssured.port = port;

        // webTestClient setup
        // 실제 서버 포트와 매핑해서 WebTestClient 생성
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(url)
                .build();
    }
}
