package org.example.JavaNaumenHW3.Cinema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MovieDatabaseConfig {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<MovieOld> moviesDatabaseImitation() {
        return new ArrayList<>();
    }
}
