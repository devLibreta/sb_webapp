package com.dev.sbWebapp.lib.apicall.wsCall;


import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

@WebService(
        serviceName = "MyWebService",
        endpointInterface = "com.dev.sbWebapp.lib.apicall.wsCall.MyWebService",
        targetNamespace = "http://webservice.domain.sbWebapp.dev.com/"
)
@Component
public class MyWebServiceImpl implements MyWebService {

    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
