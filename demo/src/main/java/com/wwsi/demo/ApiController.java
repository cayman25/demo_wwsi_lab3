package com.wwsi.demo;

import com.wwsi.demo.models.BlobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("api/v1/photos")
    public ResponseEntity<Set<BlobModel>> getAllPhotos(){
        return ResponseEntity.ok()
                .headers(addHeader())
                .body(apiService.getPhotos());
    }

    @PostMapping("api/v1/photo")
    public ResponseEntity<String> addPhoto(@RequestPart("id") String id, @RequestPart("file") MultipartFile file){
        apiService.uploadPhoto(id,file);
        return ResponseEntity.ok()
                .headers(addHeader()).body("Success");
    }

    @DeleteMapping("api/v1/photo/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable String id){
        apiService.deletePhoto(id);
        return ResponseEntity.ok()
                .headers(addHeader()).body("Success");
    }

    private HttpHeaders addHeader(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*");
        responseHeaders.set("Access-Control-Allow-Methods","GET, PUT, POST, DELETE, HEAD, OPTIONS");
        return responseHeaders;
    }
}
