package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import fr.almeri.beerboard.models.Marque;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import fr.almeri.beerboard.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
public class BeersController {


    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private TypeRepository typeRepository;

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
    public String getPageAddBeer(Model pModel, @RequestParam(required = false) String marque, @RequestParam(required = false) String version) {

        //Je crée la clef primaire à l'aide de marque et version qui se trouvent dans l'url afin de trouver le détail d'une bière
//        BiereId primaryKey = new BiereId(marque, version);
//        //Je récupère en BDD la bière qui a pour clef primaire la marque et la version issues de l'url
//        Biere biere = biereRepository.findById(primaryKey).orElseThrow();
//
//        //J'envoie la bière à afficher à la Vue
//
//        pModel.addAttribute("detailBiere", biere);

        //à la création & modification
        pModel.addAttribute("listeMarque", marqueRepository.findAll());
        pModel.addAttribute("listeType", typeRepository.findAll());

        if (marque == null && version == null) {

            pModel.addAttribute("biere", new Biere());
            pModel.addAttribute("update", "false");

        } else {

            Biere biere = biereRepository.findById(new BiereId(marque, version)).orElseThrow();
            pModel.addAttribute("biere", biere);
            pModel.addAttribute("update", "true");
        }
        return "add-beer";  //nom de la page html que je renvoie

    }

    @PostMapping("/add-beer")
    public String addBeerInDatabase(Model pModel, @ModelAttribute Biere uneBiere, RedirectAttributes message, String update) {

        if ("false".equals(update)) {

            //Si Id existe alors on n'ajoute rien
            if (biereRepository.existsById(new BiereId(uneBiere.getMarque().getNomMarque(), uneBiere.getVersion()))) {

                message.addFlashAttribute("messagehtml", " Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle version.");
                return "redirect:/add-beer";

            } else {
                //save = create si l'Id n'est pas connu en BDD
                biereRepository.save(uneBiere);

                message.addFlashAttribute("messagehtml", " La biere " + uneBiere.getMarque().getNomMarque() + " a été enregistrée avec succès.");

                //redirect: appelle le @GetMapping dont l'url est /breweries
                return "redirect:/beers";

            }
        } else {
            biereRepository.save(uneBiere);

            message.addFlashAttribute("messagehtml", " La biere " + uneBiere.getMarque().getNomMarque() + " a été modifiée avec succès.");

            //redirect: appelle le @GetMapping dont l'url est /breweries
            return "redirect:/beers";


        }

    }
}
