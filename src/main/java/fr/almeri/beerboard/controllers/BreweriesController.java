package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BreweriesController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @GetMapping("/breweries")
    public String getPageBreweries(Model pModel) {

        ArrayList<Brasserie> listBrasseriesFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listBrasseries", listBrasseriesFromDatabase);

        return "breweries";
    }


    @GetMapping("/see-brewery/{code}")
    public String getPageBreweries(Model pModel, @PathVariable(required = true) String code) {

        //listBrasseries = indice, listBrasseriesFromDatabase = objet à afficher
//        ArrayList<Brasserie> listBrasseriesFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
//        pModel.addAttribute("listBrasseries", listBrasseriesFromDatabase);


        //Je récupère la brasserie via le repository avec un findById
        //et je passe en paramètres ce que je récupère via l'URL
        //orElseThrow() va déclencher une exception si on ne trouve pas la brasserie & non arrêter le programme
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        return "brewery";
    }

    //URL appelée  : /see-brewery1?code=variable&toto=variable2
    @GetMapping("/see-brewery1")
    public String getBreweryByCode(Model pModel, @RequestParam(required = true) String
            code, @RequestParam(required = false) String toto) {
        //Le nom donné au paramètre dans le @RequestParam doit être le même que la clef utilisée dans l'URL

        return "brewery";
    }


}


