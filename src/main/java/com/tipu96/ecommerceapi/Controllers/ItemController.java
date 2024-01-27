package com.tipu96.ecommerceapi.Controllers;

import com.tipu96.ecommerceapi.Models.Item;
import com.tipu96.ecommerceapi.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<String> AddAnItem(@RequestBody Item item){
        Item createdItem = itemRepository.insert(item);
        return ResponseEntity.created(null).body("Item Added to DB with ID: "+ createdItem.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> DeleteAnItem(@PathVariable String id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted the item with ID:" + id);
        }
        return ResponseEntity.notFound().build();
    }
}
