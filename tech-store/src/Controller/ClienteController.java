package Controller;

import Model.Cliente;
import View.ClienteView;
import java.awt.Color;
import java.util.List;

public class ClienteController {
    private ClienteView view;

    public ClienteController(ClienteView view) {
        this.view = view;
        this.view.setController(this);
    }

    // Iniciar la vista
    public void iniciar() {
        view.setVisible(true);
        viewClientes();
    }

    // Agregar Cliente
    public void addCliente() {
        Cliente nuevoCliente = view.getClienteInput();

        if (nuevoCliente != null) {
            if (Cliente.buscarPorId(nuevoCliente.getId()) != null) {
                view.showMessage("Ya existe un cliente con el ID " + nuevoCliente.getId(), Color.RED);
                return;
            }

            Cliente.addCliente(nuevoCliente);
            view.showMessage("Cliente agregado correctamente.", Color.GREEN);
            viewClientes();
        }
    }

    // Ver/Listar Clientes
    public void viewClientes() {
        List<Cliente> lista = Cliente.listarClientes();
        view.displayClientes(lista);
    }
}
