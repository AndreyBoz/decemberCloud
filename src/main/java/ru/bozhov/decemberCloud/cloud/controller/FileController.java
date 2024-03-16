package ru.bozhov.decemberCloud.cloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/file")
public class FileController {
    @PostMapping
    public ResponseEntity<?> postFile(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
