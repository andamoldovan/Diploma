package com.licenta.project.business.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private ArrayList<String> preferences;
    private int readArticles;
    private ArrayList<String> favoriteArticles;
    private String emailSchedule;

    public UserDTO() {
    }


    public UserDTO(String firstName, String lastName, String userName, String email, String password,
                   ArrayList<String> preferences, int readArticles, ArrayList<String> favoriteArticles,
                   String emailSchedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.readArticles = readArticles;
        this.favoriteArticles = favoriteArticles;
        this.emailSchedule = emailSchedule;
    }

    public UserDTO(String id, String firstName, String lastName, String userName, String email, String password,
                   ArrayList<String> preferences, int readArticles, ArrayList<String> favoriteArticles,
                   String emailSchedule) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.readArticles = readArticles;
        this.favoriteArticles = favoriteArticles;
        this.emailSchedule = emailSchedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }

    public int getReadArticles() {
        return readArticles;
    }

    public void setReadArticles(int readArticles) {
        this.readArticles = readArticles;
    }

    public ArrayList<String> getFavoriteArticles() {
        return favoriteArticles;
    }

    public void setFavoriteArticles(ArrayList<String> favoriteArticles) {
        this.favoriteArticles = favoriteArticles;
    }

    public String getEmailSchedule() {
        return emailSchedule;
    }

    public void setEmailSchedule(String emailSchedule) {
        this.emailSchedule = emailSchedule;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", preferences=" + preferences +
                ", readArticles=" + readArticles +
                ", favoriteArticles=" + favoriteArticles +
                ", emailSchedule='" + emailSchedule + '\'' +
                '}';
    }
}
