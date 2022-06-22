package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table
public class Marque {

    private static final Logger LOGGER = Logger.getLogger(Marque.class.getName());
    @Column(name="nom_marque")
    private String nomMarque;

    @Column(name="code_brasserie")
    private Brasserie brasserie;


//Constructeur vide

    public Marque() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marque)) return false;
        Marque marque = (Marque) o;
        return getNomMarque().equals(marque.getNomMarque()) && getBrasserie().equals(marque.getBrasserie());
    }
//
//    private Object getBrasserie() {
//    }

    //Hashcode
    @Override
    public int hashCode() {
        return Objects.hash(getNomMarque(), getBrasserie());
    }



    //toString

    @Override
    public String toString() {
        return "Marque{" +
                "nomMarque='" + nomMarque + '\'' +
                '}';
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
}
