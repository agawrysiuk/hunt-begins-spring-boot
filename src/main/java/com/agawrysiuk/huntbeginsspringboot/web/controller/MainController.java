package com.agawrysiuk.huntbeginsspringboot.web.controller;

import com.agawrysiuk.huntbeginsspringboot.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final GameService gameService;

    public MainController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/")
    private String getHome() {
        return "home";
    }
}
