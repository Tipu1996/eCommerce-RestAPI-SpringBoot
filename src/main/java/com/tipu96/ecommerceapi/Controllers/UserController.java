package com.tipu96.ecommerceapi.Controllers;

import com.tipu96.ecommerceapi.Models.CompletedOrder;
import com.tipu96.ecommerceapi.Models.ShoppingCart;
import com.tipu96.ecommerceapi.Models.User;
import com.tipu96.ecommerceapi.Repositories.CompletedOrderRepository;
import com.tipu96.ecommerceapi.Repositories.ShoppingCartRepository;
import com.tipu96.ecommerceapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CompletedOrderRepository completedOrderRepository;

    @PostMapping
    public ResponseEntity<User> RegisterUser(@RequestBody User user){
        ShoppingCart shoppingCart= new ShoppingCart();
        shoppingCartRepository.insert(shoppingCart);

        CompletedOrder completedOrder=new CompletedOrder();
        completedOrderRepository.insert(completedOrder);

        user.setShoppingCartReference(shoppingCart.getId());
        user.setCompletedOrderReference(completedOrder.getId());

        User savedUser=userRepository.insert(user);

        return ResponseEntity.ok(savedUser);
    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> RemoveUser(@PathVariable String id){
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()){
//            userRepository.deleteById(id);
//            return ResponseEntity.ok("Successfully deleted the User with ID:" + id);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID "+id+" does not exist");
//    }
}
