package edu.upc.dsa.elements;

import java.util.Objects;

public class Producto {
    String nombreProducto;
    double precio;
    int numVentas;
    String idProducto;

    public Producto(String nombreProducto, double precio, int numVentas, String idProducto) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.numVentas = numVentas;
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas = numVentas;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public void sold(int quantity) {
        this.numVentas = numVentas + quantity;
    }

    public boolean isNull(){
        return(Objects.equals(idProducto, ""));
    }
}
