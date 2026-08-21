package com.example.med_spring_project.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class AppConfig {


    /*
     * Configure le moteur de templates Thymeleaf.
     * Il recherche les fichiers HTML dans le dossier
     * src/main/resources/templates/
     */

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // To render ou template
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    /*
     * Configure ModelMapper pour faciliter la conversion
     * entre les objets Java (DTO, Entity, etc.).
     * Le mapping des champs privés est activé.
     */

    @Bean
    public ModelMapper modelMapper(){
        //Creating the object of modelMapper
        ModelMapper modelMapper = new ModelMapper();
        //Convert to the class that match
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
