package com.example.finalproject.controllers;

import com.example.finalproject.exceptions.ApiErrorException;
import com.example.finalproject.Entities.IngredientEntityAssembler;
import com.example.finalproject.models.Ingredient;
import com.example.finalproject.repos.IngredientRepo;
import com.example.finalproject.utils.AsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RequestMapping("/api/ingredient")
@RestController
public class IngredientController {
    private final IngredientRepo ingredientRepo;
    private final IngredientEntityAssembler ingredientEntityAssembler;
    private static final Logger asyncCommandsLogger = LoggerFactory.getLogger(AsyncCommands.class);
    public IngredientController(IngredientRepo ingredientRepo, IngredientEntityAssembler ingredientEntityAssembler) {
        this.ingredientRepo = ingredientRepo;
        this.ingredientEntityAssembler = ingredientEntityAssembler;
    }
    // @route   GET ingredients
    // @desc    get all ingredients
    @GetMapping("/getAllIngredient")
    public ResponseEntity<CollectionModel<EntityModel<Ingredient>>> getAllIngredient() {
        return ResponseEntity.ok(ingredientEntityAssembler.toCollectionModel(ingredientRepo.findAll()));
    }

    // @route   GET getIngredientById/{id}
    // @desc    get ingredient by id
    @GetMapping("/getIngredientById/{id}")
    public ResponseEntity<EntityModel<Ingredient>> getIngredientById(@PathVariable String id){
        return ingredientRepo.findById(id)
                .map(ingredientEntityAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @route   Delete deleteIngredient/{ingredientId}
    // @desc    delete ingredient by id
    @DeleteMapping("/deleteIngredient/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(@PathVariable String ingredientId) throws ApiErrorException {
        Optional<Ingredient> foundCat = ingredientRepo.findById(ingredientId);
        if(foundCat.isEmpty()) {
            throw new ApiErrorException("Wrong Ingredient id.");
        }
        ingredientRepo.deleteById(ingredientId);
        return ResponseEntity.ok("Ingredient deleted");
    }

    // @route   Delete deleteAllIngredient
    // @desc    delete all ingredient
    @DeleteMapping("/deleteAllIngredient")
    public ResponseEntity<String> deleteAllIngredient() throws ApiErrorException {
        ingredientRepo.deleteAll();
        return ResponseEntity.ok("Ingredients deleted");
    }
}
