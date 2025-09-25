package com.dev.sbWebapp.lib.apicall.wsCall;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MyWebService {

    @WebMethod
    String hello(@WebParam(name = "name") String name);
}
