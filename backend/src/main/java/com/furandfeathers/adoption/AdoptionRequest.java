package com.furandfeathers.adoption;

import com.furandfeathers.pet.Pet;
import com.furandfeathers.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "adoption_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User requester;

    @ManyToOne(optional=false)
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    @Column(length = 1000)
    private String message;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
