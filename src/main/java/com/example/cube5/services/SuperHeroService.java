package com.example.cube5.services;

import com.example.cube5.domain.Incident;
import com.example.cube5.domain.SuperHero;

import java.util.List;

public interface SuperHeroService {
    List<SuperHero> findAll();
    SuperHero findById(Long id);
    void save(SuperHero superHero);
    void deletedById(Long id);
    List<SuperHero> findHeroesForIncident(Incident incident);
}
