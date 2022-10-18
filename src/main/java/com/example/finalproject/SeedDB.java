package com.example.finalproject;

import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Ingredient;
import com.example.finalproject.repos.CocktailRepo;
import com.example.finalproject.repos.IngredientRepo;
import com.example.finalproject.repos.ProfileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
// class declares one or one @Bean method
// Spring container to generates bean definitions and handles requests beans (at runtime)
class SeedDB {
//    private static final Logger logger = LoggerFactory.getLogger(SeedDB.class);
//    @Bean
//     CommandLineRunner beans once the application context is loaded.
//    CommandLineRunner initDatabase(CocktailRepo cocktailRepo, IngredientRepo ingredientRepo,ProfileRepo profileRepo) {
//         runner gets a copy of the new DB and creates the following
//         products and saves them
//        return args -> {
//            List<Ingredient> ingredient = new ArrayList<>();
//            ingredient.add(ingredientRepo.save(new Ingredient("asd","ertert")));
//            Cocktail cocktail = cocktailRepo.save(new Cocktail("asd"));
//            cocktail.setIngredientList(ingredient);
//        };
//    }
}