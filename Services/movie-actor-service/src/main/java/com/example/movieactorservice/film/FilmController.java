package com.example.movieactorservice.film;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("/testN")
    public String testN(){
        return "nwt test";
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public List<Film> findMovie(@RequestParam(value = "title",required = false) String title) {
        if(title == null)
            return (List<Film>) filmRepository.findAll();
        return filmRepository.findByFilm(title);
        
    }

    @RequestMapping(value = "/addN", method = RequestMethod.POST)
    public void addNew(@RequestParam String film) {
        Film film1 = new Film(film);
        filmRepository.save(film1);
    }
}