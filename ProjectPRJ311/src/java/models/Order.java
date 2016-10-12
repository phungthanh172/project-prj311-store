package models;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 10, 2016 - 9:25:53 PM
 */

public class Order {
    private int id;
    private Transaction trans;
    private Product product;
    private int quantity;
    private double amount;

    public Order() {
    }

    public Order(int id, Transaction trans, Product product, int quantity, double amount) {
        this.id = id;
        this.trans = trans;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    

}
