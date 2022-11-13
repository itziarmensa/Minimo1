package edu.upc.dsa.elements;
import java.util.LinkedList;
import java.util.List;

public class Usuari {
    String idUsuario;
    String nombreUsuario;
    String apellidoUsuario;
    List<Pedido> pedidosProcesados;

    public Usuari(String idUsuario, String nombreUsuario,String apellidoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.pedidosProcesados = new LinkedList<>();
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Pedido> getPedidosProcesados() {
        return pedidosProcesados;
    }

    public void setPedidosProcesados(List<Pedido> pedidosProcesados) {
        this.pedidosProcesados = pedidosProcesados;
    }

    public void addProcessedOrder(Pedido order) {
        this.pedidosProcesados.add(order);
    }
}
