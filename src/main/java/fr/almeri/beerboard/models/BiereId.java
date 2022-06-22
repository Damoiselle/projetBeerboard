package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


//Class mock qui correspond à ma clef primaire pour Biere
//2 attributs : marque & version
public class BiereId implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_marque") //permet de définir la colonne qui possède une clef étrangère
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;

    @Id
    @Column(name = "version")
    private String version;


    //Constructeur avec paramètres

    public BiereId(String nomMarque, String version) {

        this.marque = new Marque(nomMarque);
        this.version = version;

    }

    //Constructeur vide
    public BiereId() {
    }


    //Equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiereId)) return false;
        BiereId biereId = (BiereId) o;
        return marque.equals(biereId.marque) && version.equals(biereId.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marque, version);
    }


    //Getters et Setters


    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}


