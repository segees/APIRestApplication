package com.example.finalproject.Entities;

import com.example.finalproject.controllers.CocktailController;
import com.example.finalproject.models.Cocktail;
import com.example.finalproject.utils.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CocktailEntityAssembler extends SimpleIdentifiableRepresentationModelAssembler<Cocktail> {
    public CocktailEntityAssembler(){super(CocktailController.class);}
    @Override
    public void addLinks(EntityModel<Cocktail> resource) {
        resource.add(
                linkTo(methodOn(CocktailController.class).getCocktailById(resource.getContent().getId())).withSelfRel());
        resource.add(
                linkTo(methodOn(CocktailController.class).getAllCocktails()).withRel("All Cocktail"));
    }
}
