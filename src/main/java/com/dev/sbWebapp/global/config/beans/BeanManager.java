package com.dev.sbWebapp.global.config.beans;

import com.dev.sbWebapp.global.log.LogExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanManager.class);
    private final ApplicationContext applicationContext;

    @Autowired
    public BeanManager(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
    }

    @LogExecution
    public void printContextBeanNames(){
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        LOGGER.info("Initialized Beans size : {} ", beanNames.length);
        for(String beanName : beanNames){
            LOGGER.debug("BeanName : {}", beanName);
        }
    }
}
