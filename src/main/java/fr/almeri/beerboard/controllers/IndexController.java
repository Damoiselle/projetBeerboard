package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static fr.almeri.beerboard.controllers.LoginController.getSalt;
import static fr.almeri.beerboard.controllers.LoginController.hashMD5withSalt;


@Controller
public class IndexController {

//    @Autowired
//    private UserRepository userRepository;
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

        if (pSession.getAttribute("auth") != null)
        {
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

        //Ajout d'un utilisateur pour tester la connexion
//        String pass = "pass";
//        try
//
//        {
//            byte[] salt = getSalt();
//            String hashPass = hashMD5withSalt(pass, salt);
//            User u = new User("mdagniau@almeri.fr", hashPass , salt);
//            userRepository.save(u);
//        }
//        catch(NoSuchAlgorithmException e) {
//            e.printStackTrace();
//
//        }
//        catch(NoSuchProviderException e){
//            e.printStackTrace();
//        }


        return "index";
        } else {
            return "login";
        }
        }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession) {
        return "redirect:/";
    }

}
