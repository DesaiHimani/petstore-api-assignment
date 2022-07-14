package com.n26.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pet {

    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private PetStatus status;

    @Override
    public String toString() {
        return category.getName() + " " + "\"" + name + "\"" + " with ID: " + id;
    }
}

