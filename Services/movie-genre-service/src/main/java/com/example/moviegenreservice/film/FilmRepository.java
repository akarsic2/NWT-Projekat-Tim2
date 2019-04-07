package com.example.moviegenreservice.film;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FilmRepository extends PagingAndSortingRepository<Film, Long>  {

    List<Film> findByFilm(String film);
    
    @Query("select distinct f from Film f join f.zanrs z where z.id =?1")
	List<Film> findByGenre(Integer id);

}