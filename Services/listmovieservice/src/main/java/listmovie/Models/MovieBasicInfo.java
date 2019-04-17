package listmovie.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MovieBasicInfo {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;*/

    @Id
    private Integer movieId;
    private String naziv;

    @ManyToMany(mappedBy = "movies")
    private Set<MovieList> lists;

    /*public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }*/

    public MovieBasicInfo() {

    }
    public MovieBasicInfo(Integer id, String naziv){
        this.movieId = id;
        this.naziv = naziv;
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

    public Set<MovieList> getLists() {
        return lists;
    }

    public void setLists(Set<MovieList> lists) {
        this.lists = lists;
    }
}
