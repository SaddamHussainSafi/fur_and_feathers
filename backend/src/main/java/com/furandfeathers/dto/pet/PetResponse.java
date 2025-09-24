package com.furandfeathers.dto.pet;

import com.furandfeathers.model.enums.Gender;
import com.furandfeathers.model.enums.PetStatus;
import com.furandfeathers.model.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {
    private Long id;
    private String name;
    private PetType type;
    private String breed;
    private PetStatus status;
    private Integer age;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String description;
    private boolean isVaccinated;
    private boolean isNeutered;
    private boolean isHouseTrained;
    private boolean isGoodWithKids;
    private boolean isGoodWithOtherPets;
    private Set<String> imageUrls;
    private Long shelterId;
    private String shelterName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
