package com.api.annoncesservice.controllers;


import com.api.annoncesservice.dao.AnnonceRepository;
import com.api.annoncesservice.entity.Annonce;
import com.api.annoncesservice.payload.UploadFileResponse;
import com.api.annoncesservice.property.FileStorageProperties;
import com.api.annoncesservice.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class AnnonceController {
    @Autowired
    private FileStorageService fileStorageService;
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
    @PostMapping(value = "/updateAnnonce")
    public Annonce updateAnnonce(@RequestParam(name="id") Long id, @RequestBody Annonce annonce){
        annonce.setId(id);
        return annonceRepository.save(annonce);
    }

    @PostMapping(value = "/addAnnonce")
    public Annonce addAnnonce(@RequestBody Annonce annonce){
        return annonceRepository.save(annonce);
    }

    @GetMapping(value = "/deleteAnnonce")
    public void deleteAnnonce(@RequestParam(name="id") Long id){
        annonceRepository.deleteById(id);
    }
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam(name="file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        System.out.println();
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        File img = new File(resource.getURI());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));

    }

}
