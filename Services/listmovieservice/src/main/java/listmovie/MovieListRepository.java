package listmovie;

import org.springframework.data.repository.CrudRepository;
import listmovie.Models.User;

public interface MovieListRepository extends CrudRepository<User, Integer> {

}
