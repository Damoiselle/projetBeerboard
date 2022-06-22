package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;


@Entity
@Table
@IdClass(BiereId.class) //permet de spécifier que la clef primaire est représentée par l'objet BiereId
public class Biere implements Serializable {

    //Attributs

    private static final Logger LOGGER = Logger.getLogger(Biere.class.getName());
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_marque") //permet de définir la colonne qui possède une clef étrangère
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;

    @Id
    @Column(name = "version")
    private String version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_type") //permet de définir la colonne qui possède une clef étrangère
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type type;

    @Column(name = "couleur_biere")
    private String couleurBiere;

    @Column(name = "taux_alcool")
    private Double tauxAlcool;

    @Column(name = "caracteristiques")
    private String caracteristiques;


//    private String noTypeStr;


    //Constructeur vide

    public Biere() {

    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biere)) return false;
        Biere biere = (Biere) o;
        return getMarque().equals(biere.getMarque()) && getVersion().equals(biere.getVersion()) && getType().equals(biere.getType()) && getCouleurBiere().equals(biere.getCouleurBiere()) && getTauxAlcool().equals(biere.getTauxAlcool()) && getCaracteristiques().equals(biere.getCaracteristiques());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarque(), getVersion(), getType(), getCouleurBiere(), getTauxAlcool(), getCaracteristiques());
    }

    //Getters et setters

    public Marque getMarque() {
        return this.marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCouleurBiere() {
        return this.couleurBiere;
    }

    public void setCouleurBiere(String couleurBiere) {
        this.couleurBiere = couleurBiere;
    }

    public Double getTauxAlcool() {
        return this.tauxAlcool;
    }

    public void setTauxAlcool(Double tauxAlcool) {
        this.tauxAlcool = tauxAlcool;
    }

    public String getCaracteristiques() {
        return this.caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }




    //    public String getNoTypeStr() {
//        return this.noTypeStr;
//    }
//
//    public void setNoTypeStr(String noTypeStr) {
//        this.noTypeStr = noTypeStr;
//    }

}
