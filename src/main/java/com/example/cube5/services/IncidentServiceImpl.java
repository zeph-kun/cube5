package com.example.cube5.services;

import com.example.cube5.domain.Incident;
import com.example.cube5.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<Incident> findAll() {
        return (List<Incident>) incidentRepository.findAll();
    }

    @Override
    public Incident findById(Long id) {
        return incidentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Incident incident) {
        incidentRepository.save(incident);
    }

    @Override
    public void deletedById(Long id) {
        incidentRepository.deleteById(id);
    }
}
