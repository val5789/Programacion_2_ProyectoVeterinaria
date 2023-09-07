package com.pets.veterinary_prop2.controller;

import com.pets.veterinary_prop2.Model.Vet;
import com.pets.veterinary_prop2.controller.dto.ResponseDTO;
import com.pets.veterinary_prop2.exceptions.VeterinaryException;
import com.pets.veterinary_prop2.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping(path="/vet")
@RestController
public class VetController {
    @Autowired
    private VetService vetService;

    @GetMapping(path = "/vets")
    public ResponseEntity<ResponseDTO> getAllVets(){
        return  new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        vetService.getVets(),
                null),
        HttpStatus.OK);
    }//fin del metodo vet

    @GetMapping(path = "/vets/{code}")
    public ResponseEntity<ResponseDTO>getVetByCode(@PathVariable String code){
        try {
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.OK.value(),
                            vetService.findVetByCode(code),
                            null),
                    HttpStatus.OK);
        }//fin del try
        catch (VeterinaryException e){
            List<String> errors=new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                            null, errors),
                    HttpStatus.OK);

        }//fin del catch

    }//fin de la clase encontar por codigo

    @GetMapping(path="/vets/name/{name}")
    public ResponseEntity<ResponseDTO>getVetByName(@PathVariable String name){
        if (name!= null && !name.isEmpty()){
            try {
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.OK.value(),
                                vetService.findVetByName(name),
                                null),
                        HttpStatus.OK);
            }//fin del try
            catch (VeterinaryException e){
                List<String> errors=new ArrayList<>();
                errors.add(e.getMessage());
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                                null,
                                errors),
                        HttpStatus.OK);

            }//fin del catch

        }//fin del if

        else {
            List<String>errors = new ArrayList<>();
            errors.add("La descripcion no puede ser vacia");
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                            null,
                            errors),
                    HttpStatus.OK);
            //fin del return


        }

    }//FIN DEL METODO LISTA

    @GetMapping(path = "vets/by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> getVetsByInitialLetter(
            @PathVariable char letter
    ){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        vetService.findVetByInitialLetter(letter),
                        null),
                HttpStatus.OK);
    }


    @GetMapping(path = "vets/age/{age}")
    public ResponseEntity<?> findVetByAge(@PathVariable byte age) {
        try {
            Optional<Vet> vet = vetService.findVetByAge(age);
            if (vet.isPresent()) {
                return new ResponseEntity<>(vet.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontró ningún veterinario con la edad especificada", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/vets/minimum_age")
    public ResponseEntity<ResponseDTO> getVetsWithMinimumAge() {
        List<Vet> vetsWithMinimumAge = vetService.findVetsWithMinimumAge();

        if (vetsWithMinimumAge.isEmpty()) {
            List<String> errors = new ArrayList<>();
            errors.add("No se encontraron veteriarios");
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                            null, errors),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(), vetsWithMinimumAge,
                        null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/vets/age_range/{min}/{max}")
    public ResponseEntity<ResponseDTO> getVetsInAgeRange(
            @PathVariable byte min,
            @PathVariable byte max
    ) {
        if (min > max) {
            List<String> errors = new ArrayList<>();
            errors.add("Rango de edad ivalido");
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, errors),
                    HttpStatus.OK);
        }

        List<Vet> vetsInAgeRange = vetService.findVetsBetweenAges(min, max);

        if (vetsInAgeRange.isEmpty()) {
            List<String> errors = new ArrayList<>();
            errors.add("No hay veterinarios en ese rango de edad");
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(), null, errors),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(), vetsInAgeRange, null),
                HttpStatus.OK);
    }

}//FIN DEL CONTROLLER
