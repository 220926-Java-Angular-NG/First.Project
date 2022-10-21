package com.revature.controllers;

import com.revature.models.Login;
import com.revature.models.Tickets;
import com.revature.models.User;
import com.revature.services.TicketsService;
import com.revature.services.UserService;
import io.javalin.http.Handler;

/**
 * The type Manager controller.A controller contains the handlers that connect to services
 * which in turn connect to the database. Its responsible for performing all requests from a manager
 */
public class ManagerController {

    /**
     * The User service.
     */
    UserService userService;
    /**
     * The Tickets service.
     */
    TicketsService ticketsService;

    /**
     * Instantiates a new Manager controller.
     */
    public ManagerController(){
        userService = new UserService();
        ticketsService = new TicketsService();
    }

    /**
     * The Login.
     */
    public Handler login = context -> {
        try{
            Login l = context.bodyAsClass(Login.class);

            if (l.hasNull()){
                context.result("No parameters found").status(400);
            }else{

                System.out.println(l);

                User user = userService.getUserByEmailAndPassword(l.getEmail(), l.getPassword());

                if(user != null){
                    if (user.isIsmanager()) {
                        context.json(user).status(202);
                    }else{
                        context.result("Unauthorized").status(400);
                    }

                } else {
                    context.result("Incorrect Login Details").status(400);
                }
            }

        }catch (Exception e){
            context.result("Invalid Parameters").status(400);
        }

    };


    /**
     * The Register.
     */
    public Handler register = context -> {
        User user = context.bodyAsClass(User.class);
        if (user.hasNull()){
            context.result("Invalid Arguments").status(400);

        }else{
            try {
                //this is the id that is being retrieved from the url
                user.setIsmanager(true);
                if (userService.getUserByEmail(user.getEmail())){
                    context.result("User With Username already exists").status(400);
                }else{
                    int k = userService.createUser(user);
                    user.setId(k);
                    context.json(user).status(202);
                }

            }catch(NumberFormatException nFException){
                context.result("Error").status(400);
            }
        }

    };

    /**
     * The Approve.
     */
    public Handler approve = context -> {
        String param = context.pathParam("id");
        if (param.equals("")){
            context.json("Invalid Id").status(404);
        }else{
            try {
                int id = Integer.parseInt(param);
                Tickets t = ticketsService.getTicketsById(id);

                if (t != null){
                    t.setDenied(false);
                    t.setApproved(true);
                    if (ticketsService.update(t)){
                        context.json(t).status(202);
                    }else{
                        context.result("Error ticket not updates").status(400);
                    }

                }else{
                    context.result("Ticket Not Found").status(400);
                }
            }catch(NumberFormatException nFException){
                context.result("Error").status(400);
            }

        }

    };

    /**
     * The Deny.
     */
    public Handler deny = context -> {
        String param = context.pathParam("id");
        if (param.equals("")){
            context.json("Invalid Id").status(404);
        }else{
            try {
                int id = Integer.parseInt(param);
                Tickets t = ticketsService.getTicketsById(id);

                if (t != null){
                    t.setDenied(true);
                    t.setApproved(false);

                    if (ticketsService.update(t)){
                        context.json(t).status(202);
                    }else{
                        context.result("Error ticket not updates").status(400);
                    }

                }else{
                    context.result("Ticket Not Found").status(400);
                }
            }catch(NumberFormatException nFException){
                context.result("Error").status(400);
            }

        }

    };

    /**
     * The Get all tickets handler.
     */
    public Handler getAll = context -> {
        context.json(ticketsService.getAllTickets()).status(200);
    };

    /**
     * The View ticket handler.
     */
    public Handler viewTicket = context -> {
        String param = context.pathParam("id");
        if (param.equals("")){
            context.json("Invalid Id").status(404);
        }else{
            try {
                int id = Integer.parseInt(param);
                Tickets t = ticketsService.getTicketsById(id);
                if (t == null){
                    context.json("Error").status(404);
                }else{
                    context.json(t).status(200);
                }

            }catch (Exception e) {
                context.json("Invalid Id").status(404);
            }
        }

    };

    /**
     * The View pending handler.
     */
    public Handler viewPending = context -> {
        context.json(ticketsService.getTicketsPending()).status(200);
    };


    /**
     * The View denied handler.
     */
    public Handler viewDenied = context -> {
        context.json(ticketsService.getTicketsDenied()).status(200);
    };

    /**
     * The View approved handler.
     */
    public Handler viewApproved = context -> {
        context.json(ticketsService.getTicketsApproved()).status(200);
    };

}
