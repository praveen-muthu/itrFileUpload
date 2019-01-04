package com.perficient.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.perficient.finance.itr.ItrUserFixtures;
import com.perficient.finance.itr.ItrBean;

@Controller
public class HomeController {

    private final ItrBean albumsBean;
    private final ItrUserFixtures albumFixtures;

    public HomeController(ItrBean albumsBean, ItrUserFixtures albumFixtures) {
        this.albumsBean = albumsBean;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
