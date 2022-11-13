package edu.upc.dsa.elements;

public class ListaPedidos {
    int quantity;
    String product;

    public ListaPedidos(int quantity, String product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
