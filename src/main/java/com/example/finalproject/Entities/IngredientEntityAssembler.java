package com.example.finalproject.Entities;


import com.example.finalproject.controllers.IngredientController;
import com.example.finalproject.models.Ingredient;
import com.example.finalproject.utils.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class IngredientEntityAssembler extends SimpleIdentifiableRepresentationModelAssembler<Ingredient> {
    public IngredientEntityAssembler(){super(IngredientController.class);}
    @Override
    public void addLinks(EntityModel<Ingredient> resource) {
        resource.add(
                linkTo(methodOn(IngredientController.class).getIngredientById(resource.getContent().getId())).withSelfRel());
        resource.add(
                linkTo(methodOn(IngredientController.class).getAllIngredient()).withRel("All Ingredients"));
    }
}
