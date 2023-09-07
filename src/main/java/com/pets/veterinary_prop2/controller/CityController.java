package com.pets.veterinary_prop2.controller;

import com.pets.veterinary_prop2.Model.City;
import com.pets.veterinary_prop2.controller.dto.ResponseDTO;
import com.pets.veterinary_prop2.exceptions.VeterinaryException;
import com.pets.veterinary_prop2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<ResponseDTO>getAllCities(){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        cityService.getCities(),
                        null),
                HttpStatus.OK);

    }//FIN DEL METODO LISTA

    @GetMapping(path="/{id}")
    public ResponseEntity<ResponseDTO>getCityById(@PathVariable String id){
        try {
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.OK.value(),
                            cityService.findCityById(id),
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
    }//FIN DEL METODO LISTA

    @GetMapping(path="/description/{description}")
    public ResponseEntity<ResponseDTO>getCityByDescription(@PathVariable String description){
        if (description!= null && !description.equals("")){
            try {
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.OK.value(),
                                cityService.findCityByDescription(description),
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

    @GetMapping(path = "/by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByInitialLetter(
            @PathVariable char letter
    ){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        cityService.findCityByInitialLetter(letter),
                        null),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCity(
            @RequestBody City city){
        try {

            return new ResponseEntity<>(
                    new ResponseDTO(
                            HttpStatus.OK.value(),
                            cityService.addCity(city),
                            null
                    ), HttpStatus.OK
            );
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.CONFLICT.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO> updateCity(
            @PathVariable String id, @RequestBody City city){
        try {

            return new ResponseEntity<>(
                    new ResponseDTO(
                            HttpStatus.OK.value(),
                            cityService.updateCity(id,city),
                            null
                    ), HttpStatus.OK
            );
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }
    }


}//FIN DE A CLASE VETERINARYCONTROLLER
