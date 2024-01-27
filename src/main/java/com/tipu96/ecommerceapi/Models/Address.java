package com.tipu96.ecommerceapi.Models;

import java.util.Objects;

public class Address {
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private String phoneNumber;

    public Address(String street, String zipCode, String city, String country, String phoneNumber) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public Address() {
        this.street="";
        this.zipCode="";
        this.city="";
        this.country="";
        this.phoneNumber="";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(country, address.country) && Objects.equals(phoneNumber, address.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city, country, phoneNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
