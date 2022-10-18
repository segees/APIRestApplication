package com.example.finalproject.controllers;


import com.example.finalproject.cocktailsNames.CocktailsNames;
import com.example.finalproject.exceptions.ApiErrorException;
import com.example.finalproject.Entities.CocktailEntityAssembler;
import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Ingredient;
import com.example.finalproject.models.Profile;
import com.example.finalproject.repos.CocktailRepo;
import com.example.finalproject.repos.IngredientRepo;
import com.example.finalproject.repos.ProfileRepo;
import com.example.finalproject.services.CocktailAPI;
import com.example.finalproject.services.CocktailAPIClass;
import com.example.finalproject.services.UserService;
import com.example.finalproject.utils.AsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cocktail")
public class CocktailController {

    private final CocktailRepo cocktailRepo;
    private final IngredientRepo ingredientRepo;
    private final ProfileRepo profileRepo;
    private final UserService userService;
    private final CocktailEntityAssembler cocktailEntityAssembler;
    private static final Logger asyncCommandsLogger = LoggerFactory.getLogger(AsyncCommands.class);
    public CocktailController(CocktailRepo cocktailRepo, IngredientRepo ingredientRepo, ProfileRepo profileRepo, UserService userService, CocktailEntityAssembler cocktailEntityAssembler) {
        this.cocktailRepo = cocktailRepo;
        this.ingredientRepo = ingredientRepo;
        this.profileRepo = profileRepo;
        this.userService = userService;
        this.cocktailEntityAssembler = cocktailEntityAssembler;

    }
    // @route   GET getAllCocktails
    // @desc    get all cocktails
    @GetMapping("/getAllCocktails")
    public ResponseEntity<CollectionModel<EntityModel<Cocktail>>> getAllCocktails() {
        return ResponseEntity.ok(cocktailEntityAssembler.toCollectionModel(cocktailRepo.findAll()));
    }

    // @route   GET getAllMainCocktail
    // @desc    get all cocktails optional names
    @GetMapping("/getAllMainCocktail")
    public ResponseEntity<List<String>> getAllMainCocktail() {
        List<String>str=new ArrayList<>();
        for (CocktailsNames name : CocktailsNames.values()) {
            str.add(name.toString());
        }
        return ResponseEntity.ok(str);
    }

    // @route   GET getCocktailById/{id}
    // @desc    get cocktail by id
    @GetMapping("/getCocktailById/{id}")
    public ResponseEntity<EntityModel<Cocktail>> getCocktailById(@PathVariable String id){
        return cocktailRepo.findById(id)
                .map(cocktailEntityAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @route   Post postCocktailByName/{name}
    // @desc    Post cocktail by name and give ref to profile an ingredients
    @PostMapping("/postCocktailByName/{name}")
    public ResponseEntity<CollectionModel<EntityModel<Cocktail>>> postCocktailByName(@PathVariable String name,@RequestParam String PersonName) throws ExecutionException, InterruptedException, ApiErrorException {
        Optional<Profile> profile = profileRepo.findByName(PersonName);
        if(profile.isEmpty()) {
            throw new ApiErrorException("Wrong Profile");
        }
        if(!cocktailRepo.findByCocktailName(name)
                .map(cocktailEntityAssembler::toModel)
                .map(ResponseEntity::ok).equals(Optional.empty())){
            return ResponseEntity.ok(cocktailEntityAssembler.toCollectionModel(cocktailRepo.findAllByCocktailName(name)));
        }
        CompletableFuture<CocktailAPI> cocktailAPICompletableFuture ;
        List<Ingredient> allIngredientList=ingredientRepo.findAll();
        List<Ingredient> ingredientList=new ArrayList<>();
        cocktailAPICompletableFuture=userService.getCocktailAPI(name);
        for (CocktailAPIClass cocktailAPIClass:cocktailAPICompletableFuture.get().getDrinks()) {
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient1()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient2()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient3()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient4()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient5()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient6()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient7()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient8()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient9()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient10()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient11()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient12()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient13()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient14()).equals(any.getName())).findAny().orElse(null));
            ingredientList.add(allIngredientList.stream()
                    .filter(any->String.valueOf(cocktailAPIClass.getStrIngredient15()).equals(any.getName())).findAny().orElse(null));
            Cocktail tempCocktail=cocktailRepo.save(new Cocktail(cocktailAPIClass.getStrDrink(),ingredientList));
            List<Cocktail> cocktailList= profile.get().getCocktailArrayList();
            cocktailList.add(tempCocktail);
            profile.get().setCocktailArrayList(cocktailList);
            profileRepo.save(profile.get());
            ingredientList.removeAll(Collections.singleton(null));
            for (Ingredient ingredient:ingredientList) {
                Ingredient tempIngredient = ingredientRepo.findByIngredientId(ingredient.getId());
                List<Cocktail> tempCocktailList = tempIngredient.getCocktailList();
                tempCocktailList.add(tempCocktail);
                tempIngredient.setCocktailList(tempIngredient.getCocktailList());
                ingredientRepo.save(tempIngredient);
            }
            ingredientList.clear();
        }
        return ResponseEntity.ok(cocktailEntityAssembler.toCollectionModel(cocktailRepo.findAllByCocktailName(name)));
    }

    // @route   POST addCocktail
    // @desc    create a cocktail
    @PostMapping("/addCocktail")
    public ResponseEntity<EntityModel<Cocktail>> addCocktail(  @RequestBody Cocktail newCocktail) throws ApiErrorException {
        cocktailRepo.save(newCocktail);
        return ResponseEntity.ok(cocktailEntityAssembler.toModel(newCocktail));
    }

    // @route   DELETE deleteCocktail/{cocktailId}
    // @desc    delete cocktail by id
    @DeleteMapping("/deleteCocktail/{cocktailId}")
    public ResponseEntity<String> deleteCocktail(@PathVariable String id) throws ApiErrorException {
        Optional<Cocktail> cocktail = cocktailRepo.findById(id);
        if(cocktail.isEmpty()) {
            throw new ApiErrorException("Wrong Cocktail id.");
        }
        cocktailRepo.deleteById(id);
        return ResponseEntity.ok("Cocktail deleted");
    }

    // @route   DELETE deleteAllCocktail
    // @desc    delete all cocktail
    @DeleteMapping("/deleteAllCocktail")
    public ResponseEntity<String> deleteAllCocktail() throws ApiErrorException {
        cocktailRepo.deleteAll();
        return ResponseEntity.ok("Cocktail deleted");
    }

}
