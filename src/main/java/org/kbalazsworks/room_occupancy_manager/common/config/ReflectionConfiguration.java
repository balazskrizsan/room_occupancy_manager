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
            ;
        }
    }
}
