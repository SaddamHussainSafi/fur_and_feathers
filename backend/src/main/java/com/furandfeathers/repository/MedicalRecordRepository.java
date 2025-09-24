package com.furandfeathers.repository;

import com.furandfeathers.model.MedicalRecord;
import com.furandfeathers.model.MedicalRecordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends BaseRepository<MedicalRecord, Long>, JpaSpecificationExecutor<MedicalRecord> {
    
    List<MedicalRecord> findByPetIdOrderByRecordDateDesc(Long petId);
    
    List<MedicalRecord> findByPetIdAndRecordTypeOrderByRecordDateDesc(Long petId, MedicalRecordType recordType);
    
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.pet.id = :petId AND mr.recordDate BETWEEN :startDate AND :endDate ORDER BY mr.recordDate DESC")
    List<MedicalRecord> findByPetIdAndRecordDateBetween(
        @Param("petId") Long petId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
    
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.veterinarian.id = :vetId ORDER BY mr.recordDate DESC")
    Page<MedicalRecord> findByVeterinarianId(@Param("vetId") Long vetId, Pageable pageable);
    
    @Query("SELECT DISTINCT mr.recordType FROM MedicalRecord mr WHERE mr.pet.id = :petId")
    List<MedicalRecordType> findDistinctRecordTypesByPetId(@Param("petId") Long petId);
    
    @Query("SELECT mr FROM MedicalRecord mr WHERE " +
           "mr.pet.id = :petId AND " +
           "(:recordType IS NULL OR mr.recordType = :recordType) AND " +
           "(:startDate IS NULL OR mr.recordDate >= :startDate) AND " +
           "(:endDate IS NULL OR mr.recordDate <= :endDate)")
    Page<MedicalRecord> searchMedicalRecords(
        @Param("petId") Long petId,
        @Param("recordType") MedicalRecordType recordType,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        Pageable pageable
    );
}
