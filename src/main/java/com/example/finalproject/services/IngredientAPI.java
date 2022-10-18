package com.example.finalproject.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientAPI {
    private List<IngredientAPIClass> drinks;
    @Override
    public String toString() {
        return "IngredientsAPI{" +
                drinks;
    }
}


