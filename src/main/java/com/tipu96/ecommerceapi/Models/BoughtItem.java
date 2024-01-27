package com.tipu96.ecommerceapi.Models;

import java.util.Objects;

public class BoughtItem {
    private String itemId;
    private Integer quantity;

    public BoughtItem(String itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public BoughtItem() {
        this.quantity=0;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtItem that = (BoughtItem) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, quantity);
    }

    @Override
    public String toString() {
        return "BoughtItems{" +
                "itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}