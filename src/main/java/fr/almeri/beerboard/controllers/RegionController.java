//package fr.almeri.beerboard.controllers;
//
//import fr.almeri.beerboard.models.Brasserie;
//import fr.almeri.beerboard.models.Region;
//import fr.almeri.beerboard.repositories.RegionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//
//@Controller
//public class RegionController {
//    //Permet de définir une route appelée avec la méthode GET (directement via l'URL)
//    //localhost:8888/region
//
//
//    @Autowired
//    private RegionRepository regionRepository;
//
//
//    @GetMapping("/example")
//    public String getPageRegion(Model pModel) {
//
//        ArrayList<Region> listRegionsFromDatabase = (ArrayList<Region>) regionRepository.findAll();
//        pModel.addAttribute("listRegions", listRegionsFromDatabase);
//
//        return "region";
//
//
//    }
//}
//
//
//
//}
