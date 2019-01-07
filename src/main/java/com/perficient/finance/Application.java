package com.perficient.finance;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.perficient.finance.blobstore.BlobStore;
import com.perficient.finance.blobstore.S3Store;
import com.perficient.finance.blobstore.ServiceCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    ServiceCredentials serviceCredentials() {
        String vcapServices = "{\"user-provided\": [{\"credentials\": {\"access_key_id\": \"I69OWSM525NG3ODWMN9F\", \"bucket\": \"2018-2019\", \"secret_access_key\": \"q+h15i5zPtO6ZN0+RT2i5qUYga8x7rv07u+fwFO+\", \"endpoint\": \"http://10.113.34.53:9000\"}, \"name\": \"photo-storage\"}]}";
        return new ServiceCredentials(vcapServices);
    }

    @Bean
    public BlobStore blobStore(
        ServiceCredentials serviceCredentials) {

        String photoStorageAccessKeyId = serviceCredentials.getCredential("photo-storage", "user-provided", "access_key_id");
        String photoStorageSecretKey = serviceCredentials.getCredential("photo-storage", "user-provided", "secret_access_key");
        String photoStorageBucket = serviceCredentials.getCredential("photo-storage", "user-provided", "bucket");

        AWSCredentials credentials = new BasicAWSCredentials(photoStorageAccessKeyId, photoStorageSecretKey);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);
        String endpoint = "http://10.113.34.53:9000";
        System.out.println(endpoint);
        if (endpoint != null) {
            s3Client.setEndpoint(endpoint);
        }

        return new S3Store(s3Client, photoStorageBucket);
    }
}
