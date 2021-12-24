package com.api.annoncesservice.dao;

import com.api.annoncesservice.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins="*")
@RepositoryRestResource
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {

    // To call this service you must use this link localhost:8080/annonces/search/ByNameAndType?name=exp&type=exp
    @RestResource(path = "/ByNameAndType")
    public List<Annonce> findByNameContainsAndTypeContains(@Param("name") String name,@Param("type") String type);

}
