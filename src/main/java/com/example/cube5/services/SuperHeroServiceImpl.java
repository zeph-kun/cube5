package com.example.cube5.services;

import com.example.cube5.domain.Incident;
import com.example.cube5.domain.SuperHero;
import com.example.cube5.repository.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {
    private final SuperHeroRepository superHeroRepository;

    public SuperHeroServiceImpl(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    @Override
    public List<SuperHero> findAll() {
        return (List<SuperHero>) superHeroRepository.findAll();
    }

    @Override
    public SuperHero findById(Long id) {
        return superHeroRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SuperHero superHero) {
        superHeroRepository.save(superHero);
    }

    @Override
    public void deletedById(Long id) {
        superHeroRepository.deleteById(id);
    }

    public List<SuperHero> findHeroesForIncident(Incident incident) {
        List<SuperHero> allHeroes = this.findAll();
        System.out.println(allHeroes);
        System.out.println("Incident city: " + incident.getCity());
        System.out.println("Incident latitude: " + incident.getLatitude());
        System.out.println("Incident longitude: " + incident.getLongitude());
        return allHeroes.stream()
                .filter(hero -> hero.canHandleIncident(incident))
                .filter(hero -> hero.isInRadius(incident.getLatitude(), incident.getLongitude()))
                .collect(Collectors.toList());
    }
}
