package com.furandfeathers.model;

public enum PetStatus {
    AVAILABLE,          // Available for adoption
    PENDING_ADOPTION,   // Adoption in progress
    ADOPTED,            // Successfully adopted
    UNAVAILABLE,        // Not available for adoption (medical reasons, etc.)
    FOSTER,            // In foster care
    MEDICAL_HOLD,       // On hold due to medical reasons
    ADOPTION_PENDING   // Adoption is being processed
}
