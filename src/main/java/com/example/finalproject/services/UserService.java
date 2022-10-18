package com.example.finalproject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
/**
 * We use @Service to do 2 things:
 * 1. theoretical decoupling: between our Views (DAO/DTO/HTML documents returned in the
 * response's body), Controllers that serve users at different URLs with specific parameters
 * and the business logic of our application (Model)
 * Model usually represents business logic operations and DB access
 * 2. In practical sense: this annotation makes Spring scan this component and use
 * it as a candidate for auto-wiring
 *
 * This class will use a remote REST endpoint to get data from Github's api
 * We will use a type called RestTemplate to go to a specific URL and create a GitHubUser
 * object from the RestTemplate. This is done using a RestTemplateBuilder
 */
public class UserService {
    // RestTemplate is used to invoke an external REST point by another service
    private RestTemplate restTemplate;
    private static final Logger serviceLogger = LoggerFactory.getLogger(UserService.class);
    public UserService(RestTemplateBuilder templateBuilder){
        this.restTemplate = templateBuilder.build();
    }

    /**
     * This method should run asynchronously in order to get
     * information about a specific user - using @Async annotation and CompletableFuture
     * Our program needs to send an HTTP GET request to a remote REST endpoint
     * we ought to get a JSON representing the user.
     * @Name
     * @return
     */
    @Async
    public CompletableFuture<CocktailAPI> getCocktailAPI(String Name){
        String template = String.format("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=%s",Name);
        CocktailAPI aCocktail = this.restTemplate.getForObject(template,CocktailAPI.class);
        serviceLogger.info("Running in thread = " + Thread.currentThread().getName());
        //serviceLogger.info(aCocktail.getDrinks().toString());
        return CompletableFuture.completedFuture(aCocktail);
    }
    @Async
    public CompletableFuture<IngredientAPI> getIngredientAPI(){
        String template = String.format("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
        IngredientAPI ingredientAPI = this.restTemplate.getForObject(template,IngredientAPI.class);
        serviceLogger.info("Running in thread = " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(ingredientAPI);
    }
    @Async
    public CompletableFuture<IngredientKindAPI> getIngredientKindAPI(String name){
        String template = String.format("https://thecocktaildb.com/api/json/v1/1/search.php?i=%s",name);
        IngredientKindAPI ingredientKindAPI = this.restTemplate.getForObject(template,IngredientKindAPI.class);
        serviceLogger.info("Running in thread = " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(ingredientKindAPI);
    }
}