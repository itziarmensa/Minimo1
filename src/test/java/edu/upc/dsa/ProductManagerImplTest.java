package edu.upc.dsa;

import edu.upc.dsa.elements.Pedido;
import edu.upc.dsa.elements.Producto;
import edu.upc.dsa.manager.ProductManager;
import edu.upc.dsa.manager.ProductManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductManagerImplTest {

    ProductManager pm;

    @Before
    public void setUp() {
        pm = new ProductManagerImpl();
        pm.addUser("1111111", "Juan", "lopez");
        pm.addUser("2222222",  "David", "Rincon");
        pm.addUser("3333333",  "Juan", "Hernández");

        pm.addProduct("B001", "Coca cola", 2);
        pm.addProduct("C002", "Café amb gel", 1.5);
        pm.addProduct("A002", "Donut", 2.25);
        pm.addProduct("A003", "Croissant", 1.25);

        prepareOrders();
    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    private void prepareOrders() {
        Pedido o1 = new Pedido("1111111");
        o1.addLP(3, "B001");
        o1.addLP(2, "C002");

        Pedido o2 = new Pedido("1111111");
        o2.addLP(3, "A002");
        o2.addLP(1, "B001");

        Pedido o3 = new Pedido("2222222");
        o3.addLP(3, "B001");
        o3.addLP(2, "A002");


        Assert.assertEquals(0, this.pm.numOrders());
        this.pm.addOrder(o1);
        Assert.assertEquals(1, this.pm.numOrders());
        this.pm.addOrder(o2);
        Assert.assertEquals(2, this.pm.numOrders());
        this.pm.addOrder(o3);
        Assert.assertEquals(3, this.pm.numOrders());
    }

    @Test
    public void testAddOrder() {
        Assert.assertEquals(3, this.pm.numUsers());
        Assert.assertEquals(4, this.pm.numProducts());
        Assert.assertEquals(3, this.pm.numOrders());
        // ...
        Pedido o4 = new Pedido("2222222");
        o4.addLP(3, "B001");
        o4.addLP(2, "A003");
        this.pm.addOrder(o4);

        Assert.assertEquals(4, this.pm.numOrders());

    }

    @Test
    public void processOrderTest() {
        Assert.assertEquals(3, this.pm.numUsers());
        Assert.assertEquals(4, this.pm.numProducts());
        Assert.assertEquals(3, this.pm.numOrders());

        Pedido order1 = this.pm.processOrder();
        Assert.assertEquals(2, this.pm.numOrders());
        Assert.assertEquals(3, order1.getLP(0).getQuantity());
        Assert.assertEquals(2, order1.getLP(1).getQuantity());
        Assert.assertEquals(3, this.pm.numSales("B001"));

        Pedido order2 = this.pm.processOrder();
        Assert.assertEquals(1, this.pm.numOrders());
        Assert.assertEquals(4, this.pm.numSales("B001"));

        Pedido order3 = this.pm.processOrder();
        Assert.assertEquals(0, this.pm.numOrders());
        Assert.assertEquals(7, this.pm.numSales("B001"));
    }


    @Test
    public void productsSortByPrice() {
        List<Producto> products = this.pm.productsByPrice();

        Assert.assertEquals("A003", products.get(0).getIdProducto());
        Assert.assertEquals(1.25, products.get(0).getPrecio(), 0);

        Assert.assertEquals("C002", products.get(1).getIdProducto());
        Assert.assertEquals(1.5, products.get(1).getPrecio(), 0);

        Assert.assertEquals("B001", products.get(2).getIdProducto());
        Assert.assertEquals(2, products.get(2).getPrecio(), 0);

        Assert.assertEquals("A002", products.get(3).getIdProducto());
        Assert.assertEquals(2.25, products.get(3).getPrecio(), 0);
    }

    @Test
    public void productsSortByNumSales() {
        processOrderTest();

        List<Producto> products = this.pm.productsBySales();
        Assert.assertEquals("B001", products.get(0).getIdProducto());
        Assert.assertEquals("Coca cola", products.get(0).getNombreProducto());
        Assert.assertEquals(7, products.get(0).getNumVentas());

        Assert.assertEquals("A002", products.get(1).getIdProducto());
        Assert.assertEquals("Donut", products.get(1).getNombreProducto());
        Assert.assertEquals(5, products.get(1).getNumVentas());

        Assert.assertEquals("C002", products.get(2).getIdProducto());
        Assert.assertEquals("Café amb gel", products.get(2).getNombreProducto());
        Assert.assertEquals(2, products.get(2).getNumVentas());

        Assert.assertEquals("A003", products.get(3).getIdProducto());
        Assert.assertEquals("Croissant", products.get(3).getNombreProducto());
        Assert.assertEquals(0, products.get(3).getNumVentas());
    }

    @Test
    public void ordersByUserTest() {
        processOrderTest();
        List<Pedido> orders1 = this.pm.ordersByUser("1111111");
        Assert.assertEquals(2, orders1.size());

        List<Pedido> orders2 = this.pm.ordersByUser("2222222");
        Assert.assertEquals(1, orders2.size());

    }
}
