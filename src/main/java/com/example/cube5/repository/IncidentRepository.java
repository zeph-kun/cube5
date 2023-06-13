package com.example.cube5.repository;

import com.example.cube5.domain.Incident;
import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Long> {
}
