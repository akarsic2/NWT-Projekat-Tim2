package usermanagment.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MovieBasicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer movieId;
    private String naziv;

    /*@ManyToMany(mappedBy = "savedMovies")
    private Set<User> usersThatLikeTheMovie;
*/
    public MovieBasicInfo() {

    }
    public MovieBasicInfo(Integer id, String naziv) {
        this.movieId = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
/*
    public Set<User> getUsersThatLikeTheMovie() {
        return usersThatLikeTheMovie;
    }

    public void setUsersThatLikeTheMovie(Set<User> usersThatLikeTheMovie) {
        this.usersThatLikeTheMovie = usersThatLikeTheMovie;
    }*/
}
