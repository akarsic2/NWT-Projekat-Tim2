package listmovie;

import com.netflix.discovery.converters.Auto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import listmovie.Models.MovieBasicInfo;
import listmovie.Models.User;

import javax.validation.Valid;


@Controller
public class ListMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    @PostMapping("/addMovie")
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
    /*@PostMapping("/addUser")
    public void addUser(@RequestParam("name") String name) {
        try {
            User user = new User();
            user.setUsername(name);

            userRepository.save(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

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
    }
}