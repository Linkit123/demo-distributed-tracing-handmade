package com.dvtt.demo.coredemo.config;

import com.dvtt.demo.coredemo.filter.CustomURLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by linhtn on 1/3/2022.
 */
@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public FilterRegistrationBean<CustomURLFilter> filterRegistrationBean() {
        var registrationBean = new FilterRegistrationBean<CustomURLFilter>();
        var customURLFilter = new CustomURLFilter();

        registrationBean.setFilter(customURLFilter);
        registrationBean.setOrder(Integer.MAX_VALUE); //set precedence
        return registrationBean;
    }

}
