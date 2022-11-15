package edu.upc.dsa.services;

import edu.upc.dsa.elements.Pedido;
import edu.upc.dsa.elements.Producto;
import edu.upc.dsa.elements.Usuari;
import edu.upc.dsa.manager.ProductManager;
import edu.upc.dsa.manager.ProductManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Api(value = "/product", description = "Endpoint to Product Service")
@Path("/product")
public class ProductServices {
    private ProductManager productManager;

    public ProductServices(){
        this.productManager = ProductManagerImpl.getInstance();
        if(productManager.size()==0){
            this.productManager.addProduct("B001", "Coca cola", 2);
            this.productManager.addProduct("C002", "Café amb gel", 1.5);
            this.productManager.addProduct("A002", "Donut", 2.25);
            this.productManager.addProduct("A003", "Croissant", 1.25);

        }
    }

    @GET
    @ApiOperation(value = "get all Products", notes = "Ordered by Price")
    @ApiResponses(value={
            @ApiResponse(code = 201, message = "Seuccessful", response = Producto.class, responseContainer = "List"),
    })
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByPrice(){
        List<Producto> products = this.productManager.productsByPrice();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all Products", notes = "Ordered by Sales")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales() {

        List<Producto> products = this.productManager.productsBySales();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(products) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @POST
    @ApiOperation(value = "add a new product", notes = "new Product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Producto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Producto product) {

        if (product.getIdProducto()==null || product.getNombreProducto()==null)  return Response.status(500).entity(product).build();
        this.productManager.addProduct(product.getIdProducto(), product.getNombreProducto(), product.getPrecio());
        return Response.status(201).entity(product).build();
    }

    @POST
    @ApiOperation(value = "add a new order", notes = "from User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuari.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Pedido order) {

        if (order.getIdUsuario()==null )  return Response.status(500).entity(order).build();
        this.productManager.addOrder(order);
        return Response.status(201).entity(order).build();
    }

    @PUT
    @ApiOperation(value = "process an Order", notes = "fifo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuari.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processOrder() {

        Pedido order = this.productManager.processOrder();
        return Response.status(201).entity(order).build();
    }

    @POST
    @ApiOperation(value = "add a new user", notes = "new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuari.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Usuari user) {

        if (user.getIdUsuario()==null || user.getNombreUsuario()==null)  return Response.status(500).entity(user).build();
        this.productManager.addUser(user.getIdUsuario(), user.getNombreUsuario(), user.getApellidoUsuario());
        return Response.status(201).entity(user).build();
    }

    @GET
    @ApiOperation(value = "get Product", notes = "From Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") String productId) {

        Producto product = this.productManager.getProduct(productId);
        if (Objects.equals(product.getIdProducto(), "")) return Response.status(404).build();
        else  return Response.status(201).entity(product).build();
    }

    @GET
    @ApiOperation(value = "get orders processed from User", notes = "from User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserOrders(@PathParam("userId") String userId) {

        List<Pedido> orders = this.productManager.ordersByUser(userId);

        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(orders) {};
        return Response.status(201).entity(entity).build()  ;
    }
}
