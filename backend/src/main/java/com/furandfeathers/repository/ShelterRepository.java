package com.furandfeathers.repository;

import com.furandfeathers.model.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends BaseRepository<Shelter, Long>, JpaSpecificationExecutor<Shelter> {
    
    @Query("SELECT s FROM Shelter s WHERE LOWER(s.city) LIKE LOWER(concat('%', :city, '%'))")
    List<Shelter> findByCityContainingIgnoreCase(@Param("city") String city);
    
    @Query("SELECT s FROM Shelter s WHERE LOWER(s.state) = LOWER(:state)")
    List<Shelter> findByStateIgnoreCase(@Param("state") String state);
    
    @Query("SELECT s FROM Shelter s WHERE s.shelterOwner.id = :ownerId")
    List<Shelter> findByOwnerId(@Param("ownerId") Long ownerId);
    
    @Query("SELECT s FROM Shelter s WHERE " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(concat('%', :name, '%'))) AND " +
           "(:city IS NULL OR LOWER(s.city) LIKE LOWER(concat('%', :city, '%'))) AND " +
           "(:state IS NULL OR LOWER(s.state) = LOWER(:state))")
    Page<Shelter> searchShelters(
        @Param("name") String name,
        @Param("city") String city,
        @Param("state") String state,
        Pageable pageable
    );
    
    @Query("SELECT DISTINCT s.state FROM Shelter s ORDER BY s.state")
    List<String> findAllStates();
    
    @Query("SELECT DISTINCT s.city FROM Shelter s WHERE s.state = :state ORDER BY s.city")
    List<String> findCitiesByState(@Param("state") String state);
}
