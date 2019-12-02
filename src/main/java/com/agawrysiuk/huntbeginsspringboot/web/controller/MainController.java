package com.agawrysiuk.huntbeginsspringboot.web.controller;

import com.agawrysiuk.huntbeginsspringboot.model.GameMap;
import com.agawrysiuk.huntbeginsspringboot.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final GameService gameService;

    public MainController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/")
    public String getHome() {
        return "home";
    }

    @RequestMapping(value = "/", params = "create", method = RequestMethod.POST)
    public String getMap(Model model) {
        GameMap gameMap = gameService.getMap();
        if (gameMap != null) {
            model.addAttribute("map",gameMap);
            return "map";
        } else {
            model.addAttribute("message", "Something went wrong while creating a map.");
            return "home";
        }

    }

}
