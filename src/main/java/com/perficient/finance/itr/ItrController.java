package com.perficient.finance.itr;

import org.apache.tika.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.perficient.finance.blobstore.Blob;
import com.perficient.finance.blobstore.BlobStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Controller
@RequestMapping("/itreturns")
public class ItrController {

    private final ItrBean itrBean;
    private final BlobStore blobStore;

    public ItrController(ItrBean itrBean, BlobStore blobStore) {
        this.itrBean = itrBean;
        this.blobStore = blobStore;
    }

    @PostMapping("/itrfileupload")
    public String uploadCover(@RequestParam(value = "myfile", required = true) MultipartFile uploadedFile,
                              @RequestParam(value = "fname", required = true) String fName,
                              @RequestParam(value = "lname", required = true) String lName,
                              Map<String, String> model) throws IOException {
        if (uploadedFile.getSize() > 0) {
            Blob coverBlob = new Blob(
                getCoverBlobName(fName,lName),
                uploadedFile.getInputStream(),
                uploadedFile.getContentType()
            );
            try {
                blobStore.put(coverBlob);
                model.put("message", "Upload Successfull!!!");
            }catch (Exception e){
                System.out.println(e.getMessage());
                model.put("message", "Upload Failed!!! Try again later.");
            }
        }

        return "index";
    }

    @GetMapping("/itrReport")
    public String reports(){
        return "report";
    }

    private String getCoverBlobName(String fname, String lname ) {
        return format("%s, %s/%s-%s", lname,fname,lname,fname);
    }
}
