package models;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 10, 2016 - 9:17:18 PM
 */

public class Product {
    private int id;
    private Catalog catalog;
    private String name;
    private double price;
    private int discount;
    private int quan;
    private String content;
    private String imgPath;
    private String imgList;
    private int view;

    public Product() {
    }

    public Product(int id, Catalog catalog, String name, double price, int discount, int quan, String content, String imgPath, String imgList, int view) {
        this.id = id;
        this.catalog = catalog;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quan = quan;
        this.content = content;
        this.imgPath = imgPath;
        this.imgList = imgList;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
    

}
