package usermanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import usermanagment.Models.MovieBasicInfo;
import usermanagment.Models.User;


@Controller
public class UserMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/addMovie")
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
    @GetMapping("/addUser")
    public void addUser(@RequestParam("name") String name) {
        try {
            User user = new User();
            user.setIme(name);

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
    }
}