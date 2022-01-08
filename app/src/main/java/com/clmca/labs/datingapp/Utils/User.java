package com.clmca.labs.datingapp.Utils;

import java.io.Serializable;



public class User implements Serializable {
    private String user_id;
    private String phone_number;
    private String email;
    private String username;
    //hobby
    private boolean sports;
    private boolean travel;
    private boolean music;
    private boolean fishing;
    private boolean motorcycles;
    private boolean libra;
    private boolean coffee;
    private boolean karaoke;
    private boolean streetfood;
    private boolean sushi;
    //
    private String description;
    private String sex;
    private String preferSex;
    private String dateOfBirth;
    private String profileImageUrl;
    public User() {
    }

    public User(String sex, String preferSex, String user_id, String phone_number, String email, String username, boolean sport, boolean travel, boolean music, boolean fish, boolean motorcycles, boolean libra,
                 boolean coffee, boolean karaoke, boolean streetfood, boolean sushi,String description, String dateOfBirth, String profileImageUrl) {
        this.sex = sex;
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        //hobby
        this.sports = sport;
        this.travel = travel;
        this.music = music;
        this.fishing = fish;
        this.motorcycles = motorcycles;
        this.libra = libra;
        this.coffee = coffee;
        this.karaoke = karaoke;
        this.streetfood = streetfood;
        this.sushi = sushi;
        //
        this.description = description;
        this.preferSex = preferSex;
        this.dateOfBirth = dateOfBirth;
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSports() {
        return sports;
    }

    public void setSports(boolean sports) {
        this.sports = sports;
    }

    public boolean isTravel() {
        return travel;
    }

    public void setTravel(boolean travel) {
        this.travel = travel;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isFishing() {
        return fishing;
    }

    public void setFishing(boolean fishing) {
        this.fishing = fishing;
    }
    public boolean isMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(boolean motorcycles) {
        this.motorcycles = motorcycles;
    }

    public boolean isLibra() {
        return libra;
    }

    public void setLibra(boolean libra) {
        this.libra = libra;
    }

    public boolean isCoffee() {
        return coffee;
    }

    public void setCoffee(boolean coffee) {
        this.coffee = coffee;
    }

    public boolean isKaraoke() {
        return karaoke;
    }

    public void setKaraoke(boolean karaoke) {
        this.karaoke = karaoke;
    }

    public boolean isStreetfood() {
        return streetfood;
    }

    public void setStreetfood(boolean streetfood) {
        this.streetfood = streetfood;
    }

    public boolean isSushi() {
        return sushi;
    }

    public void setSushi(boolean sushi) {
        this.sushi = sushi;
    }

    public String getPreferSex() {
        return preferSex;
    }

    public void setPreferSex(String preferSex) {
        this.preferSex = preferSex;
    }

    // Added new attribute called date of birth.
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", sports=" + sports +
                ", travel=" + travel +
                ", music=" + music +
                ", fishing=" + fishing +
                ", description='" + description + '\'' +
                ", sex='" + sex + '\'' +
                ", preferSex='" + preferSex + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
