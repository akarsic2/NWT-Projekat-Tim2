package com.example.moviegenreservice.zanr;

import java.util.List;

import com.example.moviegenreservice.film.Film;
import com.example.moviegenreservice.film.FilmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZanrController {

    @Autowired
    private ZanrRepository zanrRepository;

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("/test")
    public String test(){
        return "nwt test";
    }

    @RequestMapping("/")
    public void addDataToTable() {
        Zanr zanr = new Zanr("Drama");
        Zanr zanr1 = new Zanr("Sci-Fi");
        Zanr zanr2 = new Zanr("Thriller");
        Zanr zanr3 = new Zanr("Mystery");
        Zanr zanr4 = new Zanr("Action");
        Zanr zanr5 = new Zanr("Horror");

        Film film1 = new Film("Endgame");
        Film film2 = new Film("The Prestige");
        Film film3 = new Film("Batman");
        Film film4 = new Film("Iron Man");
        Film film5 = new Film("Spiderman");
        Film film6 = new Film("Pulp fiction");
        Film film7 = new Film("The shining");

        zanr.setMovies(film2);
        zanr.setMovies(film6);
        zanr.setMovies(film7);

        zanr1.setMovies(film1);
        zanr1.setMovies(film3);
        zanr1.setMovies(film4);
        zanr1.setMovies(film5);

        zanr2.setMovies(film3);
        zanr2.setMovies(film6);
        zanr2.setMovies(film7);

        zanr3.setMovies(film2);
        zanr3.setMovies(film7);

        zanr4.setMovies(film1);
        zanr5.setMovies(film7);

        film1.setZanrs(zanr1);
        film1.setZanrs(zanr4);
        film2.setZanrs(zanr);
        film2.setZanrs(zanr3);

        film3.setZanrs(zanr1);
        film3.setZanrs(zanr2);
        film4.setZanrs(zanr1);
        film5.setZanrs(zanr1);
        film6.setZanrs(zanr);

        film6.setZanrs(zanr2);

        film7.setZanrs(zanr);
        film7.setZanrs(zanr2);
        film7.setZanrs(zanr3);
        film7.setZanrs(zanr5);

        zanrRepository.save(zanr);
        zanrRepository.save(zanr1);
        zanrRepository.save(zanr2);
        zanrRepository.save(zanr3);
        zanrRepository.save(zanr4);
        zanrRepository.save(zanr5);

        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);
        filmRepository.save(film4);
        filmRepository.save(film5);
        filmRepository.save(film6);
        filmRepository.save(film7);

    }
   
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addNewGenre(@RequestParam String genre) {
        Zanr zanr = new Zanr(genre);
        zanrRepository.save(zanr);
    }

    @RequestMapping(value = "/zanrovi", method = RequestMethod.GET)
    public List<Zanr> findZanr(@RequestParam(value = "zanr",required = false) String zanr) {
        if(zanr == null)
            return (List<Zanr>) zanrRepository.findAll();
        return zanrRepository.findByZanr(zanr);
        
    }

    @RequestMapping(value = "/filmovi", method = RequestMethod.GET)
    public List<Film> findAllMovies(@RequestParam String zanr) {
        if(zanrRepository.findByZanr(zanr).isEmpty())
            return null;
        Zanr zanr1 = zanrRepository.findByZanr(zanr).get(0);
        return filmRepository.findByGenre(zanr1.getId());
    } 
}