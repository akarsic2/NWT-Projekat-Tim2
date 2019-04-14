package listmovie;

import listmovie.Models.MovieList;
import org.springframework.data.repository.CrudRepository;
import listmovie.Models.User;

public interface MovieListRepository extends CrudRepository<MovieList, Integer> {

}
