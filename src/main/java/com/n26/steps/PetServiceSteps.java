package com.n26.steps;

import com.n26.entities.Pet;
import com.n26.service.PetService;
import io.restassured.response.Response;

import static com.n26.service.uritemplate.PetServiceUri.*;

public class PetServiceSteps {

    private static final PetService PET_SERVICE = PetService.getInstance();

    public static Response getPetById(int petId) {
        return PET_SERVICE.getRequest(PET_BY_ID, petId);
    }

    public static Response getPetsByStatus(String petStatus) {
        return PET_SERVICE.getRequest(PET_BY_STATUS, petStatus);
    }

    public static Response getPetsByTags(String petTag) {
        return PET_SERVICE.getRequest(PET_BY_TAGS, petTag);
    }

    public static Response addPet(Pet pet) {
        return PET_SERVICE.postRequest(PET, pet);
    }

    public static  Response updatePet(Pet pet){
        return PET_SERVICE.putRequest(PET,pet);
    }

    public static void deletePetById(int id) {
        PET_SERVICE.deleteRequest(PET_BY_ID, id);
    }
}
