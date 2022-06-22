package fr.almeri.beerboard.controllers;


import fr.almeri.beerboard.models.Pays;
import fr.almeri.beerboard.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PaysController {
    //Permet de définir une route appelée avec la méthode GET (directement via l'URL)
    //localhost:8888/pays

    @Autowired
    private PaysRepository paysRepository;


    @GetMapping("/pays")
    public String getPagePays(Model pModel) {
        //l'objet Model est instancié lorsqu'on appelle la méthode getPageExample par Spring
        //Il permet d'envoyer des données dynamiques à la page

        ArrayList<Pays> listPaysFromDatabase = (ArrayList<Pays>) paysRepository.findAll();
        pModel.addAttribute("listPays", listPaysFromDatabase);

        return "pays";

    }
}


