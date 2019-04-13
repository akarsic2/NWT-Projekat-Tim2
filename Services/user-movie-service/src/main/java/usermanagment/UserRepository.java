package user.managment.usermanagment;

import org.springframework.data.repository.CrudRepository;
import user.managment.usermanagment.Models.MovieBasicInfo;
import user.managment.usermanagment.Models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
