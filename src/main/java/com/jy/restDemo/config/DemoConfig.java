package com.jy.restDemo.config;

import com.jy.restDemo.RestDemoApplication;
import com.jy.restDemo.service.DataService;
import com.jy.restDemo.service.impl.DataServiceImpl;
import com.jy.restDemo.utils.DataUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = RestDemoApplication.class)
public class DemoConfig {

    @Bean
    public DataService dataService(DataUtils dataUtils) { return new DataServiceImpl(dataUtils); }

    @Bean
    public DataUtils dataUtils() { return new DataUtils(); }
}
