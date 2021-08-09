/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;

import mcc53.client.app.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author haikal
 */
@Controller
@RequestMapping("/pokemon")
public class PokemonController {
    
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("pokemons", pokemonService.getAll());
        return "pokemon/index";
    }
    
}
