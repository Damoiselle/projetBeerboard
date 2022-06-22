package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.models.Pays;
//import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.PaysRepository;
import fr.almeri.beerboard.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


//Permet d'indiquer ) SpringBoot qu'il y aura un routage dans ce fichier
@Controller
public class ExampleController {
    //Permet de définir une route appelée avec la méthode GET (directement via l'URL)
    //localhost:8888/example


    //permet d'instancier automatiquement PaysRepository si besoin




    @Autowired
    private RegionRepository regionRepository;



    @GetMapping("/example")
    public String getPageExample(Model pModel) {
        //l'objet Model est instancié lorsqu'on appelle la méthode getPageExample par Spring
        //Il permet d'envoyer des données dynamiques à la page
        //la méthode addAttribute prend 2 paramètres : le nom de variable dans le html et la donnée à afficher

//        pModel.addAttribute("prenom", "Marie");


//        Pays pays = new Pays();
//        pays.setNomPays("France");
//        pays.setConsommation(145.0);
//        pays.setProduction(190.9);
//
//        Pays pays1 = new Pays();
//        pays1.setNomPays("Angleterre");
//        pays1.setConsommation(250.0);
//        pays1.setProduction(190.9);
//
//        Pays pays2 = new Pays();
//        pays2.setNomPays("Espagne");
//        pays2.setConsommation(130.0);
//        pays2.setProduction(100.0);
//
//        ArrayList<Pays> listPays = new ArrayList<>();
//        listPays.add(pays);
//        listPays.add(pays1);
//        listPays.add(pays2);
//
//        pModel.addAttribute("pays", pays);





        //Méthode permettant d'indiquer quelle page HTML on renvoie
        //Ici example.html se trouve dans le répertoire/templates
        return "example";


    }
}
