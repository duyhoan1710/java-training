package com.example.schoolmanangement.configuration;

import com.example.schoolmanangement.dto.response.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean("person01")
    public Person getDefaultPerson(){
        return new Person("Jason", List.of("Ha Noi 2"));
    }

    @Bean("person02")
    public Person getDefaultPersonV2(){
        return new Person("Peter", List.of("Ha Noi"));
    }
}
