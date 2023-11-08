package cz.uhk.mc.config;

import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;
import jakarta.inject.Singleton;

import java.time.format.DateTimeFormatter;

@Factory
public class AppConfig {
    @Singleton
    DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
    }

    @Singleton
    public MessageSource messageSource() {
        return new ResourceBundleMessageSource("messages");
    }

}
