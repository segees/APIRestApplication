package com.example.finalproject.models;

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
@Document("profile")
public class Profile {

    @Id
    @Generated
    private String id;
    private String name;
    private byte[] password;
    private byte[] salt;
    /*
    create relationship between a Profile and a Cocktail
    i.e., a Profile's object primary key will be stored as a foreign key in Cocktail table
   */
    @DBRef
    private List<Cocktail> cocktailArrayList = new ArrayList<>();
    public Profile(String fullName, byte[] password, byte[] salt){

        this.name = fullName;
        this.password = password;
        this.salt = salt;
    }


}

