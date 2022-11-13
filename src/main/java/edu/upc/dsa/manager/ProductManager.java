package edu.upc.dsa.manager;

import edu.upc.dsa.elements.Pedido;
import edu.upc.dsa.elements.Producto;

import java.util.List;

public interface ProductManager {
    public List<Producto> productsByPrice();
    public List<Producto> productsBySales();
    public void addOrder(Pedido order);
    public Pedido processOrder();
    public List<Pedido> ordersByUser(String userId);
    /////////////////////////////////////////////
    ////////////////////////////////////////////




    public void addUser(String s, String name, String surname);
    public void addProduct(String productId, String name, double price);

    public Producto getProduct(String productId);

    public int numUsers();
    public int numProducts();

    public int numOrders();

    public int numSales(String b001);

    public int size();
}
