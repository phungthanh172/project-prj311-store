package models;

import java.sql.Date;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 10, 2016 - 9:06:34 PM
 */

public class Transaction {
    private int id;
    private User user;
    private boolean status;
    private double amount;
    private String note;
    private Date created;

    public Transaction() {
    }

    public Transaction(int id, User user, boolean status, double amount, String note, Date created) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.amount = amount;
        this.note = note;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    

}
