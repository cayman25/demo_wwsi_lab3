package com.wwsi.demo;

import com.wwsi.demo.models.BlobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("api/v1/photos")
    public Set<BlobModel> getAllPhotos(){
        return apiService.getPhotos();
    }

    @PostMapping("api/v1/photo")
    public String addPhoto(@RequestPart("id") String id, @RequestPart("file") MultipartFile file){
        System.out.println(id);
        apiService.uploadPhoto(id,file);
        return "ssss";
    }

    @DeleteMapping("api/v1/photo/{id}")
    public String deletePhoto(@PathVariable String id){
        apiService.deletePhoto(id);
        return "Success";
    }

}
