package com.pets.veterinary_prop2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vet {
    private String code;
    private String name;
    private Byte age;
    private City city;

}
