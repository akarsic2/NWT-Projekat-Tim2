package user.managment.usermanagment;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.managment.usermanagment.Models.MovieBasicInfo;
import user.managment.usermanagment.Models.User;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            User result = userRepository.save(user);
            JSONObject o = new JSONObject();
            o.put("id", result.getId());

            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<String> getUserById(@RequestBody int userId) {
        try {
            Optional<User> user = userRepository.findById(userId);

            JSONObject o = new JSONObject(user);

            return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<String> getUserByUsername(@RequestBody String username) {
        try {
            Iterable<User> users = userRepository.findAll();

            User user = null;

            while (users.iterator().hasNext())
            {
                user = users.iterator().next();

                if (user.getUsername().equals(username))
                    break;
            }

            JSONObject jsonObject = new JSONObject(user);

            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<String> deleteUserById(@RequestBody int userId) {
        try {
            userRepository.deleteById(userId);

            return new ResponseEntity<String>(String.valueOf(userId), HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*@GetMapping("/addMovie")
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
    }*/
}