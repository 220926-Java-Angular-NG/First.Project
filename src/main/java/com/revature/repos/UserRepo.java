package com.revature.repos;

import com.revature.models.User;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The reason I used this code was because..."
 * This is the class that is responsible for performing
 * create read update and delete functions in the user database
 */
public class UserRepo implements CRUDDaoInterface<User> {

    /**
     * The Conn.
     */
    public Connection conn;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepo.class);

    /**
     * Instantiates a new User repo.
     */
    public UserRepo(){

        // Note: certain methods throw errors because there are different instances of what can go wrong
        // in order to handle those errors in a way that saves the application from crashing
        // we wrap those methods or blocks of code in a "try{}catch(){}"block
        try {
            // this is the code that can throw an error
            conn = ConnectionManager.getConnection();
            //System.out.println("Connection Succesful ");

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());

           LOGGER.error(sqlException.getMessage());
        }
    }


    @Override
    public int create(User user) {

        //although it says create we are inserting into a table that is already created
        //however we are still creating a new row...

        try {
            String sql = "INSERT INTO users (id, first_name, last_name, email, pass_word, is_manager) VALUES (default,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.isIsmanager());

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
            LOGGER.error(sqlException.getMessage());
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<User>();

        try {

            String sql = "SELECT * FROM users";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setIsmanager(rs.getBoolean("is_manager"));

                users.add(user);

            }

            return users;

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public User getById(int id) {

        try {

            String sql = "SELECT * FROM users WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            // we are returning a user
            // therefore we have to create a new instance of a user from the database

            User user = new User();
            System.out.println(id);

            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setIsmanager(rs.getBoolean("is_manager"));
                return user;
            }





        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public User update(User user) {

        try {

            String sql = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getEmail());
            pstmt.setInt(2,user.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()){
                user.setEmail(rs.getString("email"));
            }

            return user;

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(User user) {
        //Delete from tablename where id = ?

        try {

            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, user.getId());

            return pstmt.execute();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return true;
    }

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteById(int id) {
        //Delete from tablename where id = ?

        try {

            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            //pstmt.execute() returns a boolean
            // it returns false if the executed statement returns void

            pstmt.execute();
            return true;
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

    /**
     * Gets user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the by email and password
     */
    public User getByEmailAndPassword(String email, String password) {
        try {

            ResultSet rs;
            String sql = "SELECT * FROM users WHERE lower(email) = ? and pass_word = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email.toLowerCase());
            pstmt.setString(2,password);

            rs = pstmt.executeQuery();

            User user = null;

            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setIsmanager(rs.getBoolean("is_manager"));
                return user;
            }


        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the by email
     */
    public boolean getByEmail(String email) {
        List<User> users = getAll();

        for(User user : users){
            if(user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
}
