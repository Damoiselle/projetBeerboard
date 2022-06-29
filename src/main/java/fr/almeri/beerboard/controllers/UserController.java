package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static fr.almeri.beerboard.controllers.LoginController.getSalt;
import static fr.almeri.beerboard.controllers.LoginController.hashMD5withSalt;

public class UserController {

    @Autowired
    UserRepository userRepository;



   }
