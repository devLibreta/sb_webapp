package com.dev.sbWebapp.domain.webservice;

import jakarta.xml.ws.soap.SOAPBinding;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyWebServiceTest {

    @LocalServerPort
    private int port; // Spring Boot 랜덤 포트

    private MyWebService client; // 클라이언트
    private Client proxy;

    @BeforeEach
    void setUp() {
        String serviceUrl = "http://localhost:" + port + "/services/MyWebService";

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(MyWebService.class);
        factory.setAddress(serviceUrl);

        // SOAP 1.2 적용, 없으면 SOAP 1.1
        factory.setBindingId(SOAPBinding.SOAP12HTTP_BINDING);

        // 로깅 기능 추가
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true); // 보기 좋게 출력
        factory.getFeatures().add(loggingFeature);

        // 클라이언트 생성
        client = (MyWebService) factory.create();

        // Client 프록시 가져오기
        proxy = ClientProxy.getClient(client);

        // HTTP 요청 헤더 설정
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", Collections.singletonList("Bearer my-token"));
        headers.put("Custom-Header", Collections.singletonList("value"));

        proxy.getRequestContext().put(org.apache.cxf.message.Message.PROTOCOL_HEADERS, headers);

        // SOAP 헤더 생성
        DocumentBuilderFactory factoryDoc = DocumentBuilderFactory.newInstance();
        Element headerElement = null;
        try {
            headerElement = factoryDoc.newDocumentBuilder().newDocument()
                    .createElementNS("http://example.com/headers", "AuthToken");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        headerElement.setTextContent("12345");

        Header soapHeader = new SoapHeader(new QName("http://example.com/headers", "AuthToken"), headerElement);

        // 클라이언트에 헤더 추가
        proxy.getRequestContext().put(Header.HEADER_LIST, Collections.singletonList(soapHeader));

    }

    @Test
    void testHello() {
        String result = client.hello("Alice");
        assertEquals("Hello Alice!", result);
    }
}
