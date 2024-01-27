package com.tipu96.ecommerceapi.Repositories;

import com.tipu96.ecommerceapi.Models.CompletedOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompletedOrderRepository extends MongoRepository<CompletedOrder, String> {
}
