package com.example.schoolmanangement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Component("person")
public class Person {
    private String name;
    private List<String> address;

    public Person(){
        this.name = "David";
        this.address = List.of("Phu Yen");
    }
}
