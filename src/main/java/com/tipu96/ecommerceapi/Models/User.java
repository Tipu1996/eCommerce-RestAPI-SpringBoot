package com.tipu96.ecommerceapi.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Users")
public class User {
    @Id
    private String id;
    @DBRef
    private String shoppingCartReference;
    @DBRef
    private String completedOrderReference;
    private String name;
    private String email;
    private String password;
    private String isAdmin;
    private Address address;

    public User() {
        this.id="";
        this.shoppingCartReference="";
        this.completedOrderReference="";
        this.name="";
        this.email="";
        this.password="";
        this.isAdmin="";
        this.address=new Address();
    }

    public User(String id, String shoppingCartReference, String completedOrderReference, String name, String email, String password, String isAdmin, Address address) {
        this.id = id;
        this.shoppingCartReference = shoppingCartReference;
        this.completedOrderReference = completedOrderReference;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShoppingCartReference() {
        return shoppingCartReference;
    }

    public void setShoppingCartReference(String shoppingCartReference) {
        this.shoppingCartReference = shoppingCartReference;
    }

    public String getCompletedOrderReference() {
        return completedOrderReference;
    }

    public void setCompletedOrderReference(String completedOrderReference) {
        this.completedOrderReference = completedOrderReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(shoppingCartReference, user.shoppingCartReference) && Objects.equals(completedOrderReference, user.completedOrderReference) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(isAdmin, user.isAdmin) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingCartReference, completedOrderReference, name, email, password, isAdmin, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", shoppingCartReference='" + shoppingCartReference + '\'' +
                ", completedOrderReference='" + completedOrderReference + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", address=" + address +
                '}';
    }
}