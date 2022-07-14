package com.n26.tests;

import com.n26.entities.*;
import com.n26.log.Log;
import com.n26.steps.PetServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetServiceTest {

    Pet pet = createPet();

    Pet updatePet = editPet();

    @Test(priority = 1)
    public void createPetTest() {
        Log.info("Pet creation test starts...");
        Response responseCreatedPet = PetServiceSteps.addPet(pet);
        Assert.assertEquals(responseCreatedPet.getStatusCode(), 200
                , "Status code is not as expected");
        Assert.assertEquals(responseCreatedPet.jsonPath().getString("name"), pet.getName()
                ,"Pet name is incorrect");
        Log.info(pet + " created successfully!");
    }

    @Test(priority = 2)
    public void getPetsByPetId() {
        Log.info("Getting pets by Pet ID test starts...");
        Response responsePetsById = PetServiceSteps.getPetById(pet.getId());
        Assert.assertEquals(responsePetsById.getStatusCode(), 200
                ,"Status code is not as expected");
        Assert.assertEquals(responsePetsById.as(Pet.class).getId(), pet.getId(),
                "Pet ID is incorrect");
        Log.info(pet + " pets retrieved!");
    }

    @Test(priority = 3)
    public void getPetsByStatusTest() {
        Log.info("Getting pets by status test starts...");
        Response responsePetsByStatus = PetServiceSteps.getPetsByStatus("available");
        Assert.assertEquals(responsePetsByStatus.getStatusCode(), 200
                ,"Status code is not as expected");
        Assert.assertTrue(responsePetsByStatus.jsonPath().getList("name").size() > 0
                ,"Less than 5 pets by requested status found");
        Log.info(responsePetsByStatus.jsonPath().getList("name").size() + " pets retrieved!");
    }

    @Test(priority = 3)
    public void getPetsByTagTest() {
        Log.info("Getting pets by status test starts...");
        Response responsePetsByTags = PetServiceSteps.getPetsByTags("domestic");
        Assert.assertEquals(responsePetsByTags.getStatusCode(), 200
                ,"Status code is not as expected");
        Assert.assertTrue(responsePetsByTags.jsonPath().getList("name").size() > 0
                ,"No pets by requested tag found");
        Log.info(responsePetsByTags.jsonPath().getList("item").size() + " pets retrieved!");
    }

    @Test(priority = 4)
    public void deletePetByIdTest() {
        Log.info("Delete pet by ID test starts...");
        Response responseCreatedPet = PetServiceSteps.addPet(pet);
        PetServiceSteps.deletePetById(pet.getId());
        Response responseDeletedPet = PetServiceSteps.getPetById(pet.getId());
        Assert.assertEquals(responseDeletedPet.getStatusCode(), 404
                ,"Status code is not as expected");
        Log.info(pet + " deleted successfully!");
    }

    @Test(priority = 3)
    public void updatePetTest() {
        Log.info("Pet update test starts...");
        Response responseUpdatedPet = PetServiceSteps.updatePet(updatePet);
        Assert.assertEquals(responseUpdatedPet.getStatusCode(), 200
                , "Status code is not as expected");
        Assert.assertEquals(responseUpdatedPet.jsonPath().getString("name"), updatePet.getName()
                ,"Updated Pet name is incorrect");
        Log.info(updatePet + " updated successfully!");
    }

    private Pet createPet() {
        return new Pet()
                .setId(1)
                .setCategory(new Category()
                        .setId(1)
                        .setName("cat"))
                .setName("Mara")
                .setPhotoUrls(new String[]{"https://en.wikipedia.org/wiki/Cat#/media/File:Cat_poster_1.jpg"})
                .setTags(new Tag[]{new Tag()
                        .setId(1)
                        .setName("domestic")})
                .setStatus(PetStatus.available);
    }

    private Pet editPet() {
        return new Pet()
                .setId(1)
                .setCategory(new Category()
                        .setId(1)
                        .setName("cat"))
                .setName("Tara")
                .setPhotoUrls(new String[]{"https://upload.wikimedia.org/wikipedia/commons/b/bb/Kittyply_edit1.jpg"})
                .setTags(new Tag[]{new Tag()
                        .setId(1)
                        .setName("domestic")})
                .setStatus(PetStatus.available);
    }
}