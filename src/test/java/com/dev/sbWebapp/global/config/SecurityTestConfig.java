package com.dev.sbWebapp.global.config;

import com.dev.sbWebapp.global.config.security.SecurityConfigLocal;
import org.springframework.context.annotation.Import;

@Import(SecurityConfigLocal.class) // Security 설정 직접 import
public class SecurityTestConfig {

}
