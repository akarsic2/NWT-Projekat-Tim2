package com.nwt.movie.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Grade { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer vrijednost;
    private String kratakOpis;


    public Grade(){}

    public Grade(int vrijednost, String kratakOpis){
        this.vrijednost = vrijednost;
        this.kratakOpis = kratakOpis;
    }

    @ManyToMany(mappedBy = "grades")
    @JsonBackReference
    private Set<Movie> movies = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVrijednost(){
        return vrijednost;
    }

    public void setVrijednost(Integer vrijednost){
        this.vrijednost = vrijednost;
    }

    public String getKratakOpis(){
        return kratakOpis;
    }

    public void setKratakOpis(String kratakOpis){
        this.kratakOpis = kratakOpis;
    }

}