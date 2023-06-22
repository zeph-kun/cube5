package com.example.cube5.controller;

import com.example.cube5.domain.Incident;
import com.example.cube5.domain.SuperHero;
import com.example.cube5.services.SuperHeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heroes")
public class SuperHeroController {
    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    public String getIncident(Model model) {
        model.addAttribute("heroes", superHeroService.findAll());
        return "heroList";
    }

//    @GetMapping("/create")
//    public String createHeroForm(Model model) {
//        model.addAttribute("hero", new SuperHero());
//        return "createHero";
//    }

    @PostMapping("/create")
    public String createHero(@Validated SuperHero superHero, BindingResult result) {
        if (result.hasErrors()) {
            return "createHero";
        }
        superHeroService.save(superHero);
        return "redirect:/heroes";
    }

    @GetMapping("/edit/{id}")
    public String editHeroForm(@PathVariable("id") Long id, Model model) {
        SuperHero heroFound = superHeroService.findById(id);
        if (heroFound != null) {
            model.addAttribute("hero", heroFound);
            return "editHero";
        } else {
            return "redirect:/heroes";
        }
    }

    @PostMapping("/edit/{id}")
    public String editHero(@PathVariable("id") Long id, @Validated SuperHero superHero, BindingResult result) {
        if (result.hasErrors()) {
            return "editHero";
        }
        superHero.setId(id);
        superHeroService.save(superHero);
        return "redirect:/heroes";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncident(@PathVariable("id") Long id) {
        superHeroService.deletedById(id);
        return "redirect:/heroes";
    }
}
