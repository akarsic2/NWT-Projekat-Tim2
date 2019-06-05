package com.nwt.movie.api;

import com.nwt.movie.repository.*;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonArray;
import com.nwt.movie.models.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.query.QueryProducer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins="http://localhost:4200")

public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private QueueProducer producer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void init() {
        Movie m1 = new Movie("Endgame", "kratki opis1", "reziser1", "scenarista1", "producent1",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika1");
        Movie m2 = new Movie("The Prestige", "kratki opis2", "reziser2", "scenarista2", "producent2",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika2");
        Movie m3 = new Movie("Batman", "kratki opis3", "reziser3", "scenarista3", "producent3",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");
        Movie m4 = new Movie("Iron Man", "kratki opis3", "reziser3", "scenarista3", "producent3",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");
        Movie m5 = new Movie("Spiderman", "kratki opis3", "reziser3", "scenarista3", "producent3",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");
        Movie m6 = new Movie("Pulp fiction", "kratki opis3", "reziser3", "scenarista3", "producent3",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");
        Movie m7 = new Movie("The shining", "kratki opis3", "reziser3", "scenarista3", "producent3",
                "https://www.youtube.com/watch?v=gq4S-ovWVlM", "slika3");

        movieRepository.save(m1);
        movieRepository.save(m2);
        movieRepository.save(m3);
        movieRepository.save(m4);
        movieRepository.save(m5);
        movieRepository.save(m6);
        movieRepository.save(m7);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<String> getAllMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> {
        movies.add(movie);
        });

        JSONArray result = new JSONArray(movies);

        return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/moviesgenres", method = RequestMethod.GET)
     public ResponseEntity<String> getAllMoviesWithGenres(){
        ArrayList<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> {
        movies.add(movie);
        });
        ArrayList<MovieGenres> movieGenresList = new ArrayList<>();
        String genresFromService = restTemplate.getForObject("http://movie-genre-service/film", String.class);
        JSONArray jsonArray = new JSONArray(genresFromService);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            MovieGenres movieGenres = new MovieGenres();
            movieGenres.id = obj.getInt("id");
            movieGenres.film = obj.getString("film");
            JSONArray jsonGenres = obj.getJSONArray("zanrs");
            ArrayList<Genre> genres = new ArrayList<>();
            for (int j=0;j<jsonGenres.length();j++){
                Genre genre = new Genre();
                JSONObject objGenre = (JSONObject)jsonGenres.get(j);
                genre.id = objGenre.getInt("id");
                genre.zanr = objGenre.getString("zanr");
                genres.add(genre);
            }
            movieGenres.zanrs = genres;
            movieGenresList.add(movieGenres);

        }

        for (MovieGenres movieGenres : movieGenresList) {
            for (Movie movie : movies) {
                if (movieGenres.id == movie.getId()){
                    movieGenres.kratakOpis = movie.getKratakOpis();
                    movieGenres.producent = movie.getProducent();
                    movieGenres.reziser = movie.getReziser();
                    movieGenres.scenaristi = movie.getScenaristi();
                    movieGenres.slika = movie.getSlika();
                    movieGenres.trailer = movie.getTrailer();
                }
            }
        }
        Gson gson = new Gson();
        String result = gson.toJson(movieGenresList);
        return new ResponseEntity<String>(result, HttpStatus.OK);
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

            try {
                Movie result = movieRepository.save(movie);
                JSONObject object = new JSONObject();
                object.put("id", result.getId());
                object.put("naziv", result.getNaziv());
                producer.send(object.toString());
                JSONObject o = new JSONObject();
                o.put("id", result.getId());
                o.put("movieName", result.getNaziv());
                return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

            } catch (Exception e) {
                System.out.print(e.getMessage());

                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
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