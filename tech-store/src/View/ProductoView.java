package View;

import Model.Producto;
import Controller.ProductoController;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoView extends JFrame {
    private JTextField idField;
    private JTextField codigoField;
    private JTextField nombreField;
    private JTextField categoriaField;
    private JTextField precioUniField;
    private JTextField stockDisField;

    private JButton addButton;
    private JButton updateButton;
    private JButton viewButton;
    private JButton removeButton;
    private JButton exitButton;

    private JList productoList;
    private DefaultListModel listModel;
    private JLabel messageLabel;

    private ProductoController controller;

    public ProductoView() {
        setTitle("Gestión de Productos - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(580, 620);
        setLocationRelativeTo(null);

        // Panel de entrada 
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID Producto:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        inputPanel.add(codigoField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        inputPanel.add(categoriaField);

        inputPanel.add(new JLabel("Precio Unitario:"));
        precioUniField = new JTextField();
        inputPanel.add(precioUniField);

        inputPanel.add(new JLabel("Stock Disponible:"));
        stockDisField = new JTextField();
        inputPanel.add(stockDisField);

        // Botones de control
        addButton = new JButton("Agregar Producto");
        updateButton = new JButton("Actualizar Producto");
        viewButton = new JButton("Ver Todos");
        removeButton = new JButton("Eliminar por ID");
        exitButton = new JButton("Salir");

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(viewButton);
        inputPanel.add(removeButton);
        inputPanel.add(exitButton);

        messageLabel = new JLabel("Ingrese detalles y presione una opción.");
        messageLabel.setForeground(Color.BLUE);
        inputPanel.add(new JLabel()); // Espacio
        inputPanel.add(messageLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Lista visual
        listModel = new DefaultListModel<>();
        productoList = new JList<>(listModel);
        productoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productoList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        add(scrollPane, BorderLayout.CENTER);

        // Listeners delegando al Controlador
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.addProducto();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.updateProducto();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.viewProductos();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) controller.removeProducto();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void setController(ProductoController controller) {
        this.controller = controller;
    }

    // Lee los datos de las cajas de texto y construye un objeto Producto
    public Producto getProductoInput() {
        String idStr = idField.getText().trim();
        String codigo = codigoField.getText().trim();
        String nombre = nombreField.getText().trim();
        String categoria = categoriaField.getText().trim();
        String precioStr = precioUniField.getText().trim();
        String stockStr = stockDisField.getText().trim();

        if (idStr.isEmpty() || codigo.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            showMessage("Por favor, complete todos los campos.", Color.RED);
            return null;
        }

        int id;
        double precio;
        int stock;

        try {
            id = Integer.parseInt(idStr);
            precio = Double.parseDouble(precioStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException ex) {
            showMessage("ID y Stock deben ser enteros, y Precio un valor numérico.", Color.RED);
            return null;
        }

        clearFields();
        return new Producto(id, codigo, nombre, categoria, precio, stock);
    }

    // Lee solo el ID para eliminar o buscar
    public int getIdInput() {
        String idStr = idField.getText().trim();
        if (idStr.isEmpty()) {
            showMessage("Por favor ingrese el ID.", Color.RED);
            return -1;
        }
        try {
            int id = Integer.parseInt(idStr);
            clearFields();
            return id;
        } catch (NumberFormatException ex) {
            showMessage("El ID debe ser un número.", Color.RED);
            return -1;
        }
    }

    // Muestra los productos en la lista utilizando su método toString()
    public void displayProductos(List<Producto> productos) {
        listModel.clear();
        if (productos == null || productos.isEmpty()) {
            listModel.addElement("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                listModel.addElement(p.toString());
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
        codigoField.setText("");
        nombreField.setText("");
        categoriaField.setText("");
        precioUniField.setText("");
        stockDisField.setText("");
    }
}