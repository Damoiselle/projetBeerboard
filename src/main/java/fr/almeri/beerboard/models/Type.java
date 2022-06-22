package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table
public class Type {

    private static final Logger LOGGER = Logger.getLogger(Type.class.getName());

    @Id
    @Column(name="no_type")
    private Integer noType;

    @Column(name="nom_type")
    private String nomType;

    @Column(name="description")
    private String description;

    @Column(name="fermentation")
    private String fermentation;

    @Column(name="commentaire")
    private String commentaire;


//Constructeur vide

    public Type(){

    }
 // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;
        Type type = (Type) o;
        return getNoType().equals(type.getNoType()) && getNomType().equals(type.getNomType()) && getDescription().equals(type.getDescription()) && getFermentation().equals(type.getFermentation()) && getCommentaire().equals(type.getCommentaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoType(), getNomType(), getDescription(), getFermentation(), getCommentaire());
    }

    //toString


    @Override
    public String toString() {
        return nomType ;
    }


    //Renvoie l'attribut noType en String
    public String getNoTypeStr() {
        return String.valueOf(this.noType);
    }

    //Set l'attribut noType à partir d'une chaîne de caractères
    public void setNoTypeStr(String pNoType) {
        this.noType = Integer.parseInt(pNoType);

    }

    // Getters et setters
    public Integer getNoType() {
        return this.noType;
    }

    public void setNoType(Integer noType) {
        this.noType = noType;
    }

    public String getNomType() {
        return this.nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFermentation() {
        return this.fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }



}
