package com.furandfeathers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
public class Pet extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PetType type;

    @Size(max = 50)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PetStatus status = PetStatus.AVAILABLE;

    @PositiveOrZero
    private Integer age; // in months

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Size(max = 1000)
    private String description;

    @Size(max = 1000)
    private String medicalHistory;

    private boolean isVaccinated = false;
    private boolean isNeutered = false;
    private boolean isHouseTrained = false;
    private boolean isGoodWithKids = true;
    private boolean isGoodWithOtherPets = true;

    @ElementCollection
    @CollectionTable(name = "pet_images", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdoptionApplication> adoptionApplications = new HashSet<>();

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    // Helper methods
    public void addImageUrl(String imageUrl) {
        this.imageUrls.add(imageUrl);
    }

    public void removeImageUrl(String imageUrl) {
        this.imageUrls.remove(imageUrl);
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecords.add(medicalRecord);
        medicalRecord.setPet(this);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecords.remove(medicalRecord);
        medicalRecord.setPet(null);
    }
}

enum Gender {
    MALE,
    FEMALE,
    UNKNOWN
}
