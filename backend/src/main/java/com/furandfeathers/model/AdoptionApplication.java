package com.furandfeathers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_applications")
@Getter
@Setter
@NoArgsConstructor
public class AdoptionApplication extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @NotBlank
    @Size(max = 1000)
    private String reasonForAdoption;

    @Size(max = 1000)
    private String experienceWithPets;

    @Size(max = 1000)
    private String homeDescription;

    @Size(max = 1000)
    private String additionalNotes;

    private LocalDateTime applicationDate = LocalDateTime.now();

    private LocalDateTime reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @Size(max = 1000)
    private String reviewNotes;

    // Enums
    public enum ApplicationStatus {
        PENDING,        // Application submitted, waiting for review
        UNDER_REVIEW,   // Application is being reviewed
        APPROVED,       // Application approved, next steps in progress
        REJECTED,       // Application rejected
        WITHDRAWN,      // Applicant withdrew application
        COMPLETED       // Adoption process completed successfully
    }
}
