package com.pets.veterinary_prop2.service;

import com.pets.veterinary_prop2.Model.City;
import com.pets.veterinary_prop2.Model.Vaccine;
import com.pets.veterinary_prop2.Model.Vet;
import com.pets.veterinary_prop2.exceptions.VeterinaryException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class VetService {
    private List<Vet> vets;
    private List<City> cities;
    private List<Vaccine> vaccines;

    public VetService(){
        //Conecto con la base de datos para los nombres de los veterinarios
        vets= new ArrayList<>();
        vets.add(new Vet("1005" , "Sergio",(byte)18));
        vets.add(new Vet("1003","Jhair",(byte)18));
        vets.add(new Vet("1004","Jhon Jaime",(byte)18));
        vets.add(new Vet("1006","Sebastian",(byte)18));
        vets.add(new Vet("1009","Valeria",(byte)20));
        vets.add(new Vet("1002","Santiago",(byte)15));
        vets.add(new Vet("1001","Nicol",(byte)22));
    }//fin del public vetservice

    public Vet findVetByCode (String code) throws VeterinaryException{
        for (Vet vetFound: this.getVets()){
            if (vetFound.getCode().equals(code)){
                return vetFound;
            }//fin del if
        }//fin del for
        throw new VeterinaryException("El veterinario con codigo" +code+
                " no existe");
    }//fin del metodod fin by code


    public Vet findVetByName(String name) throws VeterinaryException {
        String lowercaseName= name.toLowerCase();
        for (Vet vets : this.getVets()){
            if(vets.getName().equalsIgnoreCase(name)){
                return vets;
            }//fin del if
        }//fin del for
        //no se encontro la ciudad buscada
        throw new VeterinaryException("La ciudad del nombre " +name+
                " no existe");

    }//FIN DEL FIND by city name


    public List<Vet> findVetByInitialLetter (char letter){
        List<Vet> vetsFound =new ArrayList<>();
        for(Vet vet: this.getVets()){
            if (vet.getName().charAt(0)==letter){
                vetsFound.add(vet);

            }//fin del if
        }//fin de for
        return vetsFound;
    }// in del public fincitybyinitialletter

    public Optional<Vet> findVetByAge(byte age) {
        return vets.stream()
                .filter(vet -> vet.getAge() == age)
                .findFirst();
    }

    public List<Vet> findVetsWithMinimumAge() {
        List<Vet> vetsWithMinimumAge = new ArrayList<>();
        byte minAge = Byte.MAX_VALUE;

        for (Vet vet : vets) {
            byte age = vet.getAge();
            if (age < minAge) {
                minAge = age;
                vetsWithMinimumAge.clear();
                vetsWithMinimumAge.add(vet);
            } else if (age == minAge) {
                vetsWithMinimumAge.add(vet);
            }
        }

        return vetsWithMinimumAge;
    }

    public List<Vet> findVetsBetweenAges(byte minAge, byte maxAge) {
        List<Vet> vetsBetweenAges = new ArrayList<>();

        for (Vet vet : vets) {
            byte age = vet.getAge();
            if (age >= minAge && age <= maxAge) {
                vetsBetweenAges.add(vet);
            }
        }

        return vetsBetweenAges;
    }

    public String addCity(City city) throws VeterinaryException{
        //verificar si existe
        if(this.verifyCityExist(city)){
            throw new VeterinaryException("El código ingresado ya existe");
        }
        else{
            this.cities.add(city);

        }
        return "Veterinario adicionado correctamente";
    }

    private boolean verifyCityExist(City city){
        for(City cityAct: this.cities){
            if(city.getCode().equals(cityAct.getCode())){
                return true;
            }
        }
        return false;
    }

    public String updateVet(String code, Vet vet) throws VeterinaryException{
        for(Vet vetAct : this.vets){
            if(vetAct.getCode().equals(code)){
                vetAct.setName(vet.getName());
                return "Veterinario actualizado correctamente";
            }
        }
        throw new VeterinaryException("El código ingresado no existe");

    }

}// FIN DE LA PUBLIC CLASS VET SERVICE


