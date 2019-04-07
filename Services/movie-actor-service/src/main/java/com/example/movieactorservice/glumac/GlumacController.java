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
        Glumac glumac2 = new Glumac("Amy", "Poehler");
        Glumac glumac3 = new Glumac("Robert", "Downey");
        Glumac glumac4 = new Glumac("Chris", "Hemsworth");
        Glumac glumac5 = new Glumac("Tom", "Hiddleston");
        Glumac glumac6 = new Glumac("Tom", "Holand");
        Glumac glumac7 = new Glumac("Scarlett", "Johansson");
        Glumac glumac8 = new Glumac("Christian", "Bale");
        Glumac glumac9 = new Glumac("Jack" ,"Nicholson");
        Glumac glumac10 = new Glumac("John", "Travolta");


        Film film1 = new Film("Endgame");
        Film film2 = new Film("The Prestige");
        Film film3 = new Film("Batman");
        Film film4 = new Film("Iron Man");
        Film film5 = new Film("Spiderman");
        Film film6 = new Film("Pulp fiction");
        Film film7 = new Film("The shining");

        glumac3.setMovies(film1);
        glumac3.setMovies(film4);

        glumac4.setMovies(film1);
        glumac5.setMovies(film1);
        glumac6.setMovies(film1);
        glumac6.setMovies(film5);
        glumac7.setMovies(film1);
        glumac7.setMovies(film2);
        glumac8.setMovies(film2);
        glumac8.setMovies(film3);
        glumac9.setMovies(film7);
        glumac10.setMovies(film6);
       

        film1.setGlumci(glumac3);
        film1.setGlumci(glumac4);
        film1.setGlumci(glumac5);
        film1.setGlumci(glumac6);
        film1.setGlumci(glumac7);
        film2.setGlumci(glumac8);
        film2.setGlumci(glumac7);
        film3.setGlumci(glumac8);

        film4.setGlumci(glumac3);
        film5.setGlumci(glumac6);
        film6.setGlumci(glumac10);
        film7.setGlumci(glumac9);

        glumacRepository.save(glumac);
        glumacRepository.save(glumac2);
        glumacRepository.save(glumac3);
        glumacRepository.save(glumac4);
        glumacRepository.save(glumac5);
        glumacRepository.save(glumac6);
        glumacRepository.save(glumac7);
        glumacRepository.save(glumac8);
        glumacRepository.save(glumac9);
        glumacRepository.save(glumac10);


        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);
        filmRepository.save(film4);
        filmRepository.save(film5);
        filmRepository.save(film6);
        filmRepository.save(film7);

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
        return filmRepository.findByActor(glumac.getId());
    } 

}