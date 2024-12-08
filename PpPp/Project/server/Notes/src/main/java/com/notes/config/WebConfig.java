package com.notes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Настраиваем, чтобы любые запросы к /uploads/** раздавались с указанного пути
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:D:/Projects/quicknotes/server/Notes/uploads/images/");
    }
}
