package io.saslab.spring.rhms.controller;

import io.saslab.spring.rhms.entity.ResponsableRessourceHumaine;
import io.saslab.spring.rhms.repository.RHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RHController {

    @Autowired

    private RHRepository rhRepository;


    @PostMapping("/rh/{id}")
    public ResponsableRessourceHumaine save(@PathVariable Long id){
        ResponsableRessourceHumaine rh = new ResponsableRessourceHumaine();
        rh.setId(id);
        rh.setNom("nom");
        return rhRepository.save(rh);
    }

    @GetMapping("/rh")
    public List<ResponsableRessourceHumaine> findAll(){
        return rhRepository.findAll();
    }
}
