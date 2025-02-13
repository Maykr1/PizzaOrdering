package com.project.PizzaOrdering.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.PizzaOrdering.entities.Pizza;
import com.project.PizzaOrdering.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping()
    public Iterable<Pizza> getPizzas() {
        Iterable<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas;
    }

    @PostMapping()
    public String createNewPizza(@RequestParam String name, @RequestParam Double price, @RequestParam String size, @RequestParam List<String> toppings) {
        Pizza newPizza = new Pizza(null, name, price, size, toppings);
        pizzaRepository.save(newPizza);
        return "New Pizza has been added to the menu!";
    }

    /*
    Possibly do this instead:
    @PostMapping("/addPizza")
    public Pizza createNewPizza(@RequestBody Pizza pizza) {
        Pizza newPizza = this.pizzaRepository.save(pizza);
        return newPizza;
    }
    */
    
    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable("id") Integer id, @RequestBody Pizza pizza) {
        Optional<Pizza> pizzaToUpdateOptional = this.pizzaRepository.findById(id);
        if (!pizzaToUpdateOptional.isPresent()) {
            return null;
        }
        Pizza pizzaToUpdate = pizzaToUpdateOptional.get();
        if (pizza.getName() != null) {
            pizzaToUpdate.setName(pizza.getName());
        }
        if (pizza.getPrice() != null) {
            pizzaToUpdate.setPrice(pizza.getPrice());
        }
        if (pizza.getSize() != null) {
            pizzaToUpdate.setSize(pizza.getSize());
        }
        if (pizza.getToppings() != null) {
            pizzaToUpdate.setToppings(pizza.getToppings());
        }

        Pizza updatedPizza = this.pizzaRepository.save(pizzaToUpdate);
        return updatedPizza;
    }
    
    @DeleteMapping("/{id}")
    public Pizza deletePizza(@PathVariable("id") Integer id) {
        Optional<Pizza> pizzaToDeleteOptional = this.pizzaRepository.findById(id);
        if (!pizzaToDeleteOptional.isPresent()) {
            return null;
        }

        Pizza pizzaToDelete = pizzaToDeleteOptional.get();
        this.pizzaRepository.delete(pizzaToDelete);
        return pizzaToDelete;
    }
}
