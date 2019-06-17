package listmovie.Controllers;

import listmovie.Models.*;
import listmovie.Repositories.MovieBasicInfoRepository;
import listmovie.Repositories.MovieListPRepository;
import listmovie.Repositories.MovieListRepository;
import listmovie.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ListMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private MovieListPRepository movieListPRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public void addDataToTable() {
    }

    @PostMapping("/addBasicMovieInfo")
    public ResponseEntity<String> addNewMovie(@RequestBody MovieBasicInfo movie) {
        try {
            MovieBasicInfo result = movieBasicInfoRepository.save(movie);
            JSONObject o = new JSONObject();
            o.put("id", result.getMovieId());

            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/addUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            User result = userRepository.save(user);
            JSONObject o = new JSONObject();
            o.put("id", result.getUserId());

            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value="userlists/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserLists(@PathVariable int userId){
        List<User> list = new ArrayList();
        userRepository.findAll().forEach(x -> {
            if (x.getUserId() == userId)
            {
                list.add(x);
            }
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/addList")
    public ResponseEntity<String> addNewList(@RequestBody UserList movieList) {
        try {
            List<User> list = new ArrayList();
            userRepository.findAll().forEach(us -> {
                if (us.getUserId() == movieList.user.getUserId()){
                    list.add(us);
                }
            });
            User userResult = new User();
            if (list.size() == 0){
                userResult = userRepository.save(movieList.user);
            }
            else{
                userResult = list.get(0);
            }
            MovieList ml = new MovieList();
            ml.setListName(movieList.listName);
            User simpleUser = new User();
            simpleUser.setUserId(userResult.getUserId());
            ml.setUser(simpleUser);
            MovieList result = movieListRepository.save(ml);
            JSONObject o = new JSONObject();
            o.put("id", result.getId());

            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @CrossOrigin
    @RequestMapping(value="getmoviesforlist/{listId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Integer>> getMoviesForList(@PathVariable int listId) {
       ArrayList<Integer> moviesIds = new ArrayList<Integer>();

       movieListPRepository.findAll().forEach(m -> {
           if (m.listId == listId)
               moviesIds.add(m.movieId);
       });

        return new ResponseEntity<ArrayList<Integer>>(moviesIds, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value="addmovietolist", method = RequestMethod.POST)
    public ResponseEntity<MovieList> addMovieToList(@RequestBody MovieListP movieList) {
         AtomicBoolean entryExists = new AtomicBoolean(false);

        movieListPRepository.findAll().forEach(m -> {
            if (m.listId == movieList.listId && m.movieId == movieList.movieId)
                entryExists.set(true);
        });

        if (entryExists.get() == true)
            return new ResponseEntity<MovieList>(new MovieList(), HttpStatus.NOT_ACCEPTABLE);

        movieListPRepository.save(movieList);

        return new ResponseEntity<MovieList>(new MovieList(), HttpStatus.OK);
    }

    /*@RequestMapping(value="addmovietolist", method = RequestMethod.POST)
    public ResponseEntity<MovieList> addMovieToList(@RequestBody MovieListUser movieListUser) {
        try{
            List<MovieBasicInfo> movies = new ArrayList();
            movieBasicInfoRepository.findAll().forEach(m -> {
                movies.add(m);
            });

            boolean movieExists = false;
            for (MovieBasicInfo movie : movies) {
                if (movie.getMovieId() == movieListUser.movie.getMovieId()){
                    movieExists = true;
                }
            }

            MovieBasicInfo movieForDB = new MovieBasicInfo();
            if (!movieExists){
               movieForDB = movieBasicInfoRepository.save(movieListUser.movie);
            }
            else{
                movieForDB = movieListUser.movie;
            }
            
            Set<MovieBasicInfo> listOfMovies = new HashSet();
            listOfMovies.add(movieForDB);

            MovieList ml = new MovieList();
            ml.setId(movieListUser.listId);
            ml.setMovies(listOfMovies);

            //user
            User u = new User();
            u.setUserId(movieListUser.userId);

            ml.setUser(u);

            //list
            List<User> ul = new ArrayList();
            userRepository.findAll().forEach(x -> {
                ul.add(x);
            });

            MovieList result = movieListRepository.save(ml);
            return new ResponseEntity<MovieList>(result, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            return new ResponseEntity<MovieList>(new MovieList(), HttpStatus.NOT_ACCEPTABLE);
        }
    }*/
    /*@PostMapping("/addMovie")
    public void addMovie(@RequestParam("movieId") int movieId, @RequestParam("name") String name) {
        try {
            MovieBasicInfo movieBasicInfo = new MovieBasicInfo();
            movieBasicInfo.setMovieId(movieId);
            movieBasicInfo.setNaziv(name);

            movieBasicInfoRepository.save(movieBasicInfo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @PostMapping("/addUser")
    public void addUser(@RequestParam("name") String name) {
        try {
            User user = new User();
            user.setUsername(name);

            userRepository.save(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/movie")
    public ResponseEntity getMovie() {
        try {
            RestTemplate restTempl = new RestTemplate();

            String rezultat = restTempl.getForObject("http://movie-service/movie/1", String.class);

            return new ResponseEntity<>(rezultat, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }*/
}