package com.pets.veterinary_prop2.controller;
import com.pets.veterinary_prop2.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/vaccine")
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;
}//FIN DEL CONTROLLER VACCINE
