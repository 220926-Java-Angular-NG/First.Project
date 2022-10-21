package com.revature.repos;

import com.revature.models.Tickets;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Tickets repo. Responsible for connecting to tickets table in the database
 * the sql queries are performed here relating to Ticket
 */
public class TicketsRepo implements CRUDDaoInterface<Tickets> {
    /**
     * The Conn.
     */
    Connection conn;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepo.class);

    /**
     * Instantiates a new Tickets repo.
     */
    public TicketsRepo(){
        try {
            // this is the code that can throw an error
            conn = ConnectionManager.getConnection();

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
            LOGGER.error(sqlException.getMessage());

        }
    }


    @Override
    public int create(Tickets tickets) {

        try {
            System.out.println(tickets);
            String sql = "INSERT INTO tickets (id, userid, description, amount) VALUES (default,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, tickets.getUserId());
            pstmt.setString(2, tickets.getDescription());
            pstmt.setDouble(3,tickets.getAmount());

            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // a challenge for associates -> how can we return a user that has an id? instead of just the id?

            // the cursor is right in front of the first (or only) element in our result set
            // calling rs.next() iterates us into the first row
            rs.next();

            return rs.getInt("id");

        }catch(SQLException sqlException){
//            LOGGER.error(sqlException.getMessage());
            System.out.println(sqlException.getMessage());
            System.out.println("here");
        }

        return 0;
    }

    @Override
    public List<Tickets> getAll() {
        ArrayList<Tickets> tickets = new ArrayList<Tickets>();

        try {

            String sql = "SELECT * FROM tickets";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Tickets t = new Tickets();
                t.setId(rs.getInt("id"));
                t.setApproved(rs.getBoolean("approved"));
                t.setUserId(rs.getInt("userid"));
                t.setDescription(rs.getString("description"));
                t.setDenied(rs.getBoolean("denied"));
                t.setAmount(rs.getDouble("amount"));

                tickets.add(t);

            }
            return tickets;

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public Tickets getById(int id) {

        try {

            String sql = "SELECT * FROM tickets WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            Tickets t = null;

            if(rs.next()) {
                t = new Tickets();
                t.setId(rs.getInt("id"));
                t.setApproved(rs.getBoolean("approved"));
                t.setDenied(rs.getBoolean("denied"));
                t.setUserId(rs.getInt("userid"));
                t.setDescription(rs.getString("description"));
                t.setAmount(rs.getDouble("amount"));

                return t;

            }

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Tickets update(Tickets tickets) {
        PreparedStatement pst;
        try {

            String sql = "UPDATE tickets SET approved= ?, denied = ? WHERE id = ?";
            pst = conn.prepareStatement(sql);

            pst.setBoolean(1, tickets.isApproved());
            pst.setBoolean(2, tickets.isDenied());
            pst.setInt(3, tickets.getId());

            pst.executeUpdate();
            pst.close();

            return tickets;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(Tickets tickets) {
        try {
            String sql = "DELETE FROM tickets WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tickets.getId());
            boolean t = pstmt.execute();
            pstmt.close();
            return t;
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

    /**
     * Gets user tickets of a specific id.
     *
     * @param userId the user id
     * @return the user tickets
     */
    public List<Tickets> getUserTickets(int userId) {
        List<Tickets> tickets = getAll();

        List<Tickets> myTickets = new ArrayList<Tickets>();

        for(Tickets t : tickets) {
            if(t.getUserId() == userId) {
                myTickets.add(t);
            }
        }

        return myTickets;

    }

    /**
     * Gets tickets pending.
     *
     * @return the tickets pending
     */
    public List<Tickets> getTicketsPending() {
        List<Tickets> tickets = getAll();

        ArrayList<Tickets> myTickets = new ArrayList<Tickets>();

        for(Tickets t : tickets) {
            if (!t.isApproved() && !t.isDenied()){
                myTickets.add(t);
            }
        }

        return myTickets;
    }
}
