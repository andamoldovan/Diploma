package com.licenta.project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field(value = "first_name")
    private String firstName;
    @Field(value = "last_name")
    private String lastName;
    @Field(value = "username")
    private String userName;
    @Field(value = "email")
    private String email;
    @Field(value = "password")
    private String password;
    @Field(value = "preferences")
    private ArrayList<String> preferences;
    @Field(value = "read_articles")
    private int readArticles;
    @Field(value = "favorite_articles")
    private ArrayList<String> favoriteArticles;
    @Field(value = "email_schedule")
    @Indexed(name = "email_schedule-index", direction = IndexDirection.ASCENDING)
    private String emailSchedule;


    public User() {
    }

    public User(String firstName, String lastName, String userName, String email, String password,
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
        return "User{" +
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
