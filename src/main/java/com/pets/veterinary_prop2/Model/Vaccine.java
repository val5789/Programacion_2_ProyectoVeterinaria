package com.pets.veterinary_prop2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vaccine {
    private City city;
    private Vet vet;
    private Short quantity;
}
