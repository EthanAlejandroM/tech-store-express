package Model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private List<DetallePedido> detalles;
    private double total;
    private double iva;

    // 1. Añadido <Pedido> a la lista estática
    private static List<Pedido> pedidosList = new ArrayList<>();

    // Constructor para iniciar un pedido
    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
        this.iva = 0.0;
    }

    // Agregar un producto/detalle al pedido
    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (producto.getStockDisponible() < cantidad) {
            return false; // Stock insuficiente
        }

        producto.setStockDisponible(producto.getStockDisponible() - cantidad);

        DetallePedido detalle = new DetallePedido(producto, cantidad);
        this.detalles.add(detalle);

        calcularTotal();
        return true;
    }

    // Calcular el total recorriendo cada detalle
    public void calcularTotal() {
        double suma = 0.0;

        if (detalles != null) {
            for (DetallePedido d : detalles) {
                suma += d.getSubtotal();
            }
        }

        this.iva = suma * 0.19;
        this.total = suma + iva;
    }

    // Operaciones globales de lista de pedidos

    public static void registrarPedido(Pedido pedido) {
        pedidosList.add(pedido);
    }

    // 2. Añadido <Pedido> al tipo de retorno
    public static List<Pedido> listarPedidos() {
        return pedidosList;
    }


    public static Pedido buscarPorId(int id) {
        for (Pedido p : pedidosList) {
            if (p.getIdPedido() == id) {
                return p;
            }
        }
        return null;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }

    public double getIva() {
        return iva;
    }

     public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        double subtotal = 0.0;

        for (DetallePedido detalle : detalles) {
            subtotal += detalle.getSubtotal();
        }

        return subtotal;
    }
    
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " | Cliente: " + (cliente != null ? cliente.getNombreCompleto() : "N/A") +
                " | Ítems: " + detalles.size() + " | Total: $" + total;
    }
}