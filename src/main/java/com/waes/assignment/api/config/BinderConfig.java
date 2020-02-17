package com.waes.assignment.api.config;

import com.waes.assignment.api.binder.DiffBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure Spring Beans and manage dependency injection
 */
@Configuration
public class BinderConfig {

    /**
     *
     * Configure a spring bean responsible for data transformation of a DIFF
     * @return spring bean responsible for data transformation of a DIFF
     */
    @Bean
    public DiffBinder diffBinder(){
        return new DiffBinder();
    }
}
