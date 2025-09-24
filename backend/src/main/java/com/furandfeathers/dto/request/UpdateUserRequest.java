package com.furandfeathers.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 15)
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Invalid phone number format")
    private String phoneNumber;

    @Size(min = 6, max = 40)
    private String password;

    @Size(max = 1000)
    private String bio;

    @Size(max = 200)
    private String profileImageUrl;
}
