package View;

import Controller.MenuController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JButton productoButton;
    private JButton clienteButton;
    private JButton pedidoButton;
    private JButton exitButton;

    private MenuController controller;

    public MenuView() {
        setTitle("TechStore Express - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 350);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Gestión de TechStore Express", SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        productoButton = new JButton("Gestionar Productos");
        clienteButton = new JButton("Gestionar Clientes");
        pedidoButton = new JButton("Gestionar pedidos");
        exitButton = new JButton("Salir");

        buttonPanel.add(productoButton);
        buttonPanel.add(clienteButton);
        buttonPanel.add(pedidoButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        productoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.abrirProductos();
                }
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.abrirClientes();
                }
            }
        });

        pedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.abrirPedidos();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.salir();
                }
            }
        });
    }

    public void setController(MenuController controller) {
        this.controller = controller;
    }
}
