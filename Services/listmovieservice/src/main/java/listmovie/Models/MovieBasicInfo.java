package listmovie.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class MovieBasicInfo {

    @Id
    @NotNull
    @Min(value = 0)
    private Integer movieId;

    @Size(min = 5, max = 150)
    private String naziv;

    @ManyToMany(mappedBy = "movies")
    private Set<MovieList> lists;

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
