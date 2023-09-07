package com.pets.veterinary_prop2.service;

import com.pets.veterinary_prop2.Model.City;
import com.pets.veterinary_prop2.Model.Vaccine;
import com.pets.veterinary_prop2.Model.Vet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class VaccineService {
    private List<Vet> vets;
    private List<City> cities;
    private List<Vaccine> vaccines;
}//FIN DE SERVICE VACCINE
