package com.furandfeathers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String description;

    @NotNull
    private LocalDate recordDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MedicalRecordType recordType;

    @Size(max = 1000)
    private String treatment;

    @Size(max = 1000)
    private String medication;

    @Size(max = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id")
    private User veterinarian;

    private LocalDate nextVisitDate;

    @Size(max = 500)
    private String attachmentUrl;
}

enum MedicalRecordType {
    VACCINATION,
    SURGERY,
    ILLNESS,
    INJURY,
    CHECKUP,
    DENTAL,
    PARASITE_CONTROL,
    ALLERGY,
    CHRONIC_CONDITION,
    BEHAVIORAL,
    OTHER
}
