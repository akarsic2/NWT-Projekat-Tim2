package com.nwt.movie.api;

import com.nwt.movie.repository.*;
import com.nwt.movie.models.*;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.mapping.Array;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

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
        ArrayList<Movie> filmovi = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> {
            filmovi.add(movie);
        });
        JSONArray jsArray = new JSONArray(filmovi);
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

    @RequestMapping(value = "/add", method = RequestMethod.POST) 
    public ResponseEntity<String> addNewMovie(@RequestBody @Valid Movie movie) {
        //  try{
            Movie result = movieRepository.save(movie);
            JSONObject o = new JSONObject();
            o.put("id", result.getId());
            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);
    }


    
}