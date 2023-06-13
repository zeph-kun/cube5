package com.example.cube5.controller;

import com.example.cube5.domain.Incident;
import com.example.cube5.services.IncidentService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final IncidentService incidentService;

    public HomeController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Incident> existingIncidents = incidentService.findAll();
        model.addAttribute("existingIncidents", existingIncidents);
        return "home";
    }
}
