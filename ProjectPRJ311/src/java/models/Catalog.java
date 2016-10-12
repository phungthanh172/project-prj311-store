package models;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 10, 2016 - 9:15:44 PM
 */

public class Catalog {
    private int id;
    private String name;
    private int parentId;

    public Catalog() {
    }

    public Catalog(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    

}
