package com.wwsi.demo;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AzureBlobService {

    public BlobServiceClient blobServiceClient;
    public BlobContainerClient containerClient;

    private String connectionString="DefaultEndpointsProtocol=https;AccountName=mdrobotstorage;AccountKey=1/0OjukbTnJNW2SsVSoKBaRH0LfYyba6ui4bbOWg6+thPKexw3M3dzINGr74YSY7FaOQ5eFXEj8ygp/9MoomIQ==;EndpointSuffix=core.windows.net";
    private String accessKey=System.getenv("BLOB_ACCESS_KEY");

    @PostConstruct
    private void init(){
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient("gallery");
    }

    public PagedIterable<BlobItem> getBlobs(){
        return containerClient.listBlobs();
    }
    public void deleteBlob(String id){
        containerClient.getBlobClient(id).delete();
    }

    public void uploadBlob(String id, BinaryData data){
        containerClient.getBlobClient(id).upload(data);
    }
}
