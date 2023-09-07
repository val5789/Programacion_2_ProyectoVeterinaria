package com.pets.veterinary_prop2.service;

import com.pets.veterinary_prop2.Model.City;
import com.pets.veterinary_prop2.Model.Vaccine;
import com.pets.veterinary_prop2.Model.Vet;
import com.pets.veterinary_prop2.exceptions.VeterinaryException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CityService {
    private List<Vet> vets;
    private List<City> cities;
    private List<Vaccine> vaccines;

    public CityService() {
        //Conecto a la base de datos y cargo las ciudades y veterinarios
        cities =new ArrayList<>();
        cities.add(new City( "1717001", "Manizales"));
        cities.add(new City("16917063", "Pereira"));
        cities.add(new City("0505001", "Medellín"));
        cities.add(new City("1111001", "Bogotá"));
        cities.add(new City("1313001", "Cartagena de indias"));
        cities.add(new City("1515001", "Tunja"));
        cities.add(new City("1818001", "Florencia"));
        cities.add(new City("1919001", "Popayán"));
        cities.add(new City("2020001", "Valledupar"));
        cities.add(new City("2323001", "Montería"));
        cities.add(new City("2525001", "Agua de Dios"));
        cities.add(new City("2727001", "Quibdo"));

    }//FIN DEL CONSTRUCTOR

    public City findCityById (String id) throws VeterinaryException {
        for (City cityFound : this.getCities()){
            if(cityFound.getCode().equals(id)){
                return cityFound;
            }//fin del if
        }//fin del for
        //no se encontro la ciudad buscada
        throw new VeterinaryException("La ciudad con " +id+
                " no existe");
    }//FIN DEL FIND

    public City findCityByDescription (String description) throws VeterinaryException {
        String lowercaseDescription = description.toLowerCase();
        for (City cities : this.getCities()){
            if(cities.getDescription().equalsIgnoreCase(description)){
                return cities;
            }//fin del if
        }//fin del for
        //no se encontro la ciudad buscada
        throw new VeterinaryException("La ciudad del nombre " +description+
                " no existe");

    }//FIN DEL FIND by city name

    public List<City> findCityByInitialLetter (char letter){
        List<City> citiesFound =new ArrayList<>();
        for(City city: this.getCities()){
            if (city.getDescription().charAt(0)==letter){
                citiesFound.add(city);

            }//fin del if
        }//fin de for
        return citiesFound;
    }// in del public fincitybyinitialletter

    public String addCity(City city) throws VeterinaryException{
        //verificar si existe
        if(this.verifyCityExist(city)){
            throw new VeterinaryException("El código ingresado ya existe");
        }
        else{
            this.cities.add(city);

        }
        return "Ciudad adicionada correctamente";
    }

    private boolean verifyCityExist(City city){
        for(City cityAct: this.cities){
            if(city.getCode().equals(cityAct.getCode())){
                return true;
            }
        }
        return false;
    }

    public String updateCity(String code, City city) throws VeterinaryException{
        for(City cityAct : this.cities){
            if(cityAct.getCode().equals(code)){
                cityAct.setDescription(city.getDescription());
                return "Ciudad actualizada correctamente";
            }
        }
        throw new VeterinaryException("El código ingresado no existe");

    }


}//FIN DE LA CLASE VETERINARYSERVICE
