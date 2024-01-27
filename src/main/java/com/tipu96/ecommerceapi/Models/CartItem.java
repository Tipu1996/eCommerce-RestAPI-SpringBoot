package com.tipu96.ecommerceapi.Models;

import java.util.Objects;

public class CartItem {
    private String itemId;
    private Integer quantity;

    public CartItem(String itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public CartItem() {
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
        CartItem cartItem = (CartItem) o;
        return Objects.equals(itemId, cartItem.itemId) && Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, quantity);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}