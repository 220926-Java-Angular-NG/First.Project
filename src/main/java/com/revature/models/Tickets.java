package com.revature.models;

/**
 * The type Tickets class that maps to a ticket object the fields here are the same fields i the database.
 */
public class Tickets {
    private int id;
    private boolean approved = false;
    private int userId;

    private boolean denied = false;

    private String description;
    private double amount = -1;

    /**
     * Instantiates a new Tickets.
     *
     * @param id          the id
     * @param approved    the approved
     * @param denied      the denied
     * @param userId      the user id
     * @param description the description
     */
    public Tickets(int id, boolean approved, boolean denied, int userId, String description,double amount) {
        this.id = id;
        this.approved = approved;
        this.userId = userId;
        this.denied = denied;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Instantiates a new Tickets.
     *
     * @param approved    the approved
     * @param denied      the denied
     * @param userId      the user id
     * @param description the description
     */
    public Tickets(boolean approved, boolean denied, int userId, String description, double amount) {
        this.approved = approved;
        this.userId = userId;
        this.description = description;
        this.denied = denied;
        this.amount = amount;
    }

    /**
     * Instantiates a new Tickets.
     */
    public Tickets() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Is denied boolean.
     *
     * @return the boolean
     */
    public boolean isDenied() {
        return denied;
    }

    /**
     * Sets denied.
     *
     * @param denied the denied
     */
    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * Is approved boolean.
     *
     * @return the boolean
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets approved.
     *
     * @param approved the approved
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    /**
     * Has null boolean.
     * "Null" meaning nothing
     * @return the boolean
     */
    public boolean hasNull() {
        return userId < 0 || description == null || amount < 0;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", approved=" + approved +
                ", userId=" + userId +
                ", denied=" + denied +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
