package com.clmca.labs.datingapp.Model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authorities {
    private String idToken;
    //
    public Authorities(String idToken) {
        this.idToken = idToken;
    }

    //
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
