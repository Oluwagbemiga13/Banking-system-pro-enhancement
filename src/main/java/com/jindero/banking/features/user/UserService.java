package com.jindero.banking.features.user;

import com.jindero.banking.shared.exception.EmailAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  // Vyhledat všechny Usery
    public List<User> findAll(){
    return userRepository.findAll();
    }

    //Vyhledat Usera podle id
  public Optional<User> findById(Long id){
    if (id == null || id <= 0){
      return Optional.empty();
    } else {
      return userRepository.findById(id);
    }
  }

  //Vyhledat podle emailu
  public Optional<User> findByEmail(String email){
    if (email == null){
      return Optional.empty();
    } else {
      return userRepository.findByEmail(email);
    }
  }

  // Vytvořit Usera
  public User createUser(User user)  {
    if (userRepository.existsByEmail(user.getEmail())){
      throw new EmailAlreadyExistException("Email " + user.getEmail() + " already exists");
    } else {
      return userRepository.save(user);
    }
  }

  //Update Usera
  public User updateUser( Long id, User newUserData){
    if (userRepository.existsById(id)){

      if (userRepository.existsByEmail(newUserData.getEmail())){
        throw new EmailAlreadyExistException("Email " + newUserData.getEmail() + " already exists");
      }

      User existingUser = userRepository.findById(id).get();

      existingUser.setFirstName(newUserData.getFirstName());
      existingUser.setLastName(newUserData.getLastName());
      existingUser.setPhoneNumber(newUserData.getPhoneNumber());
      existingUser.setEmail(newUserData.getEmail());

      return userRepository.save(existingUser);
    } else {
      throw new RuntimeException("User with ID " + id + " not found");
    }
  }

  //Smazat Usera
  public void deleteUser(Long id){
    if (userRepository.existsById(id)){

      userRepository.deleteById(id);
    } else {
      throw new RuntimeException("User with ID "+ id + " not found");
    }
  }

}
