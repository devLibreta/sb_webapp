package com.dev.sbWebapp.lib.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/jasypt")
@RestController
public class JasyptController {

    /**
     * jasypt 가 프로퍼티에 등록된 인코딩된 정보를 조회할 때는 디코딩된 채로 가져오는지 확인하는 api.
     */

    @Value("#{environment['example.id']}") // As of spring 3.0, SpEl conversion
    private String encryptId;
    @Value("#{environment['example.pwd']}") // @Value need spring container.
    private String encryptPwd;
    @Value("#{environment['jasypt.encryptor.password']}")
    private String encryptKey;

    @GetMapping(value = "/property")
    public Map<String, Object> property(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", encryptId);
        map.put("pwd", encryptPwd);
        map.put("key", encryptKey);
        return map;
    }
}
