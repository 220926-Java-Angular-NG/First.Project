package com.revature.controllers;

import com.revature.models.Login;
import com.revature.models.Tickets;
import com.revature.models.User;
import com.revature.services.TicketsService;
import com.revature.services.UserService;
import io.javalin.http.Handler;

import java.util.List;

/**
 * This is the employee handler class
 * it connects the handler to the service class
 * it has Handler methods that are responsible for returning the actions to the requests
 * The methods in this controller class are similar to the features
 * <p>
 */
public class EmployeeController {

    /**
     * The Service.
     */
    UserService service;
    /**
     * The Tickets service.
     */
    TicketsService ticketsService;

    /**
     * Instantiates a new Employee controller.
     */
    public EmployeeController(){
        service = new UserService();
        ticketsService = new TicketsService();
    }


    /**
     * The Login. performs a login returns a user as json
     * and wrong credentials if the details are incorrect
     */
    public Handler login = context -> {
        try{
            Login l = context.bodyAsClass(Login.class);

            if (l.hasNull()){
                context.result("No parameters found").status(400);
            }else{

                System.out.println(l);

                User user = service.getUserByEmailAndPassword(l.getEmail(), l.getPassword());
                if(user != null){
                    context.json(user).status(202);
                } else {
                    context.result("Incorrect Login Details").status(400);
                }
            }

        }catch (Exception e){
            context.result("Invalid Parameters").status(400);
        }

    };


    /**
     * The Register. accepts user information in the context body and creates a new user
     * it checks if the username is alredy registered and returns an error
     */
    public Handler register = context -> {
        User user = context.bodyAsClass(User.class);
        if (user.hasNull()){
            context.result("Invalid Arguments").status(400);

        }else{
            try {
                //this is the id that we are getting from our url
                if (service.getUserByEmail(user.getEmail())){
                    context.result("User With Username already exists").status(400);
                }else{
                    user.setIsmanager(false);
                    int k = service.createUser(user);
                    user.setId(k);
                    context.json(user).status(202);
                }

            }catch(Exception e){
                context.result("Invalid Parameters").status(400);
            }
        }

    };

    /**
     * The Add ticket. handler
     * Adding ticket to the database when a ticket information is availed in the body
     */
    public Handler addTicket = context -> {
        try{
            Tickets ticket = context.bodyAsClass(Tickets.class);

            if (ticket.hasNull()){
                context.result("Invalid Arguments").status(400);
            }else{
                ticket.setApproved(false);
                Tickets t = ticketsService.addTicket(ticket);
                if (t != null){
                    context.json(t).status(202);
                }else{
                    context.result("Ticket Not Created").status(400);
                }
            }

        }catch(Exception e){
            context.result("Invalid Parameters").status(400);
        }


    };

    /**
     * The View my tickets.
     * Handler to return the tickets of a specific employee
     */
    public Handler viewMyTickets = context -> {
        String param = context.pathParam("id");
        System.out.println(param);
        try {
            int id = Integer.parseInt(param);
            System.out.println(id);
            User u = service.getUserById(id);
            System.out.println(u);
            List<Tickets> myTickets = ticketsService.getTickets(u);
            System.out.println(myTickets);
            context.json(myTickets).status(200);

        }catch (Exception nFExceptio){
            context.json("Error").status(404);
        }
    };

}
