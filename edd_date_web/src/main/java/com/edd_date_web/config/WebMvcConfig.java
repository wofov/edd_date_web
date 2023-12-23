package com.edd_date_web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RequestLimitInterceptor requestLimitInterceptor;

    public WebMvcConfig(RequestLimitInterceptor requestLimitInterceptor) {
        this.requestLimitInterceptor = requestLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add the RequestLimitInterceptor to the registry
        registry.addInterceptor(requestLimitInterceptor);
    }
}