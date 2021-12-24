package com.api.annoncesservice.controllers;


import com.api.annoncesservice.dao.AnnonceRepository;
import com.api.annoncesservice.entity.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class AnnonceController {

    @Autowired
    private AnnonceRepository annonceRepository;

    @GetMapping(value = "/getAnnonces")
    public List<Annonce> getAnnonces(){
        return annonceRepository.findAll();
    }

    @GetMapping(value = "/getAnnonceById/{id}")
    public Annonce getAnnonceById(@PathVariable(name="id") Long id){
        return annonceRepository.findById(id).get();
    }

    @GetMapping(value = "/getAnnonceByNameAndType")
    public List<Annonce> getAnnonceByNameAndType(@RequestParam String name, @RequestParam String type){
        return annonceRepository.findByNameContainsAndTypeContains(name,type);
    }
    @GetMapping(value = "/updateAnnonce")
    public Annonce updateAnnonce(@RequestParam(name="id") Long id, @RequestBody Annonce annonce){
        annonce.setId(id);
        return annonceRepository.save(annonce);
    }

    @GetMapping(value = "/addAnnonce")
    public Annonce addAnnonce(@RequestBody Annonce annonce){
        return annonceRepository.save(annonce);
    }

    @GetMapping(value = "/deleteAnnonce")
    public void deleteAnnonce(@RequestParam(name="id") Long id){
        annonceRepository.deleteById(id);
    }

}
