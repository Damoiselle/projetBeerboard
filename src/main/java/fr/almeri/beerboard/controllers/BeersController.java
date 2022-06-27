package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import fr.almeri.beerboard.repositories.BiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class BeersController {

    @Autowired
    private BiereRepository biereRepository;

    @GetMapping("/beers")
    public String getPageBeers(Model pModel) {


        ArrayList<Biere> listBieresFromDatabase = (ArrayList<Biere>) biereRepository.findAll(); //je récupère en BDD l'ensemble des bières
        pModel.addAttribute("listBieres", listBieresFromDatabase);


        return "beers";
    }

    @GetMapping("/see-beer")  //nom de l'url dans le a href : /see-beer?marque={valeurMarque}&version={valeurVersion}
    // la clef passée dans l'url doit avoir le même nom que le paramètre
    public String getPageSeeBeer(Model pModel, @RequestParam String marque, @RequestParam String version) {

        //Je crée la clef primaire à l'aide de marque et version qui se trouvent dans l'url afin de trouver le détail d'une bière
        BiereId primaryKey = new BiereId(marque, version);
        //Je récupère en BDD la bière qui a pour clef primaire la marque et la version issues de l'url
        Biere biere = biereRepository.findById(primaryKey).orElseThrow();

        //J'envoie la bière à afficher à la Vue

        pModel.addAttribute("detailBiere", biere);


        return "beer";  //nom de la page html que je renvoie
    }

    @GetMapping("/add-beer")  //nom de l'url dans le a href : /add-beer
    // la clef passée dans l'url doit avoir le même nom que le paramètre
    public String getPageAddBeer(Model pModel) {

        //Je crée la clef primaire à l'aide de marque et version qui se trouvent dans l'url afin de trouver le détail d'une bière
//        BiereId primaryKey = new BiereId(marque, version);
//        //Je récupère en BDD la bière qui a pour clef primaire la marque et la version issues de l'url
//        Biere biere = biereRepository.findById(primaryKey).orElseThrow();
//
//        //J'envoie la bière à afficher à la Vue
//
//        pModel.addAttribute("detailBiere", biere);


        return "add-beer";  //nom de la page html que je renvoie
    }

}
