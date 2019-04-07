package com.nwt.movie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Movie {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 5, message = "Duzina naziva mora biti preko 5 karaktera")
    private String naziv;
    @Size(min = 10, message = "Opis mora sadrzavati minimalno 10 karaktera")
    private String kratakOpis;
    private String reziser;
    private String scenaristi;
    private String producent;
    @Pattern(regexp="^(https?://)?(www.youtube.com|youtu.?be)/.+$")
    private String trailer;
    private String slika;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKratakOpis() {
        return kratakOpis;
    }

    public void setKratakOpis(String kratakOpis) {
        this.kratakOpis = kratakOpis;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public String getScenaristi() {
        return scenaristi;
    }

    public void setScenaristi(String scenaristi) {
        this.scenaristi = scenaristi;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Movie(){}

    public Movie(String naziv, String kratakOpis, String reziser, String scenaristi, String producent, String trailer, String slika ){
        this.naziv=naziv;
        this.kratakOpis = kratakOpis;
        this.reziser = reziser;
        this.scenaristi = scenaristi;
        this.producent = producent;
        this.trailer = trailer;
        this.slika= slika;
    }
}
