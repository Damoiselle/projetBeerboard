package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie,String> {


//    @Query("SELECT count(distinct b.region.nomRegion)  FROM Brasserie b asc")
//    public ArrayList<String> getNbRegions();

    @Query("SELECT Distinct p.region.nomRegion FROM Brasserie p ORDER BY p.region.nomRegion ASC")
    public ArrayList<String> getRegionsBrasserie();
    @Query("SELECT COUNT(p.codeBrasserie) FROM Brasserie p group by p.region.nomRegion ORDER BY p.region.nomRegion ASC")
    public ArrayList<Integer> getNbBrasserieRegion();
    

}
