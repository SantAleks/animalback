package com.cherentsov.animalback.Controller;

import com.cherentsov.animalback.Model.*;
import com.cherentsov.animalback.Service.DBService;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {
    private static final Log logger = LogFactory.getLog(WebController.class);

    private DBService dBService;

    @Autowired
    public void setdBService(DBService dBService){
        this.dBService = dBService;
    }

    //Read all
    @RequestMapping(value = "/animals", method = RequestMethod.GET)
    public List<Pet> animals() {
        List<Pet> lPet = dBService.getAll(Pet.class);
        return lPet;
    }

    //Read by Id
    @RequestMapping(value = "/animals/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pet> findById(@PathVariable long id){
        return new ResponseEntity<Pet>(dBService.read(Pet.class, id), HttpStatus.OK);
    }

    //Delete
    @DeleteMapping(path ={"/animals/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            dBService.deleteById(Pet.class, id);
        }
        catch(HibernateException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    //Update
    @PutMapping(value="/animals")
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        try {
            dBService.update(pet);
        } catch (HibernateException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(dBService.read(Pet.class, pet.getId()));
    }

    //Create
    @PostMapping(value="/animals")
    public ResponseEntity<Void> create(@RequestBody Pet pet){
        List<Pet> lPet = dBService.findByName(Pet.class, pet.getName());
        int flag = 0;
        for (Pet tpet: lPet) {
            if (pet.getName() == tpet.getName()) flag = 1;
        }
        if (flag == 1){
            logger.info("a animal with name " + pet.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        long idPet = 0;
        try{
            idPet = dBService.create(pet);
        }
        catch (HibernateException e){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    //Search entity
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Pet> search(@RequestParam(value="region", required=false, defaultValue="") String regionPattern,
                            @RequestParam(value="color", required=false, defaultValue="") String colorPattern,
                            @RequestParam(value="type", required=false, defaultValue="") String typePattern) {
        List<Pet> lPet = new ArrayList<>();
        List<Region> lRegion = new ArrayList<>();
        if (regionPattern.trim().length() > 0){
            lRegion = dBService.findByName(Region.class, regionPattern.trim());
            if (lRegion.size() == 0) return lPet;
        }

        List<SkinColor> lSkinColor = new ArrayList<>();
        if (colorPattern.trim().length() > 0){
            lSkinColor = dBService.findByName(SkinColor.class, colorPattern.trim());
            if (lSkinColor.size() == 0) return lPet;
        }

        List<AnimalType> lAnimalType = new ArrayList<>();
        if (typePattern.trim().length() > 0){
            lAnimalType = dBService.findByName(AnimalType.class, typePattern.trim());
            if (lAnimalType.size() == 0) return lPet;
        }


        //if (lRegion.size() == 1 || lSkinColor.size() == 1 || lAnimalType.size() == 1){
            lPet = dBService.findPetByFK(lRegion, lAnimalType, lSkinColor);
        //}
        return lPet;
    }

    //List Type Используется для выбора параметра "тип животного" при операции create и update
    @RequestMapping(value = "/animaltype", method = RequestMethod.GET)
    public List<AnimalType> animalType() {
        return dBService.getAll(AnimalType.class);
    }

    //List Location Используется для выбора параметра "Местоположение при операции create и update
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> location() {
        return dBService.getAll(Location.class);
    }

    //List SkinColor Используется для выбора параметра "Цвет шкуры" при операции create и update
    @RequestMapping(value = "/skincolor", method = RequestMethod.GET)
    public List<SkinColor> skinColor() {
        return dBService.getAll(SkinColor.class);
    }

    @RequestMapping("/")
    public String index() {
        return "Сервис справочника животных находится по адресу /animals.";
    }
}
