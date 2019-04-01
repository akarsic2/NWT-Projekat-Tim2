package com.example.movieactorservice.glumac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import com.example.movieactorservice.film.Film;
import com.example.movieactorservice.film.FilmRepository;
import com.mysql.cj.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlumacController {

    @Autowired
    private GlumacRepository glumacRepository;
    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("/test")
    public String test(){
        return "nwt test";
    }

    @RequestMapping("/")
    public void addDataToTable() {
        Glumac glumac = new Glumac("Megan", "Fox");
        Glumac glumac1 = new Glumac("Cameron", "Diaz");
        Glumac glumac2 = new Glumac("Amy", "Poehler");
        Glumac glumac3 = new Glumac("Robert", "Downey");
        Glumac glumac4 = new Glumac("Chris", "Hemsworth");
        Glumac glumac5 = new Glumac("Tom", "Hiddleston");
        Glumac glumac6 = new Glumac("Tom", "Holand");
        Glumac glumac7 = new Glumac("Scarlett", "Johansson");
        Glumac glumac8 = new Glumac("Christian", "Bale");


        Film film = new Film("Avengers, Age of Ultron");
        Film film1 = new Film("Endgame");
        Film film2 = new Film("The Prestige");
        Film film3 = new Film("Batman");
        Film film4 = new Film("Iron Man");
        Film film5 = new Film("Spiderman");

        glumac3.setMovies(film);
        glumac3.setMovies(film1);
        glumac3.setMovies(film4);

        glumac5.setMovies(film);
        glumac5.setMovies(film1);

        film.setGlumci(glumac3);
        film.setGlumci(glumac5);

        film1.setGlumci(glumac3);
        film1.setGlumci(glumac5);
        film4.setGlumci(glumac3);

        glumacRepository.save(glumac);
        glumacRepository.save(glumac1);
        glumacRepository.save(glumac2);
        glumacRepository.save(glumac3);
        glumacRepository.save(glumac4);
        glumacRepository.save(glumac5);
        glumacRepository.save(glumac6);
        glumacRepository.save(glumac7);
        glumacRepository.save(glumac8);

        filmRepository.save(film);
        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);
        filmRepository.save(film4);
        filmRepository.save(film5);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addNewActor(@RequestParam String name, @RequestParam String last) {
        Glumac glumac = new Glumac(name, last);
        glumacRepository.save(glumac);
    }

    @RequestMapping(value = "/glumac", method = RequestMethod.GET)
    public List<Glumac> findActor(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "last", required = false) String last) {
        if(last == null)
            return glumacRepository.findByName(name);
        if(name == null)
            return glumacRepository.findByLastName(last);
        return glumacRepository.findByNameAndLastName(name, last);
        
    }

    @RequestMapping(value = "/filmovi", method = RequestMethod.GET)
    public List<Film> findAllMovies(@RequestParam String name, @RequestParam String last) {
        if(glumacRepository.findByNameAndLastName(name, last).isEmpty())
            return null;
        Glumac glumac = glumacRepository.findByNameAndLastName(name, last).get(0);
        return glumacRepository.findByGlumci(glumac.getId());
    } 

}