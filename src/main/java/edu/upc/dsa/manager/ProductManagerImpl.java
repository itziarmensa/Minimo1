package edu.upc.dsa.manager;

import edu.upc.dsa.elements.ListaPedidos;
import edu.upc.dsa.elements.Pedido;
import edu.upc.dsa.elements.Producto;
import edu.upc.dsa.elements.Usuari;
import org.apache.log4j.Logger;
import edu.upc.dsa.manager.ProductManager;

import java.util.*;

public class ProductManagerImpl implements ProductManager{
    private static ProductManager instance;
    List<Producto> productos;
    Queue<Pedido> pedidos;
    Map<String, Usuari> usuarios;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public ProductManagerImpl(){
        this.productos = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.pedidos = new LinkedList<>();
    }
    public List<Producto> productsByPrice(){
        this.productos.sort((Producto p1, Producto p2) -> Double.compare(p1.getPrecio(), p2.getPrecio())); //orden ascendente
        return this.productos;
    }

    public List<Producto> productsBySales(){
        this.productos.sort((Producto p2, Producto p1) -> (p1.getNumVentas() - p2.getNumVentas())); //orden descendente
        return this.productos;
    }

    public void addOrder(Pedido order){
        this.pedidos.add(order);
    }

    public Pedido processOrder(){
        Pedido pedido = this.pedidos.poll(); //elimina i returnea el primer element de la cua
        executeProcess(pedido);
        return pedido;
    }

    public List<Pedido> ordersByUser(String userId){
        return this.usuarios.get(userId).getPedidosProcesados();
    }

    public void addUser(String s, String name, String surname){
        this.usuarios.put(s, new Usuari(s,name,surname));
    }

    public void addProduct(String productId, String name, double price){
        if (!getProduct(productId).isNull()){
            return;
        }
        this.productos.add(new Producto(name, price,0,productId));
    }

    public int numUsers() {
        return usuarios.size();
    }


    public int numProducts() {
        return productos.size();
    }


    public int numOrders() {
        return this.pedidos.size();
    }

    public Producto getProduct(String productId) {
        Producto product = new Producto("",0,0,"");
        for(Producto p : this.productos){
            if(Objects.equals(p.getIdProducto(), productId)){
                product = p;
            }
        }
        return product;
    }

    public int numSales(String b001) {
        return this.getProduct(b001).getNumVentas();
    }

    private void executeProcess(Pedido order) {
        for(ListaPedidos element : order.getElementos()) {
            Producto product = this.getProduct(element.getProduct());
            int index = productos.indexOf(product);
            product.sold(element.getQuantity());
            productos.set(index, product);
        }
        this.usuarios.get(order.getIdUsuario()).addProcessedOrder(order);
    }

    public int size() {
        int ret = this.productos.size();
        logger.info("size " + ret);

        return ret;
    }

}
