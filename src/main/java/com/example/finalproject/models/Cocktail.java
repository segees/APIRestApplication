package com.example.finalproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document("cocktail")
public class Cocktail {
    @Id
    @Generated
    private String id;
    private String Instructions;
    private String name;

    /*
    create relationship between a Cocktail and a Ingredient
    i.e., a Cocktail's object primary key will be stored as a foreign key in Ingredient table
   */
    @DBRef
    @JsonIgnore
    // ManyToMany
    private List<Ingredient> ingredientList = new ArrayList<>();

    public Cocktail(String Name,List<Ingredient> ingredientList){
        this.name = Name;
        this.ingredientList=ingredientList;
    }

}



