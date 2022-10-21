package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.utils.CRUDDaoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User service its the class responsible for connecting the handlers to the repo class.
 * When actions are performed from the controller they are called in the repository where the statements are executed
 */
public class UserService {

  private UserRepo userRepo;


  /**
   * Instantiates a new User service.
   */
// we are creating a new instance of our UserRepo
  public UserService(){
      userRepo = new UserRepo();
  }

  /**
   * Instantiates a new User service.
   *
   * @param userRepo the user repo
   */
// here we are passing in an existing UserRepo
  public UserService(UserRepo userRepo){
      this.userRepo = userRepo;
  }

    // create

  /**
   * Create user int.
   *
   * @param user the user
   * @return the int
   */
  public int createUser(User user){
      return userRepo.create(user);
    }

    //read all

  /**
   * Get all users list.
   *
   * @return the list
   */
  public List<User> getAllUsers(){
      return userRepo.getAll();
    }

    //readById

  /**
   * Get user by id user.
   *
   * @param id the id
   * @return the user
   */
  public User getUserById(int id){
      return userRepo.getById(id);
    }


    //update

  /**
   * Update user user.
   *
   * @param user the user
   * @return the user
   */
  public User updateUser(User user){
      return userRepo.update(user);
    }


    //delete

  /**
   * Delete user boolean.
   *
   * @param user the user
   * @return the boolean
   */
  public boolean deleteUser(User user){
      return userRepo.delete(user);
    }

  /**
   * Delete user by id boolean.
   *
   * @param id the id
   * @return the boolean
   */
  public boolean deleteUserById(int id){
    return userRepo.deleteById(id);
  }


  /**
   * Get user by email and password user.
   *
   * @param email    the email
   * @param password the password
   * @return the user
   */
  public User getUserByEmailAndPassword(String email, String password){
        return userRepo.getByEmailAndPassword(email, password);
    }

  /**
   * Get user by email boolean.
   *
   * @param email the email
   * @return the boolean
   */
  public boolean getUserByEmail(String email){
        return userRepo.getByEmail(email);
    }




}
