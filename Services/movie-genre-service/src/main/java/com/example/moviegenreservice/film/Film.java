package com.example.moviegenreservice.film;

import java.util.HashMap;
import java.util.HashSet;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.moviegenreservice.zanr.Zanr;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(min=1, message = "Duzina mora biti bar 1")
    private String film;

    @ManyToMany
    @JoinTable(
        name = "zanr_film", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "zanr_id"))
    @JsonManagedReference
    private Set<Zanr> zanrs = new HashSet<>();

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

    public void setZanrs(Zanr zanr) {
        this.zanrs.add(zanr);
    }
    public Set<Zanr> getZanrs() {
        return this.zanrs;
    }
    
}
