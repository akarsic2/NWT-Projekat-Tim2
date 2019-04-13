package listmovie.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;*/

    @Id
    private int userId;
    private String username;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<MovieList> movieLists;

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<MovieList> getMovieLists() {
        return movieLists;
    }

    public void setMovieLists(Set<MovieList> movieLists) {
        this.movieLists = movieLists;
    }
}