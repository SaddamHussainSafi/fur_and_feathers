package com.furandfeathers.api;

import com.furandfeathers.api.dto.CreatePetRequest;
import com.furandfeathers.api.dto.PetDTO;
import com.furandfeathers.api.dto.UpdatePetRequest;
import com.furandfeathers.pet.Species;
import com.furandfeathers.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> list(@RequestParam(name = "species", required = false) Species species) {
        return ResponseEntity.ok(petService.listAvailablePets(Optional.ofNullable(species)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SHELTER')")
    public ResponseEntity<PetDTO> create(@Valid @RequestBody CreatePetRequest req) {
        return ResponseEntity.ok(petService.create(req));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SHELTER')")
    public ResponseEntity<PetDTO> update(@PathVariable Long id, @Valid @RequestBody UpdatePetRequest req) {
        return ResponseEntity.ok(petService.update(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SHELTER')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
