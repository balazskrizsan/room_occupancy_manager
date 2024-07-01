package org.kbalazsworks.room_occupancy_manager.common.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kbalazsworks.kbalazsworks_common.io_module.services.FileService;
import org.kbalazsworks.kbalazsworks_common.native_build_module.exceptions.RuntimeHintsReflection;
import org.kbalazsworks.kbalazsworks_common.native_build_module.services.RuntimeHintsReflectionGenerator;
import org.kbalazsworks.kbalazsworks_common.templating_module.services.MustacheService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ReflectionConfigurationGenerator
{
    private final ApplicationProperties applicationProperties;

    @PostConstruct
    public void generate() throws RuntimeHintsReflection, IOException
    {
        boolean isNativeReflectionConfigurationGeneratorEnabled = applicationProperties
            .isNativeReflectionConfigurationGeneratorEnabled();

        log.info("ReflectionConfigurationGenerator status: {}", isNativeReflectionConfigurationGeneratorEnabled);

        if (!isNativeReflectionConfigurationGeneratorEnabled)
        {
            return;
        }

        new RuntimeHintsReflectionGenerator(new MustacheService(), new FileService())
            .generate(
                "src/main/java/org/kbalazsworks/room_occupancy_manager/common/config/ReflectionConfiguration.java",
                List.of(
                    "org.kbalazsworks.room_occupancy_manager.api.requests.room_manager",
                    "org.kbalazsworks.room_occupancy_manager.api.responses.room_manager",
                    "org.kbalazsworks.room_occupancy_manager.api.value_objects",
                    "org.kbalazsworks.room_occupancy_manager.domain.entities",
                    "org.kbalazsworks.room_occupancy_manager.domain.value_objects"
                ),
                List.of(
                    ResponseEntity.class
                )
            );
    }
}
