package com.example.finalproject.repos;


import com.example.finalproject.models.Cocktail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocktailRepo extends MongoRepository<Cocktail,String> {
    @Query(value="{ 'id' : ?0 }")
    public Cocktail findByCocktailId(String id);
    @Query(value="{ 'name' : ?0 }")
    public Optional <Cocktail> findByCocktailName(String name);
    @Query(value="{ 'name' :{$regex:?0}}")
    public  List<Cocktail> findAllByCocktailName(String name);
    @Query(value="{ 'name' : ?0 }")
    public List<Cocktail> findByProfileName(String name);
    @Query(value="{ 'name' : ?0 }")
    public List<Cocktail> findByIngredientName(String name);
}
