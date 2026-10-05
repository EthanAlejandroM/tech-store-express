package View;

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
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID Pedido:"));
        idPedidoField = new JTextField();
        inputPanel.add(idPedidoField);

        inputPanel.add(new JLabel("ID Cliente / Nombre:"));
        idClienteField = new JTextField();
        inputPanel.add(idClienteField);

        inputPanel.add(new JLabel("ID Producto (Agregar Ítem):"));
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
        exitButton = new JButton("Salir");

        inputPanel.add(addButton);
        inputPanel.add(addDetalleButton);
        inputPanel.add(viewButton);
        inputPanel.add(removeButton);
        inputPanel.add(exitButton);

        messageLabel = new JLabel("Ingrese detalles y presione una opción.");
        messageLabel.setForeground(Color.BLUE);
        inputPanel.add(messageLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Lista visual
        listModel = new DefaultListModel<>();
        pedidoList = new JList<>(listModel);
        pedidoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(pedidoList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Pedidos"));
        add(scrollPane, BorderLayout.CENTER);

        // Listeners delegando al Controlador
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.addPedido();
            }
        });

        addDetalleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.addDetalleToPedido();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.viewPedidos();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.removePedido();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void setController(PedidoController controller) {
        this.controller = controller;
    }

    // Retorna los datos necesarios para registrar un pedido nuevo
    public int getIdPedidoInput() {
        String idStr = idPedidoField.getText().trim();
        if (idStr.isEmpty()) {
            showMessage("Por favor, ingrese el ID del pedido.", Color.RED);
            return -1;
        }
        try {
            return Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            showMessage("El ID del pedido debe ser un entero.", Color.RED);
            return -1;
        }
    }

    public String getClienteInput() {
        return idClienteField.getText().trim();
    }

    public int getIdProductoInput() {
        String prodStr = idProductoField.getText().trim();
        if (prodStr.isEmpty()) {
            showMessage("Por favor, ingrese el ID del producto.", Color.RED);
            return -1;
        }
        try {
            return Integer.parseInt(prodStr);
        } catch (NumberFormatException ex) {
            showMessage("El ID del producto debe ser numérico.", Color.RED);
            return -1;
        }
    }

    public int getCantidadInput() {
        String cantStr = cantidadField.getText().trim();
        if (cantStr.isEmpty()) {
            showMessage("Por favor, ingrese la cantidad.", Color.RED);
            return -1;
        }
        try {
            return Integer.parseInt(cantStr);
        } catch (NumberFormatException ex) {
            showMessage("La cantidad debe ser un número entero.", Color.RED);
            return -1;
        }
    }

    // Muestra los pedidos en el JList
    public void displayPedidos(List<Pedido> pedidos) {
        listModel.clear();
        if (pedidos == null || pedidos.isEmpty()) {
            listModel.addElement("No hay pedidos registrados.");
        } else {
            for (Pedido p : pedidos) {
                listModel.addElement(p.toString());
            }
        }
        showMessage("Lista actualizada.", new Color(0, 128, 0)); // Color verde oscuro
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