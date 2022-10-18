package com.example.finalproject.dtos;

import com.example.finalproject.models.Cocktail;
import com.example.finalproject.models.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"id", "name"})
public class ProfileDTO {
    @JsonIgnore
    private final Profile profile;

    public String getId() {
        return this.profile.getId();
    }

    public String getName() {
        return this.profile.getName();
    }

}
