package com.quintus.labs.datingapp.Login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleAuthenticationRequest {
    private String idToken;

}