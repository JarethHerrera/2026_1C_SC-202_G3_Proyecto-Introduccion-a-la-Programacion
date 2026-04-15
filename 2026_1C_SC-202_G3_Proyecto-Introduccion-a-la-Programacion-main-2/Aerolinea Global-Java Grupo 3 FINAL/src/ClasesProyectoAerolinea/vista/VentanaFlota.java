/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea.vista;

import ClasesProyectoAerolinea.*;
import javax.swing.*;
import java.awt.*;

/**
 * Modulo 1 - Permite registrar aviones, ver su mapa grafico y crear vuelos
 * @author eltro
 */

public class VentanaFlota extends JFrame {

    // acceso unico a los datos del sistema
    Calculo calculo = Calculo.HayEstanciasiono();

    // campos para registrar avion
    JTextField txtMatricula    = new JTextField();
    JTextField txtModelo       = new JTextField();
    JTextField txtFilasPrimera = new JTextField("2");
    JTextField txtFilasEjec    = new JTextField("3");
    JTextField txtFilasEcon    = new JTextField("10");
    JTextField txtColumnas     = new JTextField("6");

    // campos para crear vuelo
    JTextField txtCodVuelo  = new JTextField();
    JTextField txtOrigen    = new JTextField();
    JTextField txtDestino   = new JTextField();
    JTextField txtFecha     = new JTextField("2025-12-25");
    JTextField txtPrecio    = new JTextField("200");
    JComboBox<String> cmbAviones = new JComboBox<>();

    // listas para mostrar lo registrado
    DefaultListModel<String> modeloAviones = new DefaultListModel<>();
    DefaultListModel<String> modeloVuelos  = new DefaultListModel<>();

    // panel donde se dibuja el avion
    JPanel panelDibujo = new JPanel();

    public VentanaFlota() {
        setTitle("Modulo 1 - Aviones y Vuelos");
        setSize(820, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // dos pestanias: una para aviones, otra para vuelos
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Aviones", crearTabAviones());
        tabs.addTab("Vuelos",  crearTabVuelos());
        add(tabs);
    }

    // ---- PESTANIA AVIONES ----
    private JPanel crearTabAviones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // formulario de registro
        JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Registrar Avion"));
        form.add(new JLabel("Matricula:"));       form.add(txtMatricula);
        form.add(new JLabel("Modelo:"));          form.add(txtModelo);
        form.add(new JLabel("Filas 1ra Clase:")); form.add(txtFilasPrimera);
        form.add(new JLabel("Filas Ejecutiva:")); form.add(txtFilasEjec);
        form.add(new JLabel("Filas Economica:")); form.add(txtFilasEcon);
        form.add(new JLabel("Columnas:"));        form.add(txtColumnas);

        JButton btnRegistrar = new JButton("Registrar Avion");
        JButton btnVerMapa   = new JButton("Ver Mapa del Avion");
        form.add(btnRegistrar);
        form.add(btnVerMapa);

        // lista de aviones registrados
        JList<String> listaAviones = new JList<>(modeloAviones);
        JScrollPane scroll = new JScrollPane(listaAviones);
        scroll.setBorder(BorderFactory.createTitledBorder("Aviones Registrados"));
        scroll.setPreferredSize(new Dimension(200, 150));

        // panel izquierdo: formulario + lista
        JPanel izq = new JPanel(new BorderLayout(5, 5));
        izq.add(form, BorderLayout.NORTH);
        izq.add(scroll, BorderLayout.CENTER);

        // panel de dibujo del avion
        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.setBorder(BorderFactory.createTitledBorder("Mapa del Avion"));

        panel.add(izq, BorderLayout.WEST);
        panel.add(panelDibujo, BorderLayout.CENTER);

        // cargar aviones ya existentes en la lista
        for (int i = 0; i < calculo.getCantAviones(); i++) {
            Avion a = calculo.getAviones()[i];
            modeloAviones.addElement(a.getMatricula() + " - " + a.getModelo());
        }

        // eventos de botones
        btnRegistrar.addActionListener(e -> registrarAvion());
        btnVerMapa.addActionListener(e -> {
            int idx = listaAviones.getSelectedIndex();
            if (idx >= 0) {
                dibujarAvion(calculo.getAviones()[idx]);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un avion de la lista.");
            }
        });

        return panel;
    }

    // ---- PESTANIA VUELOS ----
    private JPanel crearTabVuelos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Crear Vuelo"));
        form.add(new JLabel("Codigo Vuelo:"));    form.add(txtCodVuelo);
        form.add(new JLabel("Avion:"));           form.add(cmbAviones);
        form.add(new JLabel("Origen:"));          form.add(txtOrigen);
        form.add(new JLabel("Destino:"));         form.add(txtDestino);
        form.add(new JLabel("Fecha:"));           form.add(txtFecha);
        form.add(new JLabel("Precio Base ($):")); form.add(txtPrecio);

        JButton btnCrear = new JButton("Crear Vuelo");
        form.add(new JLabel());
        form.add(btnCrear);

        // lista de vuelos registrados
        JList<String> listaVuelos = new JList<>(modeloVuelos);
        JScrollPane scroll = new JScrollPane(listaVuelos);
        scroll.setBorder(BorderFactory.createTitledBorder("Vuelos Registrados"));

        panel.add(form, BorderLayout.WEST);
        panel.add(scroll, BorderLayout.CENTER);

        // cargar aviones en el combo y vuelos existentes
        actualizarCombo();
        for (int i = 0; i < calculo.getCantVuelos(); i++) {
            Volar v = calculo.getVuelos()[i];
            modeloVuelos.addElement(v.getCodigo() + " | " + v.getOrigen() + " -> " + v.getDestino());
        }

