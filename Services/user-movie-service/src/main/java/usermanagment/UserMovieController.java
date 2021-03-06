package usermanagment;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import usermanagment.Models.MovieBasicInfo;
import usermanagment.Models.User;
import usermanagment.Models.UserBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserMovieController {

    @Autowired
    private MovieBasicInfoRepository movieBasicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> getUserByUsernameAndPassword(@RequestBody UserBasic user){
        try{
            List<User> usersList = new ArrayList<>();

            userRepository.findAll().forEach(u -> {
                usersList.add(u);
            });

            // while (users.iterator().hasNext())
            // {
            //     result = users.iterator().next();

            //     if (result.getUsername().equals(user.username) && result.getPassword().equals(user.password))
            //         break;

            //     users.iterator().next();
            // }
            User result = null;
            for (User u : usersList) {
                System.out.println(u.getUsername() + " " + user.username);
                System.out.println(u.getPassword() + " " + user.password);
                if (u.getUsername().equals(user.username) && u.getPassword().equals(user.password)){
                    result = u;
                }
            }

            return new ResponseEntity<User>(result, HttpStatus.OK);
        }
        catch(Exception e){
            return null;
        }
    }

    // @GetMapping("/getUser")
    // public ResponseEntity<String> getUserById(@RequestBody int userId) {
    //     try {
    //         Optional<User> user = userRepository.findById(userId);

    //         JSONObject o = new JSONObject(user);

    //         return new ResponseEntity<String>(o.toString(), HttpStatus.OK);

    //     } catch (Exception e) {
    //         System.out.print(e.getMessage());
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    //     }
    // }

    // @GetMapping("/getUser")
    // public ResponseEntity<String> getUserByUsername(@RequestBody String username) {
    //     try {
    //         Iterable<User> users = userRepository.findAll();

    //         User user = null;

    //         while (users.iterator().hasNext())
    //         {
    //             user = users.iterator().next();

    //             if (user.getUsername().equals(username))
    //                 break;
    //         }

    //         JSONObject jsonObject = new JSONObject(user);

    //         return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

    //     } catch (Exception e) {
    //         System.out.print(e.getMessage());
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    //     }
    // }

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
    }*/
}