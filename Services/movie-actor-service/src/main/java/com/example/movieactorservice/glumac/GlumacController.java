package com.example.movieactorservice.glumac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.Notification;
import javax.validation.Valid;

import com.example.movieactorservice.film.Film;
import com.example.movieactorservice.film.FilmRepository;
import com.mysql.cj.Query;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class GlumacController {

    @Autowired
    private GlumacRepository glumacRepository;
    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("/test")
    public String test() {
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
        Glumac glumac9 = new Glumac("Jack", "Nicholson");
        Glumac glumac10 = new Glumac("John", "Travolta");
/*
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
        film7.setGlumci(glumac9);*/

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

        // filmRepository.save(film1);
        // filmRepository.save(film2);
        // filmRepository.save(film3);
        // filmRepository.save(film4);
        // filmRepository.save(film5);
        // filmRepository.save(film6);
        // filmRepository.save(film7);
   
    }

    @RequestMapping(value="glumci", method = RequestMethod.GET)
    public ResponseEntity getActors(){
        List<Glumac> glumci = new ArrayList<>();
        glumacRepository.findAll().forEach(glumac -> {
            glumci.add(glumac);
        });{

        };
        return new ResponseEntity<>(glumci, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addNewActor(@Valid @RequestBody Glumac glumac) {
        Glumac glumac1 = glumacRepository.save(glumac);
        JSONObject o = new JSONObject();
        o.put("id", glumac1.getId());
        return new ResponseEntity<String>(o.toString(), HttpStatus.OK);
        
    }


    @RequestMapping(value = "/addactorstomovie", method = RequestMethod.POST)
    public ResponseEntity addActorsToMovie(@Valid @RequestBody MovieActors movieActors) {
        try{
            Film f = new Film();
            f.setId(movieActors.movieId);
            f.setFilm(movieActors.movieName);
            for (Integer actorId : movieActors.actors) {
                Glumac g = new Glumac();
                g.setId(actorId);
                f.setGlumci(g);
            }
            
            filmRepository.save(f);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="movies/actor/{actorId}", method = RequestMethod.GET)
    public ResponseEntity<Set<Film>> getMoviesByGenre(@PathVariable int actorId){
        try{
            List<Glumac> actors = new ArrayList();
             glumacRepository.findAll().forEach(x -> {
                actors.add(x);
             });

             Glumac specificActor = new Glumac();

             for (Glumac actor : actors) {
                 if (actor.getId() == actorId){
                     specificActor = actor;
                     break;
                 }
             }
             Set<Film> movies = specificActor.getMovies();
             return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } 

    @RequestMapping(value = "/glumac/{name}/{last}", method = RequestMethod.GET)
    public ResponseEntity findActor(@PathVariable String name, @PathVariable String last) {
        if(last == null){
            if(glumacRepository.findByName(name).isEmpty())
                return new ResponseEntity<>("Ne postoji glumac", HttpStatus.NOT_FOUND); 
            return new ResponseEntity<>(glumacRepository.findByName(name), HttpStatus.OK);
        }
        if(name == null){
            if(glumacRepository.findByName(last).isEmpty())
                return new ResponseEntity<>("Ne postoji glumac", HttpStatus.NOT_FOUND); 
            return new ResponseEntity<>(glumacRepository.findByLastName(name), HttpStatus.OK);
        }
        if(glumacRepository.findByNameAndLastName(name, last).isEmpty())
            return new ResponseEntity<>("Ne postoji glumac", HttpStatus.NOT_FOUND); 

        return new ResponseEntity<>(glumacRepository.findByNameAndLastName(name,last), HttpStatus.OK);

        
    }

    @RequestMapping(value = "/filmovi/{name}/{last}", method = RequestMethod.GET)
    public ResponseEntity findAllMovies(@PathVariable String name, @PathVariable String last) {
        if(glumacRepository.findByNameAndLastName(name, last).isEmpty())
            return new ResponseEntity<>("Ne postoji glumac", HttpStatus.NOT_FOUND); 
        Glumac glumac = glumacRepository.findByNameAndLastName(name, last).get(0);
        if( filmRepository.findByActor(glumac.getId()).isEmpty())
            return new ResponseEntity<>("Ne postoji film", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(filmRepository.findByActor(glumac.getId()), HttpStatus.OK);
        
    } 

}