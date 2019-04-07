package com.example.moviegenreservice.film;

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
    @RequestMapping("/addN")
    public void addNew(@RequestParam String _film) {
        Film film = new Film(_film);
        filmRepository.save(film);
    }
}