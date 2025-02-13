package com.project.PizzaOrdering.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.PizzaOrdering.entities.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Integer> {
    
}
