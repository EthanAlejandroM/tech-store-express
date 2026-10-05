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

    // Crear pedido con su primer producto
    public void addPedido() {

        int idPedido = view.getIdPedidoInput();
        String idCliente = view.getClienteInput();
        int idProducto = view.getIdProductoInput();
        int cantidad = view.getCantidadInput();

        if (idPedido == -1 || idCliente.isEmpty() || idProducto == -1 || cantidad <= 0) {
            view.showMessage("Ingrese todos los datos correctamente.", Color.RED);
            return;
        }

        if (Pedido.buscarPorId(idPedido) != null) {
            view.showMessage("Ya existe un pedido con el ID: " + idPedido, Color.RED);
            return;
        }

        Cliente cliente = Cliente.buscarPorId(idCliente);

        if (cliente == null) {
            view.showMessage(
                    "No existe un cliente registrado con el ID: " + idCliente,
                    Color.RED);
            return;
        }

        Producto producto = Producto.buscarPorId(idProducto);

        if (producto == null) {
            view.showMessage(
                    "No existe un producto con el ID: " + idProducto,
                    Color.RED);
            return;
        }

        Pedido nuevoPedido = new Pedido(idPedido, cliente);

        boolean exito = nuevoPedido.agregarDetalle(producto, cantidad);

        if (!exito) {
            view.showMessage(
                    "No hay suficiente stock para el producto seleccionado.",
                    Color.RED);
            return;
        }

        Pedido.registrarPedido(nuevoPedido);

        view.showMessage(
                "Pedido #" + idPedido + " creado exitosamente.",
                new Color(0, 128, 0));

        view.clearFields();
        viewPedidos();
    }

    // Agregar producto a un pedido existente
    public void addDetalleToPedido() {

        int idPedido = view.getIdPedidoInput();
        int idProducto = view.getIdProductoInput();
        int cantidad = view.getCantidadInput();

        if (idPedido == -1 || idProducto == -1 || cantidad <= 0) {
            view.showMessage(
                    "Ingrese datos válidos para el producto y la cantidad.",
                    Color.RED);
            return;
        }

        Pedido pedido = Pedido.buscarPorId(idPedido);

        if (pedido == null) {
            view.showMessage(
                    "No se encontró el pedido #" + idPedido,
                    Color.RED);
            return;
        }

        Producto producto = Producto.buscarPorId(idProducto);

        if (producto == null) {
            view.showMessage(
                    "No existe un producto con el ID: " + idProducto,
                    Color.RED);
            return;
        }

        boolean exito = pedido.agregarDetalle(producto, cantidad);

        if (exito) {
            view.showMessage(
                    "Producto agregado al pedido #" + idPedido,
                    new Color(0, 128, 0));

            view.clearFields();
            viewPedidos();

        } else {
            view.showMessage(
                    "No hay suficiente stock.",
                    Color.RED);
        }
    }

    // Mostrar pedidos
    public void viewPedidos() {
        List<Pedido> pedidos = Pedido.listarPedidos();
        view.displayPedidos(pedidos);
    }

    // Eliminar pedido
    public void removePedido() {

        int idPedido = view.getIdPedidoInput();

        if (idPedido == -1) {
            view.showMessage(
                    "Ingrese el ID del pedido a eliminar.",
                    Color.RED);
            return;
        }

        Pedido pedido = Pedido.buscarPorId(idPedido);

        if (pedido != null) {
            Pedido.listarPedidos().remove(pedido);

            view.showMessage(
                    "Pedido #" + idPedido + " eliminado.",
                    new Color(0, 128, 0));

            view.clearFields();
            viewPedidos();

        } else {
            view.showMessage(
                    "Pedido no encontrado para eliminar.",
                    Color.RED);
        }
    }
}