package com.agawrysiuk.huntbeginsspringboot.web.controller;

import com.agawrysiuk.huntbeginsspringboot.repository.GameMapDto;
import com.agawrysiuk.huntbeginsspringboot.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("position")
public class GalleryController {

    private final GameService gameService;

    public GalleryController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/", params = "gallery", method = RequestMethod.POST)
    public String showGallery(Model model) {
        int position = 0;
        return getGalleryAttributes(model, position);
    }

    @RequestMapping(value = "/", params = "right", method = RequestMethod.POST)
    public String showNext(Model model,@ModelAttribute("position") int position) {
        position++;
        return getGalleryAttributes(model, position);
    }

    @RequestMapping(value = "/", params = "left", method = RequestMethod.POST)
    public String showPrevious(Model model,@ModelAttribute("position") int position) {
        position--;
        return getGalleryAttributes(model, position);
    }

    @RequestMapping(value = "/", params = "main", method = RequestMethod.POST)
    public String goMain() {
        return "redirect:/";
    }

    @RequestMapping(value = "/all", method=RequestMethod.GET)
    @ResponseBody
    public List<GameMapDto> showAll() {
        return gameService.findAll();
    }

    private String getGalleryAttributes(Model model, int position) {
        boolean booleanLeft = position==0;
        List<GameMapDto> list = gameService.findAll();
        boolean booleanRight = position==list.size()-1;
        model.addAttribute("booleanLeft",booleanLeft);
        model.addAttribute("booleanRight",booleanRight);
        model.addAttribute("position", position);
        model.addAttribute("map",list.get(position));
        return "gallery";
    }


}
