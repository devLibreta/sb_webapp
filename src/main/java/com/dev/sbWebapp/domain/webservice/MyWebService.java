package com.dev.sbWebapp.domain.webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MyWebService {

    @WebMethod
    String hello(@WebParam(name = "name") String name);
}
