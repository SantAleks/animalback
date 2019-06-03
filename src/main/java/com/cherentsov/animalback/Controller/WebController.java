package com.cherentsov.animalback.Controller;

import com.cherentsov.animalback.Model.Pet;
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

import java.util.List;


@RestController
public class WebController {
    private static final Log logger = LogFactory.getLog(WebController.class);
  /*  @Bean
    public DBService dBServiceBean() {
        return DBService.getInstance();
    }*/

    private DBService dBService;
/*
    @Autowired
    public WebController(DBService dBService) {
        this.dBService = dBService;
    }
*/

    @Autowired
    public void setdBService(DBService dBService){
        this.dBService = dBService;
    }

    //Read by Id
    @RequestMapping(value = "/animals", method = RequestMethod.GET)
    //@RequestMapping(value = "/map", method = RequestMethod.GET, produces = { "application/json" })
    public List<Pet> animals() {
        //List<Pet> lPet = new ArrayList<>();
        List<Pet> lPet = dBService.getAll(Pet.class);
        /*
        String[][] mPoint = new String[lPet.size()][6];
        for (int i = 0; i < lPoint.size(); i++) {
            mPoint[i][0] = lPoint.get(i).getAddress();
            mPoint[i][1] = lPoint.get(i).getCity().getCountry().getName();
            mPoint[i][2] = lPoint.get(i).getCity().getName();
            mPoint[i][3] = lPoint.get(i).getBank().getName();
            mPoint[i][4] = lPoint.get(i).getfX().toString();
            mPoint[i][5] = lPoint.get(i).getfY().toString();
        }
        */
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

    @RequestMapping("/")
    public String index() {
        return "Сервис справочника животных находится по адресу /animals.";
    }
}
