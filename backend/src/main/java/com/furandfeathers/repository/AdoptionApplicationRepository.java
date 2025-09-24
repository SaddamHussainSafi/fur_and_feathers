package com.furandfeathers.repository;

import com.furandfeathers.model.AdoptionApplication;
import com.furandfeathers.model.AdoptionApplication.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdoptionApplicationRepository extends BaseRepository<AdoptionApplication, Long>, 
                                                     JpaSpecificationExecutor<AdoptionApplication> {
    
    List<AdoptionApplication> findByUserId(Long userId);
    
    List<AdoptionApplication> findByPetId(Long petId);
    
    List<AdoptionApplication> findByShelterId(Long shelterId);
    
    List<AdoptionApplication> findByStatus(ApplicationStatus status);
    
    @Query("SELECT a FROM AdoptionApplication a WHERE a.user.id = :userId AND a.status = :status")
    List<AdoptionApplication> findByUserIdAndStatus(
        @Param("userId") Long userId, 
        @Param("status") ApplicationStatus status
    );
    
    @Query("SELECT a FROM AdoptionApplication a WHERE a.pet.id = :petId AND a.status = :status")
    List<AdoptionApplication> findByPetIdAndStatus(
        @Param("petId") Long petId, 
        @Param("status") ApplicationStatus status
    );
    
    @Query("SELECT a FROM AdoptionApplication a WHERE " +
           "a.shelter.id = :shelterId AND " +
           "(:status IS NULL OR a.status = :status) AND " +
           "(:startDate IS NULL OR a.applicationDate >= :startDate) AND " +
           "(:endDate IS NULL OR a.applicationDate <= :endDate)")
    Page<AdoptionApplication> findApplicationsByShelter(
        @Param("shelterId") Long shelterId,
        @Param("status") ApplicationStatus status,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        Pageable pageable
    );
    
    @Query("SELECT COUNT(a) > 0 FROM AdoptionApplication a WHERE " +
           "a.user.id = :userId AND " +
           "a.pet.id = :petId AND " +
           "a.status IN (com.furandfeathers.model.AdoptionApplication$ApplicationStatus.PENDING, " +
           "com.furandfeathers.model.AdoptionApplication$ApplicationStatus.UNDER_REVIEW, " +
           "com.furandfeathers.model.AdoptionApplication$ApplicationStatus.APPROVED)")
    boolean existsActiveApplicationForUserAndPet(
        @Param("userId") Long userId, 
        @Param("petId") Long petId
    );
    
    @Query("SELECT COUNT(a) FROM AdoptionApplication a WHERE a.pet.id = :petId AND a.status = 'APPROVED'")
    int countApprovedApplicationsForPet(@Param("petId") Long petId);
}
