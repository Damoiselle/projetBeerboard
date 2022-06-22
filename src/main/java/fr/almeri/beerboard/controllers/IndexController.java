package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import fr.almeri.beerboard.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class IndexController {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @GetMapping("/")
    public String home(Model pModel, HttpSession pSession) {
        pModel.addAttribute("bieres", 328);
        pModel.addAttribute("brasseries", 99);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

        //pieChart

        pModel.addAttribute("labelsPieChart", brasserieRepository.getRegionsBrasserie());
        pModel.addAttribute("datasPieChart", brasserieRepository.getNbBrasserieRegion());

        //AreaChart
        pModel.addAttribute("labelsAreaChart", biereRepository.getTxAlcool());
        pModel.addAttribute("datasAreaChart", biereRepository.getNbBieres());

        //Consommation & production de bières par pays
        ArrayList<String> labelsBarChart = paysRepository.getNomPaysAsc();
        pModel.addAttribute("labelsBarChart", labelsBarChart);
        ArrayList<Integer> datasConsommation = paysRepository.getConsommationStats();
        pModel.addAttribute("datasConsommation", datasConsommation);
        //deuxième méthode
        pModel.addAttribute("datasProduction", paysRepository.getProductionStats());


        pModel.addAttribute("labelsBarChart1", marqueRepository.getNomBrasserieAsc());
        pModel.addAttribute("datasBarChart1", marqueRepository.getNbMarquesBrasserieAsc());

        ArrayList<String> labelsBarChart2 = new ArrayList<>();
//        labelsBarChart2.add("Marque 1");
//        labelsBarChart2.add("Marque 2");
        pModel.addAttribute("labelsBarChart2", biereRepository.getNomsMarques());
        pModel.addAttribute("datasBarChart2", biereRepository.getNbVersions());

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession) {
        return "redirect:/";
    }
}
