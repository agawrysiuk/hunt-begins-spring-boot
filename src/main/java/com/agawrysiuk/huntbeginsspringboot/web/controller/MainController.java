package com.agawrysiuk.huntbeginsspringboot.web.controller;

import com.agawrysiuk.huntbeginsspringboot.repository.GameMapDto;
import com.agawrysiuk.huntbeginsspringboot.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@SessionAttributes("map")
public class MainController {

    private final GameService gameService;

    public MainController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(value = "/", params = "create", method = RequestMethod.POST)
    public String getMap(Model model) {
        GameMapDto map = gameService.getMap();
        if (map != null) {
            model.addAttribute("map",map);
            return "map";
        } else {
            model.addAttribute("message", "Something went wrong while creating a map.");
            return "home";
        }

    }

    @RequestMapping(value = "/", params = "save", method = RequestMethod.POST)
    public ModelAndView saveMap(Model model, @ModelAttribute("map") GameMapDto map) {
        log.info("Saved {}", model.getAttribute("map"));
        try {
            log.info(map.getGameListMap().toString());
            gameService.addMapToRepository(map);
        } catch (NullPointerException e) {
            log.info("map.getGameListMap() is null");
        }
        return new ModelAndView("home","message","Map successfully added to repository.");
    }


}
