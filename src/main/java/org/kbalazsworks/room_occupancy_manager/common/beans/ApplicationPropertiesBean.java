package org.kbalazsworks.room_occupancy_manager.common.beans;

import org.kbalazsworks.room_occupancy_manager.common.config.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesBean
{
    @Bean(name = "applicationProperties")
    public ApplicationProperties applicationProperties()
    {
        return new ApplicationProperties();
    }
}
