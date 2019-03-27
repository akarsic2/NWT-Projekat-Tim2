package com.example.moviegenreservice.film;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.moviegenreservice.zanr.Zanr;

@Entity
@Table (name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String film;

    @ManyToMany
    @JoinTable(
        name = "zanr_film", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "zanr_id"))
    private Set<Zanr> zanrs;

    public Film() {}

    public Film(String film) {
        this.film = film;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    
}
