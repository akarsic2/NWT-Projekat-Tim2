package listmovie.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieListP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int movieId;
    public int listId;
}
