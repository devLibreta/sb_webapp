package com.dev.sbWebapp.global.config.profiles;

import com.dev.sbWebapp.global.log.LogExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileManager.class);
    private final Environment environment;

    @Autowired
    public ProfileManager(Environment environment) {
        this.environment = environment;
    }

    @LogExecution
    public void printActiveProfiles(){
        String[] profiles = environment.getActiveProfiles();

        LOGGER.info("Active Profiles size : {} ", profiles.length);
        for(String profile : profiles){
            LOGGER.info("Current Applied Profile : {}", profile);
        }
    }

}
