package listmovie;

import org.springframework.data.repository.CrudRepository;
import listmovie.Models.MovieBasicInfo;

public interface MovieBasicInfoRepository extends CrudRepository<MovieBasicInfo, Integer> {

}
