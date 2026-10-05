package Controller;

import View.ClienteView;
import View.MenuView;
import View.PedidoView;
import View.ProductoView;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        this.view.setController(this);
    }

    public void iniciar() {
        view.setVisible(true);
    }

    public void abrirProductos() {
        ProductoView viewProducto = new ProductoView();
        ProductoController controllerProducto = new ProductoController(viewProducto);
        viewProducto.setController(controllerProducto);
        viewProducto.setVisible(true);
        controllerProducto.viewProductos();
    }

    public void abrirClientes() {
        ClienteView viewCliente = new ClienteView();
        ClienteController controllerCliente = new ClienteController(viewCliente);
        viewCliente.setController(controllerCliente);
        viewCliente.setVisible(true);
        controllerCliente.viewClientes();
    }

    public void abrirPedidos() {
        PedidoView viewPedido = new PedidoView();
        PedidoController controllerPedido = new PedidoController(viewPedido);
        viewPedido.setController(controllerPedido);
        viewPedido.setVisible(true);
        controllerPedido.viewPedidos();
    }
    
    public void salir() {
        System.exit(0);
    }
}
