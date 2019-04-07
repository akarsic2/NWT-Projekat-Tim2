package com.example.movieactorservice.film;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FilmRepository extends CrudRepository<Film, Integer> {
    List<Film> findByFilm(String film);
    @Query("select distinct f from Film f join f.glumci g where g.id =?1")
    List<Film> findByActor(Integer glumacId);
}