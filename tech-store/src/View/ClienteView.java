package View;

import Model.Cliente;
import Controller.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteView extends JFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField correoField;
    private JTextField telefonoField;

    private JButton addButton;
    private JButton viewButton;
    private JButton exitButton;

    private JList<Cliente> clienteList;
    private DefaultListModel<Cliente> listModel;
    private JLabel messageLabel;

    private ClienteController controller;

    public ClienteView() {
        setTitle("Gestión de Clientes - MVC");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(580, 500);
        setLocationRelativeTo(null);

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID Cliente:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nombre Completo:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Correo Electrónico:"));
        correoField = new JTextField();
        inputPanel.add(correoField);

        inputPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        inputPanel.add(telefonoField);

        addButton = new JButton("Agregar Cliente");
        viewButton = new JButton("Ver Todos");
        exitButton = new JButton("Cerrar");

        inputPanel.add(addButton);
        inputPanel.add(viewButton);
        inputPanel.add(exitButton);

        messageLabel = new JLabel("Ingrese datos y presione una opción.");
        inputPanel.add(new JLabel());
        inputPanel.add(messageLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Lista visual
        listModel = new DefaultListModel<>();
        clienteList = new JList<>(listModel);
        clienteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clienteList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        add(scrollPane, BorderLayout.CENTER);

        // Listeners delegando al Controlador
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.addCliente();
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.viewClientes();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setController(ClienteController controller) {
        this.controller = controller;
    }

    // Lee los datos de las cajas de texto y construye un objeto Cliente
    public Cliente getClienteInput() {
        String id = idField.getText().trim();
        String nombre = nombreField.getText().trim();
        String correo = correoField.getText().trim();
        String telefono = telefonoField.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            showMessage("Por favor, complete todos los campos.", Color.RED);
            return null;
        }

        clearFields();
        return new Cliente(id, nombre, correo, telefono);
    }

    // Muestra los clientes en la lista
    public void displayClientes(List<Cliente> clientes) {
        listModel.clear();

        if (clientes == null || clientes.isEmpty()) {
            listModel.addElement(new Cliente("-", "No hay clientes registrados.", "-", "-"));
        } else {
            for (Cliente cliente : clientes) {
                listModel.addElement(cliente);
            }
        }

        showMessage("Lista actualizada.", Color.GREEN);
    }

    public void showMessage(String message) {
        showMessage(message, Color.BLACK);
    }

    public void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    public void clearFields() {
        idField.setText("");
        nombreField.setText("");
        correoField.setText("");
        telefonoField.setText("");
    }
}
