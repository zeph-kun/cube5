package com.example.cube5.services;

import com.example.cube5.domain.Incident;

import java.util.List;

public interface IncidentService {
    List<Incident> findAll();
    Incident findById(Long id);
    void save(Incident incident);
    void deletedById(Long id);
}
