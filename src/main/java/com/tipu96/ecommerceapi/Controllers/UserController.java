package com.tipu96.ecommerceapi.Controllers;

import com.tipu96.ecommerceapi.Models.*;
import com.tipu96.ecommerceapi.Repositories.CompletedOrderRepository;
import com.tipu96.ecommerceapi.Repositories.ItemRepository;
import com.tipu96.ecommerceapi.Repositories.ShoppingCartRepository;
import com.tipu96.ecommerceapi.Repositories.UserRepository;
import com.tipu96.ecommerceapi.Security.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    JwtService jwtService;
    @PostMapping("login")
    public ResponseEntity<String> Login(@RequestBody LoginModel credentials){
        String email = credentials.getEmail();
        String password = credentials.getPassword();
        Optional<User> checkUser= Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
        System.out.println(email+password);
        if(checkUser.isPresent()){
            User user= checkUser.get();
            String jwt= jwtService.generateToken(user);
            System.out.println(jwt);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping
    public ResponseEntity<User> RegisterUser(@RequestBody User user){
        ShoppingCart shoppingCart= new ShoppingCart();
        shoppingCartRepository.insert(shoppingCart);

        CompletedOrder completedOrder=new CompletedOrder();
        completedOrderRepository.insert(completedOrder);

        user.setShoppingCart(shoppingCart);
        user.setCompletedOrder(completedOrder);

        user.setAdmin(false);

        User savedUser=userRepository.insert(user);

        return ResponseEntity.ok(savedUser);
    }
    @SecurityRequirement(name="Bearer Authentication")
    @PutMapping("{id}")
    public ResponseEntity<String> UpdateUser(@RequestBody User user, @PathVariable String id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = null;
        if (principal instanceof UserDetails) {
            currentUserId = ((UserDetails) principal).getUsername();
        }

        if (Objects.equals(currentUserId, id)) {
            Optional<User> retrievedUser = userRepository.findById(id);
            if (retrievedUser.isPresent()) {
                User existingUser = retrievedUser.get();
                if (user.getName() != null) {
                    existingUser.setName(user.getName());
                }
                if (user.getEmail() != null) {
                    existingUser.setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    existingUser.setPassword(user.getPassword());
                }
                if (user.getAddress() != null) {
                    existingUser.setAddress(user.getAddress());
                }
                return ResponseEntity.ok("User updated successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " does not exist");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the userId does not match your user id");
    }
    @SecurityRequirement(name="Bearer Authentication")
    @GetMapping("/profile")
    public ResponseEntity<User> GetUserProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = null;
        if (principal instanceof UserDetails) {
            currentUserId = ((UserDetails) principal).getUsername();
        }
        Optional<User> retrievedUser = null;
        if (!currentUserId.isBlank()) {
            retrievedUser = userRepository.findById(currentUserId);
        }
        return retrievedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @SecurityRequirement(name="Bearer Authentication")
    @DeleteMapping("{id}")
    public ResponseEntity<String> RemoveUser(@PathVariable String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User confirmedUser=user.get();
            shoppingCartRepository.deleteById(confirmedUser.getShoppingCart().getId());
            completedOrderRepository.deleteById(confirmedUser.getCompletedOrder().getId());
            userRepository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted the User with ID:" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID "+id+" does not exist");
    }
    @SecurityRequirement(name="Bearer Authentication")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> GetAllUsers(){
        List<User> users=userRepository.findAll();
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("add-to-cart/{itemId}")
    public ResponseEntity<String> AddItemToShoppingCart(@PathVariable String itemId) {
        // Get the current user from the JWT
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = null;
        if (principal instanceof UserDetails) {
            currentUserId = ((UserDetails) principal).getUsername();
        }

        // Retrieve the user and its shopping cart
        User user = userRepository.findById(currentUserId).get();
        ShoppingCart shoppingCart = user.getShoppingCart();

        Item item = itemRepository.findById(itemId).get();
        if(item.getInventory()>0){
            // Check if the item is already in the shopping cart
            boolean itemExists = false;
            for (CartItem cartItem : shoppingCart.getItems()) {
                if (cartItem.getItemId().equals(itemId)) {
                    itemExists = true;
                    // Increase the quantity of the existing cart item by 1
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    int newInventory = item.getInventory() - 1;
                    item.setInventory(newInventory);
                    itemRepository.save(item);
                    break;
                }
            }

            // If the item is not already in the shopping cart, create a new CartItem
            if (!itemExists) {
                CartItem cartItem = new CartItem();
                cartItem.setItemId(itemId);
                cartItem.setQuantity(1);
                shoppingCart.getItems().add(cartItem);
                int newInventory = item.getInventory() - 1;
                item.setInventory(newInventory);
                itemRepository.save(item);
            }

            // Save the shopping cart
            shoppingCartRepository.save(shoppingCart);

            return ResponseEntity.ok("Item added to shopping cart successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough inventory for the item");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("remove-from-cart/{itemId}")
    public ResponseEntity<String> RemoveItemFromShoppingCart(@PathVariable String itemId) {
        // Get the current user from the JWT
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = null;
        if (principal instanceof UserDetails) {
            currentUserId = ((UserDetails) principal).getUsername();
        }

        // Retrieve the user and its shopping cart
        User user = userRepository.findById(currentUserId).get();
        ShoppingCart shoppingCart = user.getShoppingCart();

        Item item = itemRepository.findById(itemId).get();

        // Find the CartItem corresponding to the item
        for (CartItem cartItem : shoppingCart.getItems()) {
            if (cartItem.getItemId().equals(itemId)) {
                // If the quantity is greater than 1, reduce the quantity by 1
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    int newInventory = item.getInventory() + 1;
                    item.setInventory(newInventory);
                    itemRepository.save(item);
                    shoppingCartRepository.save(shoppingCart);
                    return ResponseEntity.ok("Item quantity reduced by 1");
                } else {
                    // If the quantity is 1, remove the CartItem and add 1 to the inventory
                    shoppingCart.getItems().remove(cartItem);
                    int newInventory = item.getInventory() + 1;
                    item.setInventory(newInventory);
                    itemRepository.save(item);
                    shoppingCartRepository.save(shoppingCart);
                    return ResponseEntity.ok("Item removed from cart");
                }
            }
        }

        // If the item is not found in the shopping cart, return an error message
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in shopping cart");
    }

}
