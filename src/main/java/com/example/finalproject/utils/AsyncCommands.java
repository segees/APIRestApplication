package com.example.finalproject.utils;

import com.example.finalproject.cocktailsNames.CocktailsNames;
import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Ingredient;
import com.example.finalproject.repos.CocktailRepo;
import com.example.finalproject.repos.IngredientRepo;
import com.example.finalproject.services.*;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Component
public class AsyncCommands implements CommandLineRunner {
    private final UserService userService;
    private final CocktailRepo cocktailRepo;
    private final IngredientRepo ingredientRepo;
    private static final Logger asyncCommandsLogger = LoggerFactory.getLogger(AsyncCommands.class);

    public AsyncCommands(UserService userService, CocktailRepo cocktailRepo,IngredientRepo ingredientRepo) {
        this.userService = userService;
        this.cocktailRepo = cocktailRepo;
        this.ingredientRepo=ingredientRepo;
    }
    @Override
    public void run(String... args) throws Exception {
//        CompletableFuture<IngredientAPI> ingredientAPICompletableFuture = userService.getIngredientAPI();
//        for (IngredientAPIClass ingredientAPIClass:ingredientAPICompletableFuture.get().getDrinks()) {
//            CompletableFuture<IngredientKindAPI>ingredientKindAPICompletableFuture=userService.getIngredientKindAPI(ingredientAPIClass.getStrIngredient1());
//            ingredientRepo.save(new Ingredient(ingredientAPIClass.getStrIngredient1(),ingredientKindAPICompletableFuture.get().getIngredients().get(0).getStrType()));
//        }
//        CompletableFuture<CocktailAPI> cocktailAPICompletableFuture ;
//        List<Ingredient> allIngredientList=ingredientRepo.findAll();
//        List<Ingredient> ingredientList=new ArrayList<>();
//        for (CocktailsNames name : CocktailsNames.values()) {
//            cocktailAPICompletableFuture=userService.getCocktailAPI(name.toString());
//            for (CocktailAPIClass cocktailAPIClass:cocktailAPICompletableFuture.get().getDrinks()) {
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient1()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient2()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient3()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient4()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient5()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient6()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient7()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient8()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient9()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient10()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient11()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient12()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient13()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient14()).equals(any.getName())).findAny().orElse(null));
//                ingredientList.add(allIngredientList.stream()
//                        .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient15()).equals(any.getName())).findAny().orElse(null));
//                asyncCommandsLogger.info(String.valueOf(ingredientList));
//                cocktailRepo.save(new Cocktail(cocktailAPIClass.getStrDrink(),ingredientList));
//                for ( Ingredient intt:ingredientList) {
//                    ingredientRepo.findById(intt.getId());
//                }
//                ingredientList.clear();
//            }
//        }
    }
}
