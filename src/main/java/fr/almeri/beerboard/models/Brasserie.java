package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table(name="brasserie")
public class Brasserie {

    private static final Logger LOGGER = Logger.getLogger(Brasserie.class.getName());

    @Id
    @Column(name="code_brasserie")
    private  String codeBrasserie;


    @Column(name="nom_brasserie")
    private String nomBrasserie;

    @Column(name="ville")
    private  String ville;

    //Fetchtype.LAZY opermet de ne pas tout charger en même temps et de faire des re^quetes sépaées
    //JsonIgnoreProperties

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="region") //permet de définir la colonne qui possède une clef étrangère
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    //Constructeur vide

    public Brasserie(){

    }

    //Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brasserie)) return false;
        Brasserie brasserie = (Brasserie) o;
        return getCodeBrasserie().equals(brasserie.getCodeBrasserie()) && getNomBrasserie().equals(brasserie.getNomBrasserie()) && getVille().equals(brasserie.getVille()) && getRegion().equals(brasserie.getRegion());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getCodeBrasserie(), getNomBrasserie(), getVille(), getRegion());
    }


    //To string

    @Override
    public String toString() {
        return "Brasserie{" +
                "nomBrasserie='" + nomBrasserie + '\'' +
                '}';
    }

    //Getters et setters

    public String getCodeBrasserie() {
        return this.codeBrasserie;
    }

    public void setCodeBrasserie(String codeBrasserie) {
        this.codeBrasserie = codeBrasserie;
    }

    public String getNomBrasserie() {
        return this.nomBrasserie;
    }

    public void setNomBrasserie(String nomBrasserie) {
        this.nomBrasserie = nomBrasserie;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
