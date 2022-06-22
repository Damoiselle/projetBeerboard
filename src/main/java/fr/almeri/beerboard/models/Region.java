package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table(name="region")
public class Region {

    private static final Logger LOGGER = Logger.getLogger(Region.class.getName());


    @Id
    @Column(name="nom_region")
    private  String nomRegion;

    @Column(name="nom_pays")
    private  String nomPays;


    //Constructeur vide

    public Region(){

    }

    //Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return nomRegion.equals(region.nomRegion) && nomPays.equals(region.nomPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomRegion, nomPays);
    }


    //toString

    @Override
    public String toString() {
        return         nomRegion + " - " + nomPays ;
    }
    //Getters et setters

    public String getNomRegion() {
        return this.nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public String getNomPays() {
        return this.nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }




}
