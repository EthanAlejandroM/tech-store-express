package View;

import Model.DetallePedido;
import Model.Pedido;
import Controller.PedidoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PedidoView extends JFrame {

    private JTextField idPedidoField;
    private JTextField idClienteField;
    private JTextField idProductoField;
    private JTextField cantidadField;

    private JButton addButton;
    private JButton addDetalleButton;
    private JButton viewButton;
    private JButton removeButton;
    private JButton exitButton;

    private JList<String> pedidoList;
    private DefaultListModel<String> listModel;

    private JLabel messageLabel;

    private PedidoController controller;

    public PedidoView() {

        setTitle("Gestión de Pedidos - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(580, 620);
        setLocationRelativeTo(null);

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        inputPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID Pedido:"));
        idPedidoField = new JTextField();
        inputPanel.add(idPedidoField);

        inputPanel.add(new JLabel("ID Cliente:"));
        idClienteField = new JTextField();
        inputPanel.add(idClienteField);

        inputPanel.add(new JLabel("ID Producto:"));
        idProductoField = new JTextField();
        inputPanel.add(idProductoField);

        inputPanel.add(new JLabel("Cantidad:"));
        cantidadField = new JTextField();
        inputPanel.add(cantidadField);

        // Botones de control
        addButton = new JButton("Crear Pedido");
        addDetalleButton = new JButton("Agregar Producto a Pedido");
        viewButton = new JButton("Ver Todos");
        removeButton = new JButton("Eliminar por ID");
        exitButton = new JButton("Cerrar");

        inputPanel.add(addButton);
        inputPanel.add(addDetalleButton);
        inputPanel.add(viewButton);
        inputPanel.add(removeButton);
        inputPanel.add(exitButton);

        messageLabel = new JLabel(
                "Ingrese detalles y presione una opción.");
        messageLabel.setForeground(Color.BLUE);
        inputPanel.add(messageLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Lista visual
        listModel = new DefaultListModel<>();
        pedidoList = new JList<>(listModel);
        pedidoList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(pedidoList);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Lista de Pedidos"));

        add(scrollPane, BorderLayout.CENTER);

        // Listener: Crear pedido
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.addPedido();
                }
            }
        });

        // Listener: Agregar producto
        addDetalleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.addDetalleToPedido();
                }
            }
        });

        // Listener: Ver pedidos
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.viewPedidos();
                }
            }
        });

        // Listener: Eliminar pedido
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.removePedido();
                }
            }
        });

        // Listener: Salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setController(PedidoController controller) {
        this.controller = controller;
    }

    // Obtiene el ID del pedido como int
    public int getIdPedidoInput() {

        String idStr = idPedidoField.getText().trim();

        if (idStr.isEmpty()) {
            showMessage(
                    "Por favor, ingrese el ID del pedido.",
                    Color.RED);
            return -1;
        }

        try {
            return Integer.parseInt(idStr);

        } catch (NumberFormatException ex) {

            showMessage(
                    "El ID del pedido debe ser un entero.",
                    Color.RED);
            return -1;
        }
    }

    // Obtiene el ID del cliente como String
    public String getClienteInput() {
        return idClienteField.getText().trim();
    }

    // Obtiene el ID del producto como int
    public int getIdProductoInput() {

        String prodStr = idProductoField.getText().trim();

        if (prodStr.isEmpty()) {
            showMessage(
                    "Por favor, ingrese el ID del producto.",
                    Color.RED);
            return -1;
        }

        try {
            return Integer.parseInt(prodStr);

        } catch (NumberFormatException ex) {

            showMessage(
                    "El ID del producto debe ser numérico.",
                    Color.RED);
            return -1;
        }
    }

    // Obtiene la cantidad como int
    public int getCantidadInput() {

        String cantStr = cantidadField.getText().trim();

        if (cantStr.isEmpty()) {
            showMessage(
                    "Por favor, ingrese la cantidad.",
                    Color.RED);
            return -1;
        }

        try {
            return Integer.parseInt(cantStr);

        } catch (NumberFormatException ex) {

            showMessage(
                    "La cantidad debe ser un número entero.",
                    Color.RED);
            return -1;
        }
    }

    // Muestra los pedidos
    public void displayPedidos(List<Pedido> pedidos) {
        listModel.clear();

        for (Pedido pedido : pedidos) {
            for (DetallePedido detalle : pedido.getDetalles()) {

                String detallePedido = "Pedido #" + pedido.getIdPedido()
                        + " | Cliente: "
                        + (pedido.getCliente() != null
                                ? pedido.getCliente().getNombreCompleto()
                                : "N/A")
                        + " | Producto ID: "
                        + detalle.getProducto().getId()
                        + " | Cantidad: "
                        + detalle.getCantidad()
                        + " | Subtotal: $"
                        + detalle.getSubtotal();

                listModel.addElement(detallePedido);
            }
            String resumen = "   → Subtotal: $" + pedido.getSubtotal()
                    + " | IVA (19%): $" + pedido.getIva()
                    + " | Total: $" + pedido.getTotal();

            listModel.addElement(resumen);
        }
    }

    public void showMessage(String message) {
        showMessage(message, Color.BLACK);
    }

    public void showMessage(String message, Color color) {

        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    public void clearFields() {

        idPedidoField.setText("");
        idClienteField.setText("");
        idProductoField.setText("");
        cantidadField.setText("");
    }
}
