package com.example.moviegenreservice.zanr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.example.moviegenreservice.QueueConsumer;
import com.example.moviegenreservice.film.Film;
import com.example.moviegenreservice.film.FilmRepository;
import com.netflix.eureka.cluster.HttpReplicationClient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpRequest;
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
public class ZanrController {

    @Autowired
    private ZanrRepository zanrRepository;

    @Autowired
    private FilmRepository filmRepository;

    QueueConsumer queueConsumer;

    @RequestMapping("/test")
    public String test() {
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

        Film film1 = new Film(1,"Endgame");
        Film film2 = new Film(2,"The Prestige");
        Film film3 = new Film(3,"Batman");
        Film film4 = new Film(4,"Iron Man");
        Film film5 = new Film(5,"Spiderman");
        Film film6 = new Film(6,"Pulp fiction");
        Film film7 = new Film(7,"The shining");

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
    public ResponseEntity addNewGenre(@Valid @RequestBody String genre) {
        Zanr zanr = zanrRepository.save(new Zanr(genre));
        JSONObject o = new JSONObject();
        o.put("id", zanr.getId());
        return new ResponseEntity<String>(o.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addgenrestomovie", method = RequestMethod.POST)
    public ResponseEntity addGenresToMovie(@Valid @RequestBody MovieGenres movieGenres) {
        try{
            Film f = new Film();
            f.setId(movieGenres.movieId);
            f.setFilm(movieGenres.movieName);
            for (Integer genreId : movieGenres.genres) {
                Zanr z = new Zanr();
                z.setId(genreId);
                f.setZanrs(z);
            }
            
            filmRepository.save(f);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="movies/genre/{genreId}", method = RequestMethod.GET)
    public ResponseEntity<Set<Film>> getMoviesByGenre(@PathVariable int genreId){
        try{
            List<Zanr> genres = new ArrayList();
             zanrRepository.findAll().forEach(x -> {
                genres.add(x);
             });

             Zanr specificGenre = new Zanr();

             for (Zanr genre : genres) {
                 if (genre.getId() == genreId){
                     specificGenre = genre;
                     break;
                 }
             }
             Set<Film> movies = specificGenre.getMovies();
             return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    

    @RequestMapping(value="zanrovi", method = RequestMethod.GET)
    public ResponseEntity getGenres(){
        List<Zanr> zanrovi = new ArrayList<>();
        zanrRepository.findAll().forEach(zanr -> {
            zanrovi.add(zanr);
        });{

        };
        return new ResponseEntity<>(zanrovi, HttpStatus.OK);
    }

    @RequestMapping(value = "/zanrovi/{zanr}", method = RequestMethod.GET)
    public ResponseEntity findZanr(@PathVariable String zanr) {
        if(zanrRepository.findByZanr(zanr).isEmpty())
            return new ResponseEntity<>("Ne postoji zanr", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(zanrRepository.findByZanr(zanr), HttpStatus.OK);
        
    }

    @RequestMapping(value = "/filmovi/{zanr}", method = RequestMethod.GET)
    public ResponseEntity findAllMovies(@PathVariable String zanr) {
        if(zanrRepository.findByZanr(zanr).isEmpty())
            return new ResponseEntity<>("Ne postoji zanr", HttpStatus.NOT_FOUND);
        Zanr zanr1 = zanrRepository.findByZanr(zanr).get(0);
        List<Film> filmovi = filmRepository.findByGenre(zanr1.getId());
        if(filmovi.isEmpty())
            return new ResponseEntity<>("Ne postoji film", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(filmovi, HttpStatus.OK);
    } 
}