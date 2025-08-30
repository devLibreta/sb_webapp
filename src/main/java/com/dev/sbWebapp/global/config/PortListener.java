package com.dev.sbWebapp.global.config;

import com.dev.sbWebapp.global.config.beans.BeanManager;
import com.dev.sbWebapp.global.config.profiles.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    // 참고 Class
    // ServletWebServerFactoryAutoConfiguration : servlet web servers 의 Auto-configuration 을 담당하는 클래스.

    private static final Logger LOGGER = LoggerFactory.getLogger(PortListener.class);
    private final ProfileManager profileManager;
    private final BeanManager beanManager;

    @Autowired
    public PortListener(ProfileManager profileManager, BeanManager beanManager) {
        this.profileManager = profileManager;
        this.beanManager = beanManager;
    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        // 웹앱이 웹서버 서블릿과 연결되는 이벤트 발생 시 처리하는 메서드
        LOGGER.info("[onApplicationEvent] =================> 실행됨");
        LOGGER.debug("BeanNameXXXXXXXXX ");
        printActiveProfiles();
        printBeanNames();
    }


    public void printActiveProfiles() {
        // 활성화된 프로파일 출력(info)
        profileManager.printActiveProfiles();
    }


    private void printBeanNames() {
        // 초기화된 빈 목록 출력(debug)
        beanManager.printContextBeanNames();
    }
}
