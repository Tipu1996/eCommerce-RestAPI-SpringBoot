package com.tipu96.ecommerceapi.Controllers;

import com.tipu96.ecommerceapi.Models.CompletedOrder;
import com.tipu96.ecommerceapi.Models.LoginModel;
import com.tipu96.ecommerceapi.Models.ShoppingCart;
import com.tipu96.ecommerceapi.Models.User;
import com.tipu96.ecommerceapi.Repositories.CompletedOrderRepository;
import com.tipu96.ecommerceapi.Repositories.ShoppingCartRepository;
import com.tipu96.ecommerceapi.Repositories.UserRepository;
import com.tipu96.ecommerceapi.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> GetAllUsers(){
        List<User> users=userRepository.findAll();
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }
}
