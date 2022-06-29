package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Pays;
import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getPageLogin(Model pModel, HttpSession session) {
        //l'objet Model est instancié lorsqu'on appelle la méthode  par Spring
        //Il permet d'envoyer des données dynamiques à la page

        if (session.getAttribute("auth") != null) {
            return "redirect:/";

        } else {
            pModel.addAttribute("user",new User());
            return "login";
        }
    }

    @PostMapping("/login")
    public String Authentifier(@ModelAttribute User user, HttpSession session, Model model) throws NoSuchAlgorithmException, NoSuchFieldException, NoSuchProviderException {

        // Vérification du mot de passe

        boolean validPw = this.checkPassword(user);


        if (validPw) {
            session.setAttribute("auth", user);
            session.setMaxInactiveInterval(600);
            return "redirect:/";

        } else {
            return "redirect:/login";
        }


    }

    public boolean checkPassword(User user) {
        //On cherche le user en BDD à l'aide de son login
        User u = userRepository.findByLogin(user.getLogin());
        if (u == null) {
            return false;
        }

        //On hash le mdp saisi par l'utilisateur avec le sablage récupéré en BDD

        String newPass = hashMD5withSalt(user.getPassword(), u.getSalt());
        return newPass.equals(u.getPassword());
    }

    static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);

        return salt;
    }


    static String hashMD5withSalt(String passwordToHash, byte[] salt) {

        String generatedPassword = null;
        //Create MessageDigest instance for MD5

        MessageDigest md = null;
        try {

            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Add password bytes to digest
        md.update(salt);
        //Get the hash's bytes
        byte[] bytes = md.digest(passwordToHash.getBytes());
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

        }
        //Get complete hashed password in hex format
        generatedPassword = sb.toString();
        return generatedPassword;
    }

}
