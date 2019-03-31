package user.managment.usermanagment;

import org.springframework.data.repository.CrudRepository;
import user.managment.usermanagment.Models.MovieBasicInfo;

public interface MovieBasicInfoRepository extends CrudRepository<MovieBasicInfo, Integer> {

}
