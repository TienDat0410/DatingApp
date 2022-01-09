package com.clmca.labs.datingapp.Profile;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {



    private String username;
    private String fullName;
    private String avatar;

    private List<String> pictures;
    private String about;
    private String jobDescription;
    private String company;
    private String school;
    private Gender gender;
    private List<Passion> passions;
    private Double longitude;
    private Double latitude;


    private int distance;

    private List<Gender> genderToShow;
    private int yearOfBirth;
    private int minAge;
    private int maxAge;

}
