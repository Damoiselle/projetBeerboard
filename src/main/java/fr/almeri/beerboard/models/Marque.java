package fr.almeri.beerboard.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table(name = "marque")
public class Marque {

    private static final Logger LOGGER = Logger.getLogger(Marque.class.getName());

    @Id
    @Column(name = "nom_marque")
    private String nomMarque;

    @JoinColumn(name = "code_brasserie")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brasserie brasserie;


    //Constructeur avec 1 paramètre nom

    public Marque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    //Constructeur vide
    public Marque() {

    }


    //Equals and hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marque)) return false;
        Marque marque = (Marque) o;
        return getNomMarque().equals(marque.getNomMarque()) && getBrasserie().equals(marque.getBrasserie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomMarque(), getBrasserie());
    }


    //Getters et setters

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public Brasserie getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(Brasserie brasserie) {
        this.brasserie = brasserie;
    }

    @Override
    public String toString() {
        return nomMarque;
    }


}
