package com.club.padel;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/club-padel/**") // Especifica el path de tu API
                .allowedOrigins("*") // Permite el acceso desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Cabeceras permitidas
        registry.addMapping("/club-padel/reservas/**") // Especifica el path de tu API
        .allowedOrigins("*") // Permite el acceso desde este origen
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
        .allowedHeaders("*"); // Cabeceras permitidas
        registry.addMapping("/club-padel/roles/**") // Especifica el path de tu API
        .allowedOrigins("*") // Permite el acceso desde este origen
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
        .allowedHeaders("*"); // Cabeceras permitidas
        registry.addMapping("/club-padel/usuarios/**") // Especifica el path de tu API
        .allowedOrigins("*") // Permite el acceso desde este origen
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
        .allowedHeaders("*"); // Cabeceras permitidas
    }
}
