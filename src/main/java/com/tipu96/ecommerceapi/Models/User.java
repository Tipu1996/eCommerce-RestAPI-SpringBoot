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
    private ShoppingCart shoppingCart;
    @DBRef
    private CompletedOrder completedOrder;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
    private Address address;

    public User() {
        this.name="";
        this.email="";
        this.password="";
        this.isAdmin=false;
        this.address=new Address();
    }

    public User(String id, ShoppingCart shoppingCart, CompletedOrder completedOrder, String name, String email, String password, boolean isAdmin, Address address) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.completedOrder = completedOrder;
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public CompletedOrder getCompletedOrder() {
        return completedOrder;
    }

    public void setCompletedOrder(CompletedOrder completedOrder) {
        this.completedOrder = completedOrder;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
        return isAdmin == user.isAdmin && Objects.equals(id, user.id) && Objects.equals(shoppingCart, user.shoppingCart) && Objects.equals(completedOrder, user.completedOrder) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingCart, completedOrder, name, email, password, isAdmin, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", shoppingCart=" + shoppingCart +
                ", completedOrder=" + completedOrder +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", address=" + address +
                '}';
    }
}