package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private static List<Cliente> clientesListClientes = new ArrayList<>();

    public Cliente(String id, String nombreCompleto, String correoElectronico, String telefono) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public static List<Cliente> getClientesListClientes() {
        return clientesListClientes;
    }

    public static void setClientesListClientes(List<Cliente> clientesListClientes) {
        Cliente.clientesListClientes = clientesListClientes;
    }

    // registrar
    public static void addCliente(Cliente cliente) {
        clientesListClientes.add(cliente);
    }

    // listar
    public static List<Cliente> listarClientes() {
        return clientesListClientes;
    }

    // buscar por ID
    public static Cliente buscarPorId(String id) {
        for (Cliente c : clientesListClientes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombreCompleto +
                " | Correo: " + correoElectronico +
                " | Teléfono: " + telefono;
    }
}
