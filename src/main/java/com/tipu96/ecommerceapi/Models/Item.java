package com.tipu96.ecommerceapi.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Items")
public class Item {
    @Id
    private String id;
    private String name;
    private String description;
    private String company;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer inventory;
    private String imageUrl;
    private String specifications;

    public Item(String id, String name, String description, String company, String category, Integer price, Integer discount, Integer inventory, String imageUrl, String specifications) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.inventory = inventory;
        this.imageUrl = imageUrl;
        this.specifications = specifications;
    }

    public Item(){
        this.name="";
        this.description="";
        this.company="";
        this.category="";
        this.price=100;
        this.discount=0;
        this.inventory=0;
        this.imageUrl="";
        this.specifications="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(company, item.company) && Objects.equals(category, item.category) && Objects.equals(price, item.price) && Objects.equals(discount, item.discount) && Objects.equals(inventory, item.inventory) && Objects.equals(imageUrl, item.imageUrl) && Objects.equals(specifications, item.specifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, company, category, price, discount, inventory, imageUrl, specifications);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company='" + company + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", inventory=" + inventory +
                ", imageUrl='" + imageUrl + '\'' +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}
