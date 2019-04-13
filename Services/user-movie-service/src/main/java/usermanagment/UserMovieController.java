package user.managment.usermanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import user.managment.usermanagment.Models.MovieBasicInfo;
import user.managment.usermanagment.Models.User;

import java.util.ArrayList;

@Controller
public class UserMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

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
}