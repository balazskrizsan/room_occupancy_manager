package org.kbalazsworks.room_occupancy_manager.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ApplicationProperties
{
    @Value("${server.port}")
    private String serverPort;

    @Value("${native.reflection-configuration-generator.enabled}")
    private boolean nativeReflectionConfigurationGeneratorEnabled;
}
