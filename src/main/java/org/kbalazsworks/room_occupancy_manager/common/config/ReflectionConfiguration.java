package org.kbalazsworks.room_occupancy_manager.common.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

import static org.springframework.aot.hint.MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS;
import static org.springframework.aot.hint.MemberCategory.INVOKE_PUBLIC_METHODS;
import static org.springframework.aot.hint.MemberCategory.PUBLIC_FIELDS;

@Configuration
@RegisterReflectionForBinding({
    org.springframework.http.ResponseEntity.class,
    org.kbalazsworks.room_occupancy_manager.api.requests.room_manager.PostBookRequest.class,
    org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse.class,
    org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails.class,
    org.kbalazsworks.room_occupancy_manager.api.value_objects.ResponseData.class,
    org.kbalazsworks.room_occupancy_manager.domain.entities.Book.class,
    org.kbalazsworks.room_occupancy_manager.domain.value_objects.RoomTypePrices.class,
})
@ImportRuntimeHints(ReflectionConfiguration.AppRuntimeHintsRegistrar.class)
public class ReflectionConfiguration
{
    public static class AppRuntimeHintsRegistrar implements RuntimeHintsRegistrar
    {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader)
        {
            hints.reflection()
                .registerType(org.springframework.http.ResponseEntity.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.api.requests.room_manager.PostBookRequest.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.api.value_objects.ResponseData.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.domain.entities.Book.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
                .registerType(org.kbalazsworks.room_occupancy_manager.domain.value_objects.RoomTypePrices.class, PUBLIC_FIELDS, INVOKE_PUBLIC_METHODS, INVOKE_PUBLIC_CONSTRUCTORS)
            ;
        }
    }
}
