package com.pie.planingapispring.dto;

import java.time.LocalDate;
import java.util.Optional;

public class ModifyUserDto {

    private Optional<String> pseudo = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> lastname = Optional.empty();
    private Optional<String> firstname = Optional.empty();
    private Optional<String> avatar = Optional.empty();
    private Optional<LocalDate> birthdate = Optional.empty();
    private Optional<String> phone = Optional.empty();
    private Optional<String> street = Optional.empty();
    private Optional<String> city = Optional.empty();
    private Optional<String> country = Optional.empty();
    private Optional<String> zip = Optional.empty();
    private Optional<String> password = Optional.empty();

    public Optional<String> getPseudo() {
        return pseudo;
    }

    public void setPseudo(Optional<String> pseudo) {
        this.pseudo = pseudo;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public Optional<String> getLastname() {
        return lastname;
    }

    public void setLastname(Optional<String> lastname) {
        this.lastname = lastname;
    }

    public Optional<String> getFirstname() {
        return firstname;
    }

    public void setFirstname(Optional<String> firstname) {
        this.firstname = firstname;
    }

    public Optional<String> getAvatar() {
        return avatar;
    }

    public void setAvatar(Optional<String> avatar) {
        this.avatar = avatar;
    }

    public Optional<LocalDate> getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Optional<LocalDate> birthdate) {
        this.birthdate = birthdate;
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public void setPhone(Optional<String> phone) {
        this.phone = phone;
    }

    public Optional<String> getStreet() {
        return street;
    }

    public void setStreet(Optional<String> street) {
        this.street = street;
    }

    public Optional<String> getCity() {
        return city;
    }

    public void setCity(Optional<String> city) {
        this.city = city;
    }

    public Optional<String> getCountry() {
        return country;
    }

    public void setCountry(Optional<String> country) {
        this.country = country;
    }

    public Optional<String> getZip() {
        return zip;
    }

    public void setZip(Optional<String> zip) {
        this.zip = zip;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }
}
