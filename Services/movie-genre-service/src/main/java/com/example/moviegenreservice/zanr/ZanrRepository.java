package com.example.moviegenreservice.zanr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface ZanrRepository extends PagingAndSortingRepository<Zanr, Long> { 
    List<Zanr> findByZanr(@Param("zanr") String zanr);
}