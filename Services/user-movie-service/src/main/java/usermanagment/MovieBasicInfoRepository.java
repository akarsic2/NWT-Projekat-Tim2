package usermanagment;

import org.springframework.data.repository.CrudRepository;
import usermanagment.Models.MovieBasicInfo;

public interface MovieBasicInfoRepository extends CrudRepository<MovieBasicInfo, Integer> {

}
