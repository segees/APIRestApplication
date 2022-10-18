package com.example.finalproject.dtos;

import com.example.finalproject.controllers.CocktailController;
import com.example.finalproject.controllers.ProfileController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileDtoAssembler implements SimpleRepresentationModelAssembler<ProfileDTO> {
    @Override
    public void addLinks(EntityModel<ProfileDTO> resource) {
        resource.add(linkTo(methodOn(ProfileController.class)
                .getProfileById(resource.getContent().getId())).withSelfRel());
        resource.add(linkTo(methodOn(ProfileController.class)
                .getAllProfile())
                .withRel("ProfileDTO information"));
    }
    @Override
    public void addLinks(CollectionModel<EntityModel<ProfileDTO>> resources) {
        resources.add(linkTo(methodOn(ProfileController.class).getAllProfile()).withSelfRel());
    }
}

