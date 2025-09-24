package com.furandfeathers.repository;

import com.furandfeathers.model.Pet;
import com.furandfeathers.model.PetStatus;
import com.furandfeathers.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends BaseRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {
    
    List<Pet> findByType(PetType type);
    
    List<Pet> findByStatus(PetStatus status);
    
    List<Pet> findByTypeAndStatus(PetType type, PetStatus status);
    
    @Query("SELECT p FROM Pet p WHERE p.shelter.id = :shelterId")
    Page<Pet> findByShelterId(@Param("shelterId") Long shelterId, Pageable pageable);
    
    @Query("SELECT p FROM Pet p WHERE p.user.id = :userId")
    List<Pet> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Pet p WHERE p.type = :type AND p.status = 'AVAILABLE'")
    List<Pet> findAvailableByType(@Param("type") PetType type);
    
    @Query("SELECT DISTINCT p.breed FROM Pet p WHERE p.type = :type")
    List<String> findDistinctBreedsByType(@Param("type") PetType type);
    
    @Query("SELECT p FROM Pet p WHERE " +
           "(:type IS NULL OR p.type = :type) AND " +
           "(:breed IS NULL OR p.breed = :breed) AND " +
           "(:minAge IS NULL OR p.age >= :minAge) AND " +
           "(:maxAge IS NULL OR p.age <= :maxAge) AND " +
           "(:gender IS NULL OR p.gender = :gender) AND " +
           "p.status = 'AVAILABLE'")
    Page<Pet> searchPets(
        @Param("type") PetType type,
        @Param("breed") String breed,
        @Param("minAge") Integer minAge,
        @Param("maxAge") Integer maxAge,
        @Param("gender") String gender,
        Pageable pageable
    );
}
