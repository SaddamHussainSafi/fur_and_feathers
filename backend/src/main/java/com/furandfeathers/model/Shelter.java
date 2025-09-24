package com.furandfeathers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shelters")
@Getter
@Setter
@NoArgsConstructor
public class Shelter extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(max = 200)
    private String address;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 100)
    private String state;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
    private String zipCode;

    @NotBlank
    @Size(max = 15)
    @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$")
    private String phoneNumber;

    @Size(max = 15)
    @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$")
    private String secondaryPhoneNumber;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String website;

    @Size(max = 1000)
    private String operatingHours;

    @Size(max = 1000)
    private String adoptionProcess;

    @ElementCollection
    @CollectionTable(name = "shelter_images", joinColumns = @JoinColumn(name = "shelter_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User shelterOwner;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdoptionApplication> adoptionApplications = new HashSet<>();

    // Helper methods
    public void addPet(Pet pet) {
        this.pets.add(pet);
        pet.setShelter(this);
    }

    public void removePet(Pet pet) {
        this.pets.remove(pet);
        pet.setShelter(null);
    }

    public void addImageUrl(String imageUrl) {
        this.imageUrls.add(imageUrl);
    }

    public void removeImageUrl(String imageUrl) {
        this.imageUrls.remove(imageUrl);
    }
}
