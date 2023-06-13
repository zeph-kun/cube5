package com.example.cube5.controller;

import com.example.cube5.domain.Incident;
import com.example.cube5.domain.SuperHero;
import com.example.cube5.services.IncidentService;
import com.example.cube5.services.SuperHeroService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final IncidentService incidentService;
    private final SuperHeroService superHeroService;

    public HomeController(IncidentService incidentService, SuperHeroService superHeroService) {
        this.incidentService = incidentService;
        this.superHeroService = superHeroService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Incident> existingIncidents = incidentService.findAll();
        model.addAttribute("existingIncidents", existingIncidents);
        return "home";
    }

    @PostMapping("/addIncident")
    public String handleIncidentForm(@ModelAttribute Incident incident, Model model) {
        String incidentCity = incident.getCity();
        Double incidentLatitude = incident.getLatitude();
        Double incidentLongitude = incident.getLongitude();
        List<SuperHero> heroes = superHeroService.findHeroesForIncident(incident);
        model.addAttribute("heroes", heroes);
        return "incidentHeroes";
    }

}
