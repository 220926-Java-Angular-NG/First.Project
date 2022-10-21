package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

/**
 * The type User controller.
 * This code is written to be able to operate employee uri's
 */
public class UserController {

    /**
     * The Service.
     */
    UserService service;

    /**
     * Instantiates a new User controller.
     */
    public UserController(){
        service = new UserService();
    }

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService){
        service = userService;
    }

    /**
     * The Create new user after passing data vauues
     */
//create
    public Handler createNewUser = context -> {

        // grab the object from the request body (postman)
        // send the incoming user to our service , so it can
        // reach out to our repo layer and execute the request

    User user = context.bodyAsClass(User.class); //change the json from postman to on object
    context.json("hey");
    System.out.println(user);
    int id = service.createUser(user);

    if(id > 0){
        //valid number ( it represents the primary key )
        user.setId(id);
        context.json(user).status(200);
    } else {
        // something went wrong
        context.result("User not created").status(400);
    }

};


    //read

    /**
     * The Get all users.
     */
//all
    public Handler getAllUsers = context -> {
        context.json(service.getAllUsers()); //service.getAllUsers()
    };



    //by id

    /**
     * The Get user by id.
     */
    public Handler getUserById = context -> {
        String param = context.pathParam("id");

        // we are going to wrap this logic in a try catch

        try {
            //this is the id that we are getting from our url
            int id =  Integer.parseInt(param);
            User user = service.getUserById(id);

            if(user != null){
                context.json(user).status(202);
            } else {
                context.result("User not found").status(400);
            }

        }catch(NumberFormatException nFException){
            System.out.println(nFException.getMessage());
        }
    };

    //update


    /**
     * The Update user.
     */
    public Handler updateUser = context -> {

        User user = context.bodyAsClass(User.class);

        user = service.updateUser(user);

        if(user != null){
            context.json(user).status(202);
        } else {
            context.result("Could not update user").status(400);
        }
    };



    //delete

    /**
     * The Delete user.
     */
    public Handler deleteUser = context -> {
        User user = context.bodyAsClass(User.class);

        // note this should be refactored
        if(user != null ){
            context.status(200).json(service.deleteUser(user));
        } else {
            context.status(400).result("Could not delete user");
        }
    };


    /**
     * The Delete user by id.
     */
    public Handler deleteUserById = context -> {
      String param = context.pathParam("id");

        try {
            //this is the id that we are getting from our url
            int id =  Integer.parseInt(param);

                context.json(service.deleteUserById(id)).status(202);

        }catch(NumberFormatException nFMException){
            System.out.println(nFMException.getMessage());
        }
    };


}
