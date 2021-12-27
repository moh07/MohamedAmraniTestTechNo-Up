package com.api.annoncesservice.property;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Target;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
