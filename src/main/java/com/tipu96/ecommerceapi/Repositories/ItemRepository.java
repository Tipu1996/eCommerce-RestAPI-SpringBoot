package com.tipu96.ecommerceapi.Repositories;

import com.tipu96.ecommerceapi.Models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
