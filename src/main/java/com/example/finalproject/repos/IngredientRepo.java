package com.example.finalproject.repos;


import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepo extends MongoRepository<Ingredient,String> {
    @Query(value="{ 'id' : ?0 }")
    public Ingredient findByIngredientId(String id);
    @Query(value="{ 'id' : ?0 }")
    public List<Ingredient> findByProfileId(String id);
    @Query(value="{ 'cocktailList' : ?0 }")
    public List<Cocktail> findByCocktailName(String name);
}
