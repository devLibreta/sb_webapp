package com.dev.sbWebapp.domain.webservice;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.soap.SOAPBinding;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyWebServiceConfig {

    private final Bus bus;

    private final MyWebServiceImpl myWebService;

    @Bean
    public Endpoint soap12Endpoint(MyWebServiceImpl implementor) {
        // SOAP 1.2 바인딩으로 엔드포인트 생성
        Endpoint endpoint = Endpoint.create(SOAPBinding.SOAP12HTTP_BINDING, implementor);

        // 경로 지정 (서블릿 컨텍스트 기준)
        endpoint.publish("/MyWebService");

        // 로깅 기능 추가
        LoggingFeature logging = new LoggingFeature();
        logging.setPrettyLogging(true);
        ((org.apache.cxf.jaxws.EndpointImpl) endpoint).getFeatures().add(logging);

        return endpoint;
    }
}
