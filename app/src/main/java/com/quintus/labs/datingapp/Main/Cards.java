package com.quintus.labs.datingapp.Main;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DatingApp
 */
@Data
@NoArgsConstructor
public class Cards {
    private String userId;
    private String name;
    private String bio;
    private String profileImageUr;
    private String moreImageUr;
    private String interest;
    private int age;
    private double distance;

    public Cards(String userId, String name, int age, String profileImageUrl, String bio, String interest, int distance, String moreImageUr) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.profileImageUr = profileImageUrl;
        this.bio = bio;
        this.interest = interest;
        this.distance = distance;
        this.moreImageUr = moreImageUr;
    }

    public Cards(String profileImageUrl) {
        this.profileImageUr = profileImageUrl;
    }

    public double getDistance() {
        return distance;
    }

    public String getBio() {
        return bio;
    }

    public String getInterest() {
        return interest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfileImageUr() {
        return profileImageUr;
    }

    public void setProfileImageUr(String profileImageUr) {
        this.profileImageUr = profileImageUr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoreImageUr() {
        return moreImageUr;
    }

    public void setMoreImageUr(String moreImageUr) {
        this.moreImageUr = moreImageUr;
    }
}
