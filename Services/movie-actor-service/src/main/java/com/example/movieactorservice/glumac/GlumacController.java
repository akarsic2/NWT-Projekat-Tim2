package com.example.movieactorservice.glumac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlumacController {

    @Autowired
    private GlumacRepository glumacRepository;

    @RequestMapping("/test")
    public String test(){
        return "nwt test";
    }


    @RequestMapping("/add")
    public void addNewActor(@RequestParam String _glumac) {
        Glumac glumac = new Glumac(_glumac);

        glumacRepository.save(glumac);
    }
}