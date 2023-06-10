package com.example.jl_entities.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS configuration to all endpoints
                .allowedOrigins("http://example.com", "http://localhost:4200","https://spectacular-semolina-e6fee9.netlify.app","https://hamza--genuine-gumdrop-b40ee4.netlify.app/","https://strong-cendol-d5d500.netlify.app/","https://agent-app-jabak-lah.netlify.app/","https://main--keen-tartufo-6e47ae.netlify.app/") // Specify your allowed origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed HTTP methods
                .allowedHeaders("*"); // Specify allowed headers
    }
}
