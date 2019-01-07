package com.perficient.finance.itr;

import com.perficient.finance.blobstore.Blob;
import com.perficient.finance.blobstore.BlobStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import static java.lang.String.format;

@Controller
public class ItrController {

    private final BlobStore blobStore;

    public ItrController(BlobStore blobStore) {
        this.blobStore = blobStore;
    }

    @PostMapping("/itrfileupload")
    public String uploadCover(@RequestParam(value = "myfile", required = true) MultipartFile uploadedFile,
                              @RequestParam(value = "fname", required = true) String fName,
                              @RequestParam(value = "lname", required = true) String lName,
                              RedirectAttributes redirectAttributes) throws IOException {
        if (uploadedFile.getSize() > 0) {
            Blob coverBlob = new Blob(
                getCoverBlobName(fName,lName),
                uploadedFile.getInputStream(),
                uploadedFile.getContentType()
            );
            try {
                blobStore.put(coverBlob);
                redirectAttributes.addFlashAttribute("message", "Upload Completed!!!");
            }catch (Exception e){
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "Upload Failed!!! Try again later.");
            }
        }

        return "redirect:itreturns/";
    }

    @GetMapping("/itrReport")
    public String reports(){
        return "report";
    }

    private String getCoverBlobName(String fname, String lname ) {
        return format("%s %s/%s %s", fname,lname,fname,lname);
    }
}
