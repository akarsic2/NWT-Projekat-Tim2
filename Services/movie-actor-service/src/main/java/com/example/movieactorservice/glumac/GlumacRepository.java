package com.example.movieactorservice.glumac;

import java.util.List;
import java.util.Set;

import com.example.movieactorservice.film.Film;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface GlumacRepository extends PagingAndSortingRepository<Glumac, Long> {
    List<Glumac> findByName(@Param("name") String name);
    List<Glumac> findByLastName(@Param("last") String lastName);
    List<Glumac> findByNameAndLastName(@Param("name") String name, @Param("last") String last);
    @Query("SELECT u.movies FROM glumac u WHERE u.id = :id")
    Set<Film> findByGlumci(@Param("id")Integer id);
}