package com.example.cube5.repository;

import com.example.cube5.domain.SuperHero;
import org.springframework.data.repository.CrudRepository;

public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

}
