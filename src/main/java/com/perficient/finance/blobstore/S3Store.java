package com.perficient.finance.blobstore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import org.apache.tika.Tika;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class S3Store implements BlobStore {

    private final AmazonS3 s3;

    private final Tika tika = new Tika();

    private final String bucketName;

    public S3Store(AmazonS3 s3, String bucketName) {
        this.s3 = s3;
        this.bucketName = bucketName;
    }


    @Override
    public void put(Blob blob) throws IOException {
        if (!s3.doesBucketExist(bucketName)) {
            s3.createBucket(bucketName);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(blob.contentType);
        objectMetadata.setContentLength(blob.inputStream.available());

        s3.putObject(bucketName, blob.name+".zip", blob.inputStream, objectMetadata);
    }

   /* @Override
    public Optional<Blob> get(String name) throws IOException {
        if (!s3.doesObjectExist(bucketName, name)) {
            return Optional.empty();
        }

        try (S3Object s3Object = s3.getObject(bucketName, name)) {
            S3ObjectInputStream content = s3Object.getObjectContent();

            byte[] bytes = IOUtils.toByteArray(content);

            return Optional.of(new Blob(
                name,
                new ByteArrayInputStream(bytes),
                tika.detect(bytes)
            ));
        }
    }

    @Override
    public void deleteAll() {
        List<S3ObjectSummary> summaries = s3
            .listObjects(bucketName)
            .getObjectSummaries();

        for (S3ObjectSummary summary : summaries) {
            s3.deleteObject(bucketName, summary.getKey());
        }
    }*/
}
