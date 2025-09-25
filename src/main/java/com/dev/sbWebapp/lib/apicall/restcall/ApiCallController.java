package com.dev.sbWebapp.lib.apicall.restcall;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/apicall")
@RequiredArgsConstructor
public class ApiCallController {

    private final ApiCallService apiCallService;

    @GetMapping("/get")
    public String get(){
        apiCallService.testWithVoid();
        apiCallService.testWithReturn();

        return "ApiCallController.get() Success";
    }

    @PostMapping("/post")
    public String post(@RequestBody String str){
        apiCallService.testWithVoid();
        apiCallService.testWithReturn();
        return "ApiCallController.post() Success : " + str;
    }

    /* path variables */
    @GetMapping("/pathParam/{userName}")
    public String pathParam(@PathVariable final String userName){
        String str = userName;
        return str;
    }

    @GetMapping("/pathParams/{userName}/{userSex}")
    public String multiPathParam(
            @PathVariable final String userName,
            @PathVariable final String userSex){
        String str = userName+", "+userSex;
        return str;
    }

    /* query parameters */

    @GetMapping("/queryParameter")
    public String queryParameter(
            @RequestParam final String userName){
        String str = userName;
        return str;
    }

    @GetMapping("/queryParameters")
    public String queryParameters(
            @RequestParam final String userName,
            @RequestParam final String userSex){
        String str = userName+", "+userSex;
        return str;
    }

    /* form parameters */

    @PostMapping(value = "/formParameters", consumes = "application/x-www-form-urlencoded")
    public Map<String,String> formParameters(
            @RequestParam final Map<String,String> params){ // form data 는 @RequestParam으로 받아야한다.
        System.out.println("===============성공");
        return params;
    }

    /* setting headers */

    @GetMapping("/header")
    public String header(@RequestHeader(value = "User-Agent") final String userAgent ){
        return userAgent;
    }

    @GetMapping("/headerValues")
    public MultiValueMap<String,String> headers(@RequestHeader final MultiValueMap<String,String> headers ){
        return headers;
    }

    @GetMapping("/headers")
    public Map<String,String> headers(@RequestHeader final Map<String,String> headers ){
        return headers;
    }

    /* adding cookies */

    @GetMapping("/cookies")
    public Cookie[] cookies(HttpServletRequest req, @CookieValue("ck1") final String cookies){
        Cookie[] reqCookies = req.getCookies();
        return reqCookies;
    }

}
