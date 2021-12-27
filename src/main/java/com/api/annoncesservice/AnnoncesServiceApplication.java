package com.api.annoncesservice;

import com.api.annoncesservice.dao.AnnonceRepository;
import com.api.annoncesservice.entity.Annonce;
import com.api.annoncesservice.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AnnoncesServiceApplication implements CommandLineRunner {
    //@autowired to inject the service

    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(AnnoncesServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

        //add id to get response car spring par defaut ne retourne pas l'id
        restConfiguration.exposeIdsFor(Annonce.class);
//        annonceRepository.save(new Annonce(null,"T3 à Paris", "Bla bla bla", 1650, "image link", "Location"));
//        annonceRepository.save(new Annonce(null,"studio à République", "Bla bla bla", 850, "image link", "Location"));
//        annonceRepository.save(new Annonce(null,"T2 proche de Paris", "Bla bla bla", 550000, "image link", "Vente"));

        annonceRepository.findAll().forEach(annonce -> {
            System.out.println(annonce.toString());
        });
    }
}
