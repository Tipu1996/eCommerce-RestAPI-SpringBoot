package com.tipu96.ecommerceapi.Controllers;

import com.tipu96.ecommerceapi.Models.Item;
import com.tipu96.ecommerceapi.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> GetAllItems(){
        List<Item> items = itemRepository.findAll();
        if(items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }
    @GetMapping("{id}")
    public ResponseEntity<Item> GetItemById(@PathVariable String id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            Item foundItem=item.get();
            return ResponseEntity.status(HttpStatus.FOUND).body(foundItem);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<String> AddItem(@RequestBody Item item){
        Item createdItem = itemRepository.insert(item);
        return ResponseEntity.created(null).body("Item Added to DB with ID: "+ createdItem.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> DeleteItem(@PathVariable String id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted the item with ID:" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with ID "+id+" does not exist");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> UpdateItem(@RequestBody Item item, @PathVariable String id){
        Optional<Item> retrievedItem= itemRepository.findById(id);
        if(retrievedItem.isPresent()){
            Item existingItem= retrievedItem.get();
            if(item.getName()!=null){
                existingItem.setName(item.getName());
            }
            if(item.getDescription()!=null){
                existingItem.setDescription(item.getDescription());
            }
            if(item.getCategory()!=null){
                existingItem.setCategory(item.getCategory());
            }
            if(item.getCompany()!=null){
                existingItem.setCompany(item.getCompany());
            }
            if(item.getPrice()!=null){
                existingItem.setPrice(item.getPrice());
            }
            if(item.getDiscount()!=null){
                existingItem.setDiscount(item.getDiscount());
            }
            if(item.getInventory()!=null){
                existingItem.setInventory(item.getInventory());
            }
            if(item.getImageUrl()!=null){
                existingItem.setImageUrl(item.getImageUrl());
            }
            if(item.getSpecifications()!=null){
                existingItem.setSpecifications(item.getSpecifications());
            }

            itemRepository.save(existingItem);
            return ResponseEntity.ok("Item updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with ID "+id+" does not exist");
    }
}
