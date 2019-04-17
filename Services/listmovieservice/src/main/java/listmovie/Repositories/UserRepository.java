package listmovie.Repositories;

import org.springframework.data.repository.CrudRepository;
import listmovie.Models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
