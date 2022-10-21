package com.revature.models;

/**
 * The type Login. This class is used to map to the login details provided
 */
public class Login {

    private String email;
    private String password;

    /**
     * Instantiates a new Login.
     */
    public Login(){
        email = null;
        password = null;
    }

    /**
     * Instantiates a new Login.
     *
     * @param email    the email
     * @param password the password
     */
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
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

    /**
     * Has null boolean.
     *
     * @return the boolean
     */
    public boolean hasNull() {
        return password == null || email == null;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
