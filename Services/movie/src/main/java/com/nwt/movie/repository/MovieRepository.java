package com.nwt.movie.repository;

import com.nwt.movie.models.Movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
