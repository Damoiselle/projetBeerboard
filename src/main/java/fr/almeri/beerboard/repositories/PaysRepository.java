package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface PaysRepository extends CrudRepository<Pays,String> {

    //  SELECT nom_pays FROM Pays ORDER BY nom_pays asc,
    //Obligation de définir un alias pour la table

    @Query("SELECT p.nomPays FROM Pays p ORDER BY p.nomPays asc")
    public ArrayList<String> getNomPaysAsc();


    @Query("SELECT p.consommation FROM Pays p ORDER BY p.nomPays ASC ")
    public ArrayList<Integer> getConsommationStats();

    @Query("SELECT p.production FROM Pays p ORDER BY p.nomPays ASC ")
    public ArrayList<Integer> getProductionStats();

}
