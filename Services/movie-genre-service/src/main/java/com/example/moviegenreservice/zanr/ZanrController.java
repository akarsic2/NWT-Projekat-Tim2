package com.example.moviegenreservice.zanr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZanrController {

    @Autowired
    private ZanrRepository zanrRepository;

    @RequestMapping("/test")
    public String test(){
        return "nwt test";
    }


    @RequestMapping("/add")
    public void addNewZanr(@RequestParam String _zanr) {
        Zanr zanr = new Zanr(_zanr);

        zanrRepository.save(zanr);
    }
}