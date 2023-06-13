package com.example.cube5.controller;

import com.example.cube5.domain.Incident;
import com.example.cube5.services.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/incidents")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public String getIncident(Model model) {
        model.addAttribute("incidents", incidentService.findAll());
        return "incidentList";
    }

//    @GetMapping("/incidents")
//    public String getAllIncidents(Model model) {
//        model.addAttribute("incidents", incidentService.findAll());
//        return "home";
//    }

    @GetMapping("/create")
    public String createIncidentForm(Model model) {
        model.addAttribute("incident", new Incident());
        return "createIncident";
    }

    @PostMapping("/create")
    public String createIncident(@Validated Incident incident, BindingResult result) {
        if (result.hasErrors()) {
            return "createIncident";
        }
        incidentService.save(incident);
        return "redirect:/incidents";
    }

    @GetMapping("/edit/{id}")
    public String editIncidentForm(@PathVariable("id") Long id, Model model) {
        Incident incidentFound = incidentService.findById(id);
        if (incidentFound != null) {
            model.addAttribute("incident", incidentFound);
            return "editIncident";
        } else {
            return "redirect:/incidents";
        }
    }

    @PostMapping("/edit/{id}")
    public String editIncident(@PathVariable("id") Long id, @Validated Incident incident, BindingResult result) {
        if (result.hasErrors()) {
            return "editIncident";
        }
        incident.setId(id);
        incidentService.save(incident);
        return "redirect:/incidents";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncident(@PathVariable("id") Long id) {
        incidentService.deletedById(id);
        return "redirect:/incidents";
    }
}
