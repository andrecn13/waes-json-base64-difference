package com.waes.assignment.api.config;

import com.waes.assignment.api.binder.DiffBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinderConfig {

    @Bean
    public DiffBinder diffBinder(){
        return new DiffBinder();
    }
}
