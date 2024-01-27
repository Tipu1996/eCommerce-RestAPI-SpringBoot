package com.tipu96.ecommerceapi.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "CompletedOrders")
public class CompletedOrder {
    @Id
    private String id;
    private List<BoughtItem> items;

    public CompletedOrder(String id, List<BoughtItem> items) {
        this.id = id;
        this.items = items;
    }

    public CompletedOrder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BoughtItem> getItems() {
        return items;
    }

    public void setItems(List<BoughtItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedOrder that = (CompletedOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items);
    }

    @Override
    public String toString() {
        return "CompletedOrder{" +
                "id='" + id + '\'' +
                ", items=" + items +
                '}';
    }
}