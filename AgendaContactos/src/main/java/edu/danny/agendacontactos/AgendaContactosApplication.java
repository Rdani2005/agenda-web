package edu.danny.agendacontactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main class of the App. It contains all Spring Boot initializer methods
 *
 * @author Danny Sequeira
 * @version 1.0
 * @since 2023-03-06
 */
@SpringBootApplication
public class AgendaContactosApplication {

    /**
     * Main method
     */
    public static void main(String[] args) {
        SpringApplication.run(AgendaContactosApplication.class, args);
    }

    /**
     * Configuring the CORS Headers of the APP. Enable localhost:5173 because of React + Vite Frontend
     * 
     * @return Web Configure
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173/")
                        .allowedHeaders("*").allowedMethods("*").exposedHeaders("*");
            }
        };
    }
}
