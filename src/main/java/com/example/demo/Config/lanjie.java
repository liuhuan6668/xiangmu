package com.example.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.sessioninterceptor.Sessioninterceptor;

@Configuration
public class lanjie implements WebMvcConfigurer {
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 添加拦截的请求，并排除几个不拦截的请求
	        registry.addInterceptor(new Sessioninterceptor())
	        .addPathPatterns("/**")
	        .excludePathPatterns("/jpa/tologin","/jpa/login");
	    }

}
