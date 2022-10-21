package com.revature.models;

/**
 * The type  User that maps to a single user in the user table.
 */
public class User {
   private int id;
   private String firstname;
   private String lastname;
   private String email;
   private String password;
   private boolean ismanager = false;


    /**
     * Instantiates a new User.
     */
// we have our no args constructor
    // if we do not create a default constructor one is created for us
    public User() {
    }

    //all args constructor

    /**
     * Instantiates a new User.
     *
     * @param id        the id
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     * @param password  the password
     */
    public User(int id, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }


    /**
     * Instantiates a new User.
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     * @param password  the password
     */
//some-args constructor
    // here we are not including the id because we have
    //set our id to be "Serial" aka to auto generate in our db
    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    /**
     * Is ismanager boolean.
     *
     * @return the boolean
     */
    public boolean isIsmanager() {
        return ismanager;
    }

    /**
     * Sets ismanager.
     *
     * @param ismanager the ismanager
     */
    public void setIsmanager(boolean ismanager) {
        this.ismanager = ismanager;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Has null boolean.
     *
     * @return the boolean
     */
    public boolean hasNull(){
        return firstname == null || lastname == null || email == null || password == null;
    }
}
