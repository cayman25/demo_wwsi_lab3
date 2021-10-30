package com.wwsi.demo;


import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.models.BlobItem;
import com.wwsi.demo.models.BlobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Autowired
    AzureBlobService azureBlobService;

    String containerUrl="https://mdrobotstorage.blob.core.windows.net/gallery/";

    public Set<BlobModel> getPhotos(){
        return azureBlobService.getBlobs()
                .stream()
                .map(blobItem -> BlobModel.builder().id(blobItem.getName()).url(containerUrl+blobItem.getName()).build())
                .collect(Collectors.toSet());
    }

    public void deletePhoto(String id){
        azureBlobService.deleteBlob(id);
    }

    public void uploadPhoto(String id, MultipartFile file){
        try {
            azureBlobService.uploadBlob(id, BinaryData.fromBytes(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
