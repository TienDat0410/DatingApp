package com.quintus.labs.datingapp.Login;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
    private final String username;
    private String fullName;
    private String avatar;
    private String locale;

}