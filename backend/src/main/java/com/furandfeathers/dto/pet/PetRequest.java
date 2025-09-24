package com.furandfeathers.dto.pet;

import com.furandfeathers.model.enums.Gender;
import com.furandfeathers.model.enums.PetStatus;
import com.furandfeathers.model.enums.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PetRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotNull(message = "Pet type is required")
    private PetType type;

    @NotBlank(message = "Breed is required")
    @Size(max = 100, message = "Breed must be less than 100 characters")
    private String breed;

    @PositiveOrZero(message = "Age must be a positive number or zero")
    private Integer age;
    
    private LocalDate dateOfBirth;
    
    @NotNull(message = "Gender is required")
    private Gender gender;
    
    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;
    
    private String medicalHistory;
    private boolean isVaccinated;
    private boolean isNeutered;
    private boolean isHouseTrained;
    private boolean isGoodWithKids;
    private boolean isGoodWithOtherPets;
    private Set<@Size(max = 2048) String> imageUrls;
}
