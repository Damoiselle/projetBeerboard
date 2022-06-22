package fr.almeri.beerboard.repositories;
import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface BiereRepository extends CrudRepository<Biere, BiereId> { //Clef primaire de type BiereId


@Query("SELECT COUNT(b.version) FROM Biere b group by b.marque.nomMarque order by count(b.version) DESC")
public ArrayList<Integer> getNbVersions();
 @Query("SELECT b.marque.nomMarque FROM Biere b group by b.marque.nomMarque order by count(b.version) DESC")
    public ArrayList<String> getNomsMarques();

 @Query("SELECT count(b.marque.nomMarque) FROM Biere b group by b.tauxAlcool ORDER BY b.tauxAlcool DESC")
 public ArrayList<Integer> getNbBieres();
 @Query("SELECT b.tauxAlcool FROM Biere b group by b.tauxAlcool ORDER BY b.tauxAlcool DESC")
 public ArrayList<String> getTxAlcool();

 @Query("SELECT b FROM Biere b WHERE b.marque.brasserie.codeBrasserie = :code ORDER BY b.marque.nomMarque, b.version ASC")
 public ArrayList<Biere> getMarqueVersionByBrasserie(String code);
}



