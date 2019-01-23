package com.perficient.finance.blobstore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.perficient.finance.itr.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class S3Store implements BlobStore {

    private final AmazonS3 s3;

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

    @Override
    public List<Employee> get() throws IOException {
        ListObjectsRequest lor = new ListObjectsRequest().withBucketName("2018-2019");
        List<Employee> empList = new ArrayList<Employee>();
        ObjectListing objectListing = s3.listObjects(lor);
        for (S3ObjectSummary summary: objectListing.getObjectSummaries()) {
            Employee emp = new Employee();
            emp.setEmpName(summary.getKey().split("/")[0]);
            empList.add(emp);
        }
        return empList;
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
