package com.furandfeathers.pet;

import com.furandfeathers.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status);
    List<Pet> findBySpeciesAndStatus(Species species, PetStatus status);
}
