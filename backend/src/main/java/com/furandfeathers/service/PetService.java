package com.furandfeathers.service;

import com.furandfeathers.api.dto.CreatePetRequest;
import com.furandfeathers.api.dto.PetDTO;
import com.furandfeathers.api.dto.UpdatePetRequest;
import com.furandfeathers.pet.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper mapper;

    public List<PetDTO> listAvailablePets(Optional<Species> species) {
        List<Pet> pets = species
                .map(s -> petRepository.findBySpeciesAndStatus(s, PetStatus.AVAILABLE))
                .orElseGet(() -> petRepository.findByStatus(PetStatus.AVAILABLE));
        return pets.stream().map(p -> mapper.map(p, PetDTO.class)).toList();
    }

    public PetDTO getById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        return mapper.map(pet, PetDTO.class);
    }

    public PetDTO create(CreatePetRequest req) {
        Pet pet = Pet.builder()
                .name(req.getName())
                .species(req.getSpecies())
                .breed(req.getBreed())
                .ageYears(req.getAgeYears())
                .gender(req.getGender())
                .size(req.getSize())
                .location(req.getLocation())
                .description(req.getDescription())
                .imageUrl(req.getImageUrl())
                .status(PetStatus.AVAILABLE)
                .build();
        return mapper.map(petRepository.save(pet), PetDTO.class);
    }

    public PetDTO update(Long id, UpdatePetRequest req) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        if (req.getName() != null) pet.setName(req.getName());
        if (req.getSpecies() != null) pet.setSpecies(req.getSpecies());
        if (req.getBreed() != null) pet.setBreed(req.getBreed());
        if (req.getAgeYears() != null) pet.setAgeYears(req.getAgeYears());
        if (req.getGender() != null) pet.setGender(req.getGender());
        if (req.getSize() != null) pet.setSize(req.getSize());
        if (req.getLocation() != null) pet.setLocation(req.getLocation());
        if (req.getDescription() != null) pet.setDescription(req.getDescription());
        if (req.getImageUrl() != null) pet.setImageUrl(req.getImageUrl());
        if (req.getStatus() != null) pet.setStatus(req.getStatus());
        return mapper.map(petRepository.save(pet), PetDTO.class);
    }

    public void delete(Long id) {
        if (!petRepository.existsById(id)) {
            throw new EntityNotFoundException("Pet not found");
        }
        petRepository.deleteById(id);
    }
}
