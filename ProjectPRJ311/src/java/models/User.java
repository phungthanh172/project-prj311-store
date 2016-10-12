package models;

import java.sql.Date;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 9, 2016 - 2:59:59 PM
 */
public class User {

    private int id;
    private String name;
    private String username;
    private String phone;
    private String address;
    private String password;
    private boolean role;
    private Date created;

    public User() {
    }

    public User(int id, String name, String username, String phone, String address, String password, boolean role, Date created) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.role = role;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    
}
