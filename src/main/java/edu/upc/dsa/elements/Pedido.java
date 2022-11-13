package edu.upc.dsa.elements;

import java.util.LinkedList;
import edu.upc.dsa.elements.ListaPedidos;

public class Pedido {
    String idUsuario;
    LinkedList<ListaPedidos> elementos;

    public Pedido(String idUsuario) {
        this.idUsuario = idUsuario;
        this.elementos = new LinkedList<>();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LinkedList<ListaPedidos> getElementos() {
        return elementos;
    }

    public void setElementos(LinkedList<ListaPedidos> elementos) {
        this.elementos = elementos;
    }
    public void addLP(int i, String b001) {
        elementos.add(new ListaPedidos(i, b001));
    }

    public ListaPedidos getLP(int i) {
        return elementos.get(i);
    }
}
