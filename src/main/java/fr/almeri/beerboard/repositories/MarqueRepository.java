package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MarqueRepository extends CrudRepository<Brasserie,String> {



    @Query("SELECT COUNT(m.nomMarque) FROM Marque m GROUP BY m.brasserie.codeBrasserie ORDER BY m.brasserie.nomBrasserie ASC")
    public ArrayList<Integer> getNbMarquesBrasserieAsc();

    @Query("SELECT m.brasserie.nomBrasserie FROM Marque m GROUP BY m.brasserie.codeBrasserie ORDER BY m.brasserie.nomBrasserie ASC")
    public ArrayList<String> getNomBrasserieAsc();
}
