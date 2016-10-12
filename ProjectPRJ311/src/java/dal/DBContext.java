package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Catalog;
import models.Order;
import models.Product;
import models.Transaction;
import models.User;

/**
 *
 * @author Dat Le
 *
 * Created on: Oct 9, 2016 - 2:51:31 PM
 */
public class DBContext {

    Connection connection;

    public DBContext() {
        try {
            String userName = "sa";
            String password = "dathy111";
            String url = "jdbc:sqlserver://localhost;databaseName=Store"; //integratedSecurity=true
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser(String username, String password) { // Used for Login servlet
        User user = null;
        String sql = "SELECT * FROM dbo.UserTBL WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("UserId");
                String name = rs.getString("Displayname");
                String phone = username;
                boolean role = rs.getBoolean("Role");
                String address = rs.getString("Address");
                Date created = rs.getDate("Created");
                // if find out user fit condition
                user = new User(userId, name, username, phone, address, password, role, created);
                return user;

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        // not found user
        return user;
    }

    public ArrayList<User> getAllUsers(User user) { // for admin
        if (!user.isRole()) { // asking for true admin role
            return null;
        }
        ArrayList<User> result = new ArrayList<>();

        String sql = "SELECT * FROM dbo.UserTBL";
        try {
            PreparedStatement ps = connection.prepareCall(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("UserId");
                String displayname = rs.getString("Displayname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                boolean role = rs.getBoolean("Role");
                String address = rs.getString("Address");
                Date created = rs.getDate("Created");
                // get all normal user (have role=false)
                if (!role) {
                    User userOut = new User(userId, displayname, username, phone, address, password, role, created);
                    result.add(userOut);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ArrayList<Transaction> getTrans(User user) {
        ArrayList<Transaction> trans = new ArrayList<>();
        String sql = "SELECT * FROM dbo.TransactionTBL WHERE UserId = " + user.getId();
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int transId = rs.getInt(1);
                boolean status = rs.getBoolean(3);
                double amount = rs.getFloat(4);
                String note = rs.getString(5);
                Date created = rs.getDate(6);
                Transaction transaction = new Transaction(transId, user, status, amount, note, created);
                trans.add(transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trans;

    }

    public Catalog getCatalog(int catalogId) {
        String sql = "SELECT * FROM dbo.CatalogTBL WHERE CatalogId = ?";
        Catalog catalog = null;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, catalogId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                catalog = new Catalog(catalogId, rs.getString("Name"), rs.getInt("ParentId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catalog;

    }

    public ArrayList<Catalog> getAllCatalogs() {
        ArrayList<Catalog> catalogs = new ArrayList<>();
        String sql = "SELECT * FROM dbo.CatalogTBL";
        Catalog catalog = null;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                catalog = new Catalog(rs.getInt(1), rs.getString("Name"), rs.getInt("ParentId"));
                catalogs.add(catalog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catalogs;
    }

    public Product getProduct(int proId) {
        Product prod = null;
        String sql = "SELECT * FROM dbo.ProductTBL WHERE ProductId = " + proId;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int producId = rs.getInt(1);
                int catalogId = rs.getInt(2);
                Catalog catalog = getCatalog(catalogId);
                String name = rs.getString(3);
                double price = rs.getFloat(4);
                int discount = rs.getInt(5);
                int quan = rs.getInt(6);
                String content = rs.getString(7);
                String imgPath = rs.getString(8);
                String imgList = rs.getString(9);
                int view = rs.getInt(10);
                prod = new Product(producId, catalog, name, price, discount, quan, content, imgPath, imgList, view);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public ArrayList<Product> getProducts(int catalogId) {
        Catalog catalog = getCatalog(catalogId);
        ArrayList<Product> products = new ArrayList<>();
        Product prod = null;
        String sql = "SELECT * FROM dbo.ProductTBL WHERE CatalogId = " + catalogId;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int producId = rs.getInt(1);
                String name = rs.getString(3);
                double price = rs.getFloat(4);
                int discount = rs.getInt(5);
                int quan = rs.getInt(6);
                String content = rs.getString(7);
                String imgPath = rs.getString(8);
                String imgList = rs.getString(9);
                int view = rs.getInt(10);
                prod = new Product(producId, catalog, name, price, discount, quan, content, imgPath, imgList, view);
                products.add(prod);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return products;

    }

    public ArrayList<Order> getOrders(Transaction trans) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM dbo.OrderTBL Where TransId = ?";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, trans.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int transId = rs.getInt(2); // useless
                int productId = rs.getInt(3);
                Product product = getProduct(productId);
                int quantity = rs.getInt(4);
                double amount = rs.getFloat(5);
                Order order = new Order(orderId, trans, product, quantity, amount);
                orders.add(order);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    public boolean updateTranStt(int tranId, boolean stt) {
        String sql = "UPDATE dbo.TransactionTBL SET \"Status\" = ? WHERE TransId = ?;";
        PreparedStatement ps;
        try {
            ps = connection.prepareCall(sql);
            ps.setBoolean(1, stt);
            ps.setInt(2, tranId);
            return (ps.executeUpdate() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertTran(Transaction tran) {
        String sql = "INSERT INTO dbo.TransactionTBL (UserId, Amount, Note, Created) \n"
                + "	VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, tran.getUser().getId());
            ps.setFloat(2, (float) tran.getAmount());
            ps.setString(3, tran.getNote());
            ps.setDate(3, tran.getCreated());
            return (ps.executeUpdate() != 0);

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insertOrder(Order order) {
        String sql = "INSERT INTO dbo.OrderTBL (TransId, ProductId, Quantity, Amount) \n"
                + "	VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, order.getTrans().getId()); //Transaction Id
            ps.setInt(2, order.getProduct().getId()); //Product Id
            ps.setInt(3, order.getQuantity()); // Order Quantity
            ps.setFloat(4, (float) order.getAmount());
            return (ps.executeUpdate() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateProductQuan(int productId, int quan) {
        String sql = "UPDATE dbo.ProductTBL SET \"Quan\" = ? WHERE ProductId = ?";

        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps = connection.prepareCall(sql);
            ps.setInt(1, quan);
            ps.setInt(2, productId);
            return (ps.executeUpdate() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteOrderById(int orderId) {
        String sql = "DELETE FROM dbo.OrderTBL WHERE OrderId = " + orderId;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            return (ps.executeUpdate() != 0);

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteOrderByTran(int tranId) {
        String sql = "DELETE FROM dbo.OrderTBL WHERE TransId = " + tranId;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            return (ps.executeUpdate() != 0);

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteTran(int tranId) {
        String sql = "DELETE FROM dbo.TransactionTBL WHERE TransId = " + tranId;
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            return (ps.executeUpdate() != 0);

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
