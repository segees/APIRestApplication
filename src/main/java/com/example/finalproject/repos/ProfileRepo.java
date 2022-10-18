package com.example.finalproject.repos;


import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends MongoRepository<Profile,String> {
    @Query(value="{ 'id' : ?0 }")
    public List<Profile> findByCocktailId(String id);
    @Query(value="{ 'name' : ?0 }")
    public Optional<Profile> findByName(String name);
    @Query(value="{ 'cocktailArrayList' : ?0 }")
    public List<Cocktail> findCocktailByProfileName(String name);
}
