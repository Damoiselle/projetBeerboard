package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.logging.Logger;

//@Entity & &Table : j'indique la table qui correspond à l'objet Pays
@Entity
@Table(name="pays")
public class Pays {

    private static final Logger LOGGER = Logger.getLogger(Pays.class.getName());

//Attributs
    //@Id j'indique que l'attribut ci-après est une clef primaire

    @Id
    @Column(name="nom_pays")
    private  String nomPays;

    //@Column je fais la liaison entre l'attribut et le nom de la colonne
    @Column(name="consommation")
    private  Double consommation;
    @Column(name="production")
    private  Double production;


//Constructeur vide

    public Pays(){

    }

    // Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pays)) return false;
        Pays pays = (Pays) o;
        return nomPays.equals(pays.nomPays) && consommation.equals(pays.consommation) && production.equals(pays.production);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomPays, consommation, production);
    }

    //Getters et setters


    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public Double getConsommation() {
        return consommation;
    }

    public void setConsommation(Double consommation) {
        this.consommation = consommation;
    }

    public Double getProduction() {
        return production;
    }

    public void setProduction(Double production) {
        this.production = production;
    }





}
