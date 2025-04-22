package com.uro_alert.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uro_alert.backend.model.enumeration.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
