package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class BreweriesController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/breweries")
    public String getPageBreweries(Model pModel) {

        ArrayList<Brasserie> listBrasseriesFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listBrasseries", listBrasseriesFromDatabase);

        return "breweries";
    }


    @GetMapping("/see-brewery/{code}")
    public String getPageBrewery(Model pModel, @PathVariable(required = true) String code) {

        //listBrasseries = indice, listBrasseriesFromDatabase = objet à afficher
//        ArrayList<Brasserie> listBrasseriesFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
//        pModel.addAttribute("listBrasseries", listBrasseriesFromDatabase);


        //Je récupère la brasserie via le repository avec un findById
        //et je passe en paramètres ce que je récupère via l'URL
        //orElseThrow() va déclencher une exception si on ne trouve pas la brasserie & non arrêter le programme

        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("detailBrasserie", brasserie);
        pModel.addAttribute("detailBiere", biereRepository.getMarqueVersionByBrasserie(code));
        return "brewery";
    }

    @GetMapping("/add-brewery") //soit /add-brewery en création soit /add-brewery?code=ache en modif
    public String getPageAddBrewery(Model pModel, @RequestParam(required = false) String code) {


        //Je récupère la brasserie via le repository avec un findById
        //et je passe en paramètres ce que je récupère via l'URL
        //orElseThrow() va déclencher une exception si on ne trouve pas la brasserie & non arrêter le programme

        pModel.addAttribute("listeRegion", regionRepository.findAll());

        if(code == null){
            //quand je veux ajouter une brasserie, je crée un élément Brasserie vide que j'envoie à l'html qui sera rempli via le formulaire
            pModel.addAttribute("brasserie", new Brasserie());

        }else{

            Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
            pModel.addAttribute("brasserie", brasserie);
            pModel.addAttribute("update", true);

        }

//        Brasserie brasserie = brasserieRepository.;
//        pModel.addAttribute("detailBrasserie", brasserie);
        return "add-brewery";
    }


    @PostMapping("/add-brewery")
    public String addBreweryInDatabase(Model pModel, @ModelAttribute Brasserie uneBrasserie, RedirectAttributes message){

        //Si Id existe alors on n'ajoute rien
        if(brasserieRepository.existsById(uneBrasserie.getCodeBrasserie())){

            message.addFlashAttribute("messagehtml"," L’identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette brasserie n’existe pas déjà.");
            return "redirect:/add-brewery";

        }else {
            //save = create si l'Id n'est pas connu en BDD
            brasserieRepository.save(uneBrasserie);

            message.addFlashAttribute("messagehtml"," La brasserie " +uneBrasserie.getNomBrasserie() + " a été enregistrée avec succès.");

            //redirect: appelle le @GetMapping dont l'url est /breweries
            return "redirect:/breweries";

        }
    }




}


