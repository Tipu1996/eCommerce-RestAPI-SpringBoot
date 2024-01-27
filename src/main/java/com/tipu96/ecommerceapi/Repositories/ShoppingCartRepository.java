package com.tipu96.ecommerceapi.Repositories;

import com.tipu96.ecommerceapi.Models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
