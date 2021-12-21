package com.api.annoncesservice;

import com.api.annoncesservice.entity.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class AnnoncesServiceApplication implements CommandLineRunner {
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(AnnoncesServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

        //add id to get response car spring par defaut ne retourne pas l'id
        restConfiguration.exposeIdsFor(Announce.class);

    }
}
