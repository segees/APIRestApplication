package com.example.finalproject.controllers;

import com.example.finalproject.classes.ProfileCred;
import com.example.finalproject.classes.ProfileLoggedCreds;
import com.example.finalproject.dtos.ProfileDTO;
import com.example.finalproject.dtos.ProfileDtoAssembler;
import com.example.finalproject.exceptions.ApiErrorException;
import com.example.finalproject.Entities.ProfileEntityAssembler;
import com.example.finalproject.models.Profile;
import com.example.finalproject.repos.ProfileRepo;
import com.example.finalproject.utils.AsyncCommands;
import com.example.finalproject.utils.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@RequestMapping("/api/profile")
@RestController
public class ProfileController {
    private final ProfileRepo profileRepo;
    private final ProfileEntityAssembler profileEntityAssembler;
    private final ProfileDtoAssembler profileDtoAssembler;

    private static final Logger asyncCommandsLogger = LoggerFactory.getLogger(AsyncCommands.class);
    public ProfileController(ProfileRepo profileRepo, ProfileEntityAssembler profileEntityAssembler,  ProfileDtoAssembler profileDtoAssembler) {
        this.profileRepo = profileRepo;
        this.profileEntityAssembler = profileEntityAssembler;
        this.profileDtoAssembler = profileDtoAssembler;
    }

    // @route   GET getAllProfile
    // @desc    get all Profiles
    @GetMapping("/getAllProfile")
    public ResponseEntity<CollectionModel<EntityModel<Profile>>> getAllProfile() {
        return ResponseEntity.ok(profileEntityAssembler.toCollectionModel(profileRepo.findAll()));
    }

    // @route   GET getProfileById/{id}
    // @desc    get Profile by id
    @GetMapping("/getProfileById/{id}")
    public ResponseEntity<EntityModel<Profile>> getProfileById(@PathVariable String id){
        return profileRepo.findById(id)
                .map(profileEntityAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @route   GET getProfileByName/{name}
    // @desc    get Profile by name
    @GetMapping("/getProfileByName/{name}")
    public ResponseEntity<EntityModel<Profile>> getProfileByName(@PathVariable String name){
        return profileRepo.findByName(name)
                .map(profileEntityAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @route   POST addProfile
    // @desc    create a profile
    @PostMapping("/addProfile")
    public ResponseEntity<EntityModel<ProfileDTO>> addProfile(@Valid @RequestBody ProfileCred profileCred) throws ApiErrorException {
        Optional<Profile> profile = profileRepo.findByName(profileCred.getName());
        if(!profile.isEmpty()) {
            throw new ApiErrorException("name is taken");
        }
        ProfileLoggedCreds profileLoggedCreds = Security.hashPassword(profileCred.getPassword());
        Profile newProfile = new Profile(profileCred.getName(), profileLoggedCreds.getPassword(), profileLoggedCreds.getSalt());
        ProfileDTO profileDTO = new ProfileDTO(newProfile);
        profileRepo.save(newProfile);
        return ResponseEntity.ok(profileDtoAssembler.toModel(profileDTO));
    }

    // @route   POST login
    // @desc    login in (not posting something new)
    @PostMapping("/login")
    public ResponseEntity<EntityModel<ProfileDTO>> enterProfile(@Valid @RequestBody ProfileCred profileCred) throws ApiErrorException {
        Optional<Profile> profile = profileRepo.findByName(profileCred.getName());
        if(profile.isEmpty()) {
            throw new ApiErrorException("Wrong details");
        }
        if(!Security.checkPassword(profileCred.getPassword(),profile.get().getPassword(), profile.get().getSalt())) {
            throw new ApiErrorException("wrong details");
        }
        ProfileDTO profileDTO = new ProfileDTO(profile.get());
        return ResponseEntity.ok(profileDtoAssembler.toModel(profileDTO));
    }

    // @route   DELETE deleteProfile/{profileId}
    // @desc    delete profile by id
    @DeleteMapping("/deleteProfile/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable String ProfileId) throws ApiErrorException {
        Optional<Profile> profile = profileRepo.findById(ProfileId);
        if(profile.isEmpty()) {
            throw new ApiErrorException("Wrong Profile id.");
        }
        profileRepo.deleteById(ProfileId);
        return ResponseEntity.ok("Profile deleted");
    }

    // @route   PUT updateProfile/{ProfileId}
    // @desc    update profile
    @PutMapping("/updateProfile/{ProfileId}")
    ResponseEntity<EntityModel<Profile>> updateProfile(@NotNull @PathVariable String ProfileId, @RequestBody Profile newProfile) throws ApiErrorException {
        Optional<Profile> profile = profileRepo.findById(ProfileId);
        if(profile.isEmpty()) {
            throw new ApiErrorException("Wrong Profile id.");
        }
        BeanUtils.copyProperties(newProfile, profile.get());
        profileRepo.save(profile.get());
        return ResponseEntity.ok(profileEntityAssembler.toModel(profile.get()));
    }

    // @route   DELETE deleteAllProfile
    // @desc    delete all profiles
    @DeleteMapping("/deleteAllProfile")
    public ResponseEntity<String> deleteAllProfile() throws ApiErrorException {
        profileRepo.deleteAll();
        return ResponseEntity.ok("Profiles deleted");
    }
}
