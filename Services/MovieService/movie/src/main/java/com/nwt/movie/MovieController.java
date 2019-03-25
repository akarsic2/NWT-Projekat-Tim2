package com.nwt.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping("/test")
    public String test(){
        return "nwt test";
    }

    @RequestMapping("/add")
    public void addNewMovie(@RequestParam String name) {
        Movie movie = new Movie();
        movie.setNaziv(name);

        movieRepository.save(movie);
    }
}