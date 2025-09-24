package com.furandfeathers.api.dto;

import com.furandfeathers.pet.*;

public class UpdatePetRequest {
    private String name;
    private Species species;
    private String breed;
    private Integer ageYears;
    private Gender gender;
    private Size size;
    private String location;
    private String description;
    private String imageUrl;
    private PetStatus status;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public Integer getAgeYears() { return ageYears; }
    public void setAgeYears(Integer ageYears) { this.ageYears = ageYears; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public PetStatus getStatus() { return status; }
    public void setStatus(PetStatus status) { this.status = status; }
}
