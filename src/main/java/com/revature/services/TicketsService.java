package com.revature.services;

import com.revature.models.Tickets;
import com.revature.models.User;
import com.revature.repos.TicketsRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Tickets service. Connects all services related to the Tickets
 * That is its responsible for retrieving query results  from tickets repository and sending to the controllers
 */
public class TicketsService {
    /**
     * The Tickets repo.
     */
    TicketsRepo ticketsRepo;

    /**
     * Instantiates a new Tickets service.
     */
    public TicketsService() {
        ticketsRepo = new TicketsRepo();
    }

    /**
     * Add new ticket.
     *
     * @param ticket the ticket
     * @return the tickets
     */
    public Tickets addTicket(Tickets ticket) {
        int id = ticketsRepo.create(ticket);
        Tickets t = ticketsRepo.getById(id);
        return t;
    }

    /**
     * Gets tickets by user id.
     *
     * @param userById the user by id
     * @return the tickets
     */
    public List<Tickets> getTickets(User userById) {
        System.out.println(userById);
        return ticketsRepo.getUserTickets(userById.getId());
    }

    /**
     * Get tickets pending list.
     *
     * @return the list
     */
    public List<Tickets> getTicketsPending(){
        return ticketsRepo.getTicketsPending();
    }

    /**
     * Gets all tickets.
     *
     * @return the all tickets
     */
    public List<Tickets> getAllTickets() {
        return ticketsRepo.getAll();
    }

    /**
     * Gets tickets by id.
     *
     * @param id the id
     * @return the tickets by id
     */
    public Tickets getTicketsById(int id) {
        return ticketsRepo.getById(id);
    }

    /**
     * Gets all tickets denied.
     *
     * @return the tickets denied
     */
    public List<Tickets> getTicketsDenied() {
        List<Tickets> tickets = ticketsRepo.getAll();
        List<Tickets> denied = new ArrayList<Tickets>();
        for (Tickets t : tickets) {
            if (t.isDenied()){
                denied.add(t);
            }
        }
        return denied;
    }

    /**
     * Gets tickets approved.
     *
     * @return the tickets approved
     */
    public List<Tickets> getTicketsApproved() {
        List<Tickets> tickets = ticketsRepo.getAll();
        List<Tickets> approved = new ArrayList<Tickets>();
        for (Tickets t : tickets) {
            if (t.isApproved()){
                approved.add(t);
            }
        }
        return approved;
    }

    /**
     * Update boolean.
     *
     * @param t the t
     * @return the boolean
     */
    public boolean update(Tickets t) {

        return ticketsRepo.update(t) != null;
    }
}
