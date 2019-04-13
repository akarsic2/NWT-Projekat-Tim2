package usermanagment;

import org.springframework.data.repository.CrudRepository;
import usermanagment.Models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
