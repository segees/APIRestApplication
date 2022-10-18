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
@Document("ingredient")
public class Ingredient {
    @Id
    @Generated
    private String id;
    private String kind;
    private String name;
    /*
    create relationship between a Ingredient and a Cocktail
    i.e., a Ingredient's object primary key will be stored as a foreign key in Cocktail table
   */
    @DBRef
    // ManyToMany
    private List<Cocktail> cocktailList = new ArrayList<>();
    public Ingredient(String Name,String Kind){
        this.name = Name;
        this.kind=Kind;
    }
}



