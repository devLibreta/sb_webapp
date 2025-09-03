package com.dev.sbWebapp.domain.webservice;


import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@WebService(
        serviceName = "MyWebService",
        endpointInterface = "com.dev.sbWebapp.domain.webservice.MyWebService",
        targetNamespace = "http://webservice.domain.sbWebapp.dev.com/"
)
@Component
public class MyWebServiceImpl implements MyWebService {

    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
