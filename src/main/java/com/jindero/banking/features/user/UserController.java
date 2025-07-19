package com.jindero.banking.features.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;

  //Konstruktor
  public UserController(UserService userService, UserRepository userRepository){
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @GetMapping("")
  public List<User> getAllUsers(){
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id){
    Optional<User> user = userService.findById(id);

    if (user.isPresent()){
      return user.get();
    } else {
      return null;
    }
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user){
    User creatUser = userService.createUser(user);
    return ResponseEntity.ok(creatUser);
  }

}
