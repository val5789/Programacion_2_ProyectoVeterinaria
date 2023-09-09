package com.pets.veterinary_prop2.controller.dto;

import com.pets.veterinary_prop2.Model.City;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VetDTO {
    private String code;
    private String name;
    private Byte age;
    private City city;
    private String code_city;
}//FIN DE LA CLASE VETDTO
