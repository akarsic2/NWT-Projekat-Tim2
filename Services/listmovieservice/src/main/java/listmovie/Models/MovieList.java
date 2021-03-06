package listmovie.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class MovieList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 5, max = 100)
    private String listName;

    @ManyToMany
    @JoinTable(
            name = "list_movie",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<MovieBasicInfo> movies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<MovieBasicInfo> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieBasicInfo> movies) {
        this.movies = movies;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    // public User getUser(){
    //     return user;
    // }

    public void setUser(User user) {
        this.user = user;
    }
}
