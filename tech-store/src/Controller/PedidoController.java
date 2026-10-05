package Controller;

import Model.Cliente;
import Model.Pedido;
import Model.Producto;
import View.PedidoView;
import java.awt.Color;
import java.util.List;

public class PedidoController {

    private PedidoView view;

    public PedidoController(PedidoView view) {
        this.view = view;
        this.view.setController(this);
    }

    // Crea un pedido nuevo
    public void addPedido() {
        int idPedido = view.getIdPedidoInput();
        String clienteNombre = view.getClienteInput();

        if (idPedido == -1 || clienteNombre.isEmpty()) {
            view.showMessage("Ingrese el ID de pedido y los datos del cliente.", Color.RED);
            return;
        }

        if (Pedido.buscarPorId(idPedido) != null) {
            view.showMessage("Ya existe un pedido con ese ID.", Color.RED);
            return;
        }
    }
    

    // Agrega un producto/detalle a un pedido existente
    public void addDetalleToPedido() {
        int idPedido = view.getIdPedidoInput();
        int idProducto = view.getIdProductoInput();
        int cantidad = view.getCantidadInput();

        if (idPedido == -1 || idProducto == -1 || cantidad <= 0) {
            return;
        }

        Pedido pedido = Pedido.buscarPorId(idPedido);
        if (pedido == null) {
            view.showMessage("No se encontró el pedido #" + idPedido, Color.RED);
            return;
        }

        // Simulación: producto obtenido (puedes reemplazar con ProductoController / DAO)
        Producto producto = new Producto(idProducto, "PROD-" + idProducto, "Producto " + idProducto, "General", 50.0, 100);

        boolean exito = pedido.agregarDetalle(producto, cantidad);
        if (exito) {
            view.showMessage("Producto agregado al pedido #" + idPedido, new Color(0, 128, 0));
            view.clearFields();
        } else {
            view.showMessage("Error: Stock insuficiente.", Color.RED);
        }
    }

    // Muestra la lista de pedidos en la vista
    public void viewPedidos() {
        List<Pedido> pedidos = Pedido.listarPedidos();
        view.displayPedidos(pedidos);
    }

    // Elimina un pedido por su ID
    public void removePedido() {
        int idPedido = view.getIdPedidoInput();
        if (idPedido == -1) return;

        Pedido p = Pedido.buscarPorId(idPedido);
        if (p != null) {
            Pedido.listarPedidos().remove(p);
            view.showMessage("Pedido #" + idPedido + " eliminado.", new Color(0, 128, 0));
            view.clearFields();
            viewPedidos();
        } else {
            view.showMessage("Pedido no encontrado para eliminar.", Color.RED);
        }
    }
}
