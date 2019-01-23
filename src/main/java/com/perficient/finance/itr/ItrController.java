package com.perficient.finance.itr;

import com.perficient.finance.blobstore.Blob;
import com.perficient.finance.blobstore.BlobStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Controller
public class ItrController {

    private final BlobStore blobStore;
    private final EmployeeFixture employeeFixture;
    public ItrController(BlobStore blobStore, EmployeeFixture employeeFixture) {
        this.blobStore = blobStore;
        this.employeeFixture = employeeFixture;
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
    public String reports(Map<String, Object> model){
        try{
            List<Employee> empList = blobStore.get();
            List<Employee> allempList = employeeFixture.load();
            List<Employee> unique = allempList.stream().filter(empList::contains).collect(Collectors.toList());
            allempList.removeAll(empList);
            model.put("submitted", unique);
            model.put("pending", allempList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "report";
    }

    private String getCoverBlobName(String fname, String lname ) {
        return format("%s %s/%s %s", fname,lname,fname,lname);
    }
}
