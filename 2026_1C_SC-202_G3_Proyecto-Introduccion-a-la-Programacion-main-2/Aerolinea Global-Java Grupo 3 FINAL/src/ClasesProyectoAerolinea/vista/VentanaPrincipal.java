/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea.vista;

import ClasesProyectoAerolinea.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal con los 3 botones para acceder a cada modulo
 * @author eltro
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Aerolinea Global-Java");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Panel principal 
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Titulo
        JLabel titulo = new JLabel("Aerolinea Global-Java", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        // Botones de modulos
        JButton btnMod1 = new JButton("Modulo 1 - Aviones y Vuelos");
        JButton btnMod2 = new JButton("Modulo 2 - Venta de Tiquetes");
        JButton btnMod3 = new JButton("Modulo 3 - Servicios");

        panel.add(titulo);
        panel.add(btnMod1);
        panel.add(btnMod2);
        panel.add(btnMod3);

        add(panel);

        // Al hacer click abre la ventana correspondiente
        btnMod1.addActionListener(e -> new VentanaFlota().setVisible(true));
        btnMod2.addActionListener(e -> new VentanaVenta().setVisible(true));
        btnMod3.addActionListener(e -> new VentanaServicios().setVisible(true));
    }

    // punto de entrada del programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
