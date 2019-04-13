package com.nwt.movie.api;

import com.nwt.movie.repository.*;
import com.nwt.movie.models.*;
import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.query.QueryProducer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    QueryProducer producer;

    // @Autowired 
    // private RestTemplate restTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void init(){
        Movie m1 = new Movie("naziv1", "kratki opis1", "reziser1", "scenarista1", "producent1", "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika1");
        Movie m2 = new Movie("naziv2", "kratki opis2", "reziser2", "scenarista2", "producent2", "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika2");
        Movie m3 = new Movie("naziv3", "kratki opis3", "reziser3", "scenarista3", "producent3", "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");
        
        movieRepository.save(m1);
        movieRepository.save(m2);
        movieRepository.save(m3);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<String> getAllMovies(){
       //public String getAllMovies(){
        ArrayList<Movie> filmovi = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> {
            filmovi.add(movie);
        });
        JSONArray jsArray = new JSONArray(filmovi);

        // String url = "http://movie-genre-service/film";
        // return restTemplate.getForObject(url, String.class);
         return new ResponseEntity<String>(jsArray.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity getMovieById(@PathVariable int movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()){
            return new ResponseEntity<>("Ne postoji film", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movie.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") 
    public ResponseEntity<String> addNewMovie(@RequestBody @Valid Movie movie) {
        
            Movie result = movieRepository.save(movie);
            JSONObject object = new JSONObject();
            object.put("id", result.getId());
            object.put("naziv", result.getNaziv());
      //      producer.send(object);
            JSONObject o = new JSONObject();
            o.put("id", result.getId());
            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public ResponseEntity<String> getAllGrades(){
        ArrayList<Grade> ocjene = new ArrayList<>();
        gradeRepository.findAll().forEach(ocjena -> {
            ocjene.add(ocjena);
        });
        JSONArray jsArray = new JSONArray(ocjene);

        // String url = "http://movie-genre-service/film";
        // return restTemplate.getForObject(url, String.class);
         return new ResponseEntity<String>(jsArray.toString(), HttpStatus.OK);
    }
    
}