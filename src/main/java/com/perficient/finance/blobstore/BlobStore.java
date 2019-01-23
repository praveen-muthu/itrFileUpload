package com.perficient.finance.blobstore;

import com.perficient.finance.itr.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BlobStore {

    void put(Blob blob) throws IOException;
    List<Employee> get() throws IOException;
    /*Optional<Blob> get(String name) throws IOException;

    void deleteAll();*/
}