        btnCrear.addActionListener(e -> crearVuelo());
        return panel;
    }

    // ---- METODOS DE LOGICA ----

    // registra un avion nuevo en el sistema
    private void registrarAvion() {
        try {
            String mat = txtMatricula.getText().trim().toUpperCase();
            String mod = txtModelo.getText().trim();
            if (mat.isEmpty() || mod.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete matricula y modelo.");
                return;
            }
            if (calculo.buscarAvion(mat) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un avion con esa matricula.");
                return;
            }
            int fp  = Integer.parseInt(txtFilasPrimera.getText().trim());
            int fe  = Integer.parseInt(txtFilasEjec.getText().trim());
            int fec = Integer.parseInt(txtFilasEcon.getText().trim());
            int col = Integer.parseInt(txtColumnas.getText().trim());

            Avion a = new Avion(mat, mod, fp, fe, fec, col);
            calculo.agregarAvion(a);
            modeloAviones.addElement(mat + " - " + mod);
            actualizarCombo();
            JOptionPane.showMessageDialog(this, "Avion registrado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese numeros validos en filas y columnas.");
        }
    }

    // crea un vuelo nuevo en el sistema
    private void crearVuelo() {
        try {
            String cod    = txtCodVuelo.getText().trim().toUpperCase();
            String origen = txtOrigen.getText().trim();
            String dest   = txtDestino.getText().trim();
            String fecha  = txtFecha.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            if (cod.isEmpty() || origen.isEmpty() || dest.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }
            if (calculo.buscarVuelo(cod) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un vuelo con ese codigo.");
                return;
            }
            if (cmbAviones.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un avion.");
                return;
            }
            Avion avion = calculo.getAviones()[cmbAviones.getSelectedIndex()];
            Volar v = new Volar(cod, avion, origen, dest, fecha, precio);
            calculo.agregarVuelo(v);
            modeloVuelos.addElement(cod + " | " + origen + " -> " + dest);
            JOptionPane.showMessageDialog(this, "Vuelo creado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio invalido.");
        }
    }

    // actualiza el combo de aviones con los registrados
    private void actualizarCombo() {
        cmbAviones.removeAllItems();
        for (int i = 0; i < calculo.getCantAviones(); i++) {
            Avion a = calculo.getAviones()[i];
            cmbAviones.addItem(a.getMatricula() + " - " + a.getModelo());
        }
    }

    // dibuja el mapa grafico del avion con colores por zona
    private void dibujarAvion(Avion avion) {
        panelDibujo.removeAll();
        panelDibujo.setLayout(new BorderLayout());

        // canvas personalizado que pinta los asientos
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                Asiento[][] seats = avion.getAsientos();
                int totalFilas = avion.getFilasPrimera() + avion.getFilasEjecutiva() + avion.getFilasEconomica();
                int cols  = avion.getColumnas();
                int anchoC = 38;
                int altoC  = 26;
                int inicioX = 50;
                int inicioY = 30;

                for (int i = 0; i < totalFilas; i++) {
                    // etiquetas de zona
                    if (i == 0) {
                        g.setColor(Color.DARK_GRAY);
                        g.setFont(new Font("Arial", Font.BOLD, 10));
                        g.drawString("1RA CLASE", inicioX, inicioY - 8);
                    } else if (i == avion.getFilasPrimera()) {
                        g.setColor(Color.DARK_GRAY);
                        g.setFont(new Font("Arial", Font.BOLD, 10));
                        g.drawString("EJECUTIVA", inicioX, inicioY + i * (altoC + 4) - 4);
                    } else if (i == avion.getFilasPrimera() + avion.getFilasEjecutiva()) {
                        g.setColor(Color.DARK_GRAY);
                        g.setFont(new Font("Arial", Font.BOLD, 10));
                        g.drawString("ECONOMICA", inicioX, inicioY + i * (altoC + 4) - 4);
                    }

                    // numero de fila
                    g.setColor(Color.DARK_GRAY);
                    g.setFont(new Font("Arial", Font.PLAIN, 10));
                    g.drawString(String.valueOf(i + 1), inicioX - 18, inicioY + i * (altoC + 4) + 16);

                    for (int j = 0; j < cols; j++) {
                        int x = inicioX + j * (anchoC + 4);
                        int y = inicioY + i * (altoC + 4);
                        Asiento seat = seats[i][j];

                        // color segun zona y si esta ocupado
                        if (seat.getOcupado() == 1) {
                            g.setColor(new Color(220, 80, 80));
                        } else if (seat.getZona().equals("Primera")) {
                            g.setColor(new Color(255, 215, 0));
                        } else if (seat.getZona().equals("Ejecutiva")) {
                            g.setColor(new Color(100, 180, 255));
                        } else {
                            g.setColor(new Color(160, 220, 160));
                        }

                        g.fillRect(x, y, anchoC, altoC);
                        g.setColor(Color.DARK_GRAY);
                        g.drawRect(x, y, anchoC, altoC);
                        g.setFont(new Font("Arial", Font.PLAIN, 9));
                        // muestra el codigo del asiento usando getCodigoAsiento de la clase Asiento
                        g.drawString(seat.getCodigoAsiento()
                            .replace("Fila","F").replace("Columna","C"),
                            x + 2, y + 16);
                    }
                }

                // leyenda de colores
                int lx = inicioX + cols * (anchoC + 4) + 15;
                g.setFont(new Font("Arial", Font.BOLD, 10));
                g.setColor(Color.DARK_GRAY);
                g.drawString("Leyenda:", lx, 20);
                Color[] colores = {new Color(255,215,0), new Color(100,180,255), new Color(160,220,160), new Color(220,80,80)};
                String[] nombres = {"1ra Clase", "Ejecutiva", "Economica", "Ocupado"};
                for (int k = 0; k < 4; k++) {
                    g.setColor(colores[k]);
                    g.fillRect(lx, 28 + k * 20, 14, 12);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(lx, 28 + k * 20, 14, 12);
                    g.setFont(new Font("Arial", Font.PLAIN, 9));
                    g.drawString(nombres[k], lx + 18, 38 + k * 20);
                }
            }
        };

        panelDibujo.add(canvas, BorderLayout.CENTER);
        panelDibujo.revalidate();
        panelDibujo.repaint();
    }
}
