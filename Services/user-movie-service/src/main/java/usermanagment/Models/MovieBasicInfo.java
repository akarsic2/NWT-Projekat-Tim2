package usermanagment.Models;

import javax.persistence.*;

@Entity
public class MovieBasicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer movieId;
    private String naziv;

    /*@ManyToMany
    private Set<User> usersThatLikeTheMovie;*/

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

    /*public Set<User> getUsersThatLikeTheMovie() {
        return usersThatLikeTheMovie;
    }

    public void setUsersThatLikeTheMovie(Set<User> usersThatLikeTheMovie) {
        this.usersThatLikeTheMovie = usersThatLikeTheMovie;
    }*/
}
