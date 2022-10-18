package com.example.finalproject.Entities;


import com.example.finalproject.controllers.ProfileController;
import com.example.finalproject.models.Profile;
import com.example.finalproject.utils.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class extends SimpleIdentifiableRepresentationModelAssembler in order to assemble an EntityModel of
 * a profile from a Profile object
 * SimpleIdentifiableRepresentationModelAssembler requires at least 2 things:
 * 1. Specify the concrete type to assemble as EntityModel
 * 2. at least 1 constructor matching super. We will use the constructor that specifies in which controller
 * EntityModel<Profile> objects are used
 * EntityModel<Profile> represents a Profile object including the posts it holds
 */
@Component
public class ProfileEntityAssembler extends SimpleIdentifiableRepresentationModelAssembler<Profile> {
    public ProfileEntityAssembler(){super(ProfileController.class);}
    @Override
    public void addLinks(EntityModel<Profile> resource) {
        resource.add(
                linkTo(methodOn(ProfileController.class).getProfileById(resource.getContent().getId())).withSelfRel());
    }
}
