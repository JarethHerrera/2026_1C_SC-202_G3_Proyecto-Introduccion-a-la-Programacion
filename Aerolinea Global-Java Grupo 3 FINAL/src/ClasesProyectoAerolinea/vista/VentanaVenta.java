/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea.vista;

import ClasesProyectoAerolinea.*;
import javax.swing.*;
import java.awt.*;

/**
 * Modulo 2 - Muestra mapa de asientos y permite comprar tiquetes
 *
 * @author eltro
 */
public class VentanaVenta extends JFrame {

    // acceso unico a los datos del sistema
    Calculo calculo = Calculo.HayEstanciasiono();

    // vuelo y asiento que el usuario selecciono
    Volar vueloSeleccionado = null;
    Asiento asientoSeleccionado = null;

    // matriz de botones que representa el mapa de asientos
    JButton[][] botonesAsientos;

    // componentes de la pantalla
    JComboBox<String> cmbVuelos = new JComboBox<>();
    JLabel lblInfoVuelo = new JLabel("Seleccione un vuelo y cargue el mapa");
    JLabel lblAsiento = new JLabel("Ninguno");
    JLabel lblPrecio = new JLabel("Precio: -");

    JTextField txtId = new JTextField();
    JTextField txtNombre = new JTextField();
    JTextField txtApellido = new JTextField();
    JTextField txtEmail = new JTextField();
    JComboBox<String> cmbNivel = new JComboBox<>(new String[]{"Regular", "Oro", "Platino"});

    JPanel panelMapa = new JPanel();
    JButton btnComprar = new JButton("COMPRAR TIQUETE");

    public VentanaVenta() {
        setTitle("Modulo 2 - Venta de Tiquetes");
        setSize(900, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // seleccion de vuelo 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Seleccionar Vuelo"));
        JButton btnCargar = new JButton("Cargar Mapa");

        // cargar los vuelos registrados en el combo
        for (int i = 0; i < calculo.getCantVuelos(); i++) {
            Volar v = calculo.getVuelos()[i];
            cmbVuelos.addItem(v.getCodigo() + " | " + v.getOrigen() + " -> " + v.getDestino());
        }
        topPanel.add(new JLabel("Vuelo:"));
        topPanel.add(cmbVuelos);
        topPanel.add(btnCargar);
        topPanel.add(lblInfoVuelo);

        // mapa de asientos 
        panelMapa.setBackground(Color.WHITE);
        panelMapa.setBorder(BorderFactory.createTitledBorder("Mapa de Asientos - Haga click en un asiento libre"));
        JScrollPane scrollMapa = new JScrollPane(panelMapa);
        scrollMapa.setPreferredSize(new Dimension(540, 440));

        // datos del pasajero 
        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));

        // info del asiento seleccionado
        JPanel infoAsiento = new JPanel(new GridLayout(2, 1));
        infoAsiento.setBorder(BorderFactory.createTitledBorder("Asiento Seleccionado"));
        lblAsiento.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsiento.setFont(new Font("Arial", Font.BOLD, 13));
        lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(new Color(0, 120, 0));
        infoAsiento.add(lblAsiento);
        infoAsiento.add(lblPrecio);

        // formulario del pasajero
        JPanel formPas = new JPanel(new GridLayout(7, 2, 5, 5));
        formPas.setBorder(BorderFactory.createTitledBorder("Datos del Pasajero"));
        JButton btnBuscar = new JButton("Buscar");
        formPas.add(new JLabel("ID/Cedula:"));
        formPas.add(txtId);
        formPas.add(new JLabel(""));
        formPas.add(btnBuscar);
        formPas.add(new JLabel("Nombre:"));
        formPas.add(txtNombre);
        formPas.add(new JLabel("Apellido:"));
        formPas.add(txtApellido);
        formPas.add(new JLabel("Email:"));
        formPas.add(txtEmail);
        formPas.add(new JLabel("Nivel Socio:"));
        formPas.add(cmbNivel);

        // leyenda de colores
        JPanel leyenda = new JPanel(new GridLayout(4, 1, 2, 2));
        leyenda.setBorder(BorderFactory.createTitledBorder("Leyenda"));
        String[] nombres = {"1ra Clase (+100%)", "Ejecutiva (+50%)", "Economica", "Ocupado"};
        Color[] colores = {new Color(255, 215, 0), new Color(100, 180, 255), new Color(160, 220, 160), new Color(220, 80, 80)};
        for (int i = 0; i < 4; i++) {
            JLabel lbl = new JLabel("  " + nombres[i]);
            lbl.setOpaque(true);
            lbl.setBackground(colores[i]);
            lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            leyenda.add(lbl);
        }

        // boton de compra deshabilitado hasta seleccionar asiento
        btnComprar.setBackground(new Color(0, 150, 50));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 13));
        btnComprar.setEnabled(false);

        JPanel sur = new JPanel(new BorderLayout(5, 5));
        sur.add(leyenda, BorderLayout.CENTER);
        sur.add(btnComprar, BorderLayout.SOUTH);

        panelDerecho.add(infoAsiento, BorderLayout.NORTH);
        panelDerecho.add(formPas, BorderLayout.CENTER);
        panelDerecho.add(sur, BorderLayout.SOUTH);

        main.add(topPanel, BorderLayout.NORTH);
        main.add(scrollMapa, BorderLayout.CENTER);
        main.add(panelDerecho, BorderLayout.EAST);
        setContentPane(main);

        // eventos
        btnCargar.addActionListener(e -> cargarMapa());
        btnBuscar.addActionListener(e -> buscarPasajero());
        btnComprar.addActionListener(e -> comprarTiquete());
    }

    // carga el mapa del vuelo seleccionado
    private void cargarMapa() {
        if (calculo.getCantVuelos() == 0) {
            JOptionPane.showMessageDialog(this, "No hay vuelos registrados. Vaya al Modulo 1.");
            return;
        }
        int idx = cmbVuelos.getSelectedIndex();
        if (idx < 0) {
            return;
        }

        vueloSeleccionado = calculo.getVuelos()[idx];
        asientoSeleccionado = null;
        lblAsiento.setText("Ninguno");
        lblPrecio.setText("Precio: -");
        btnComprar.setEnabled(false);

        // mostrar porcentaje de ocupacion
        double ocp = vueloSeleccionado.getAvion().getPorcentajeOcupacion();
        lblInfoVuelo.setText(String.format("Ocupacion: %.0f%%%s", ocp,
                ocp > 80 ? "  (+20% por demanda)" : ""));

        dibujarMapa(vueloSeleccionado.getAvion());
    }

    // dibuja el mapa de botones del avion
    private void dibujarMapa(Avion avion) {
        panelMapa.removeAll();

        int totalFilas = avion.getFilasPrimera() + avion.getFilasEjecutiva() + avion.getFilasEconomica();
        int cols = avion.getColumnas();
        botonesAsientos = new JButton[totalFilas][cols];

        JPanel grid = new JPanel(new GridBagLayout());
        grid.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        // encabezado de columnas A B C D...
        gbc.gridy = 0;
        gbc.gridx = 0;
        grid.add(new JLabel(""), gbc);
        for (int j = 0; j < cols; j++) {
            gbc.gridx = j + 1;
            JLabel lbl = new JLabel(String.valueOf((char) ('A' + j)), SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 11));
            grid.add(lbl, gbc);
        }

        int filaVisual = 1;
        for (int i = 0; i < totalFilas; i++) {
            // separador entre zonas
            if (i == avion.getFilasPrimera() || i == avion.getFilasPrimera() + avion.getFilasEjecutiva()) {
                gbc.gridy = filaVisual;
                gbc.gridx = 0;
                gbc.gridwidth = cols + 1;
                String txt = (i == avion.getFilasPrimera()) ? "--- EJECUTIVA ---" : "--- ECONOMICA ---";
                JLabel sep = new JLabel(txt, SwingConstants.CENTER);
                sep.setFont(new Font("Arial", Font.BOLD, 10));
                grid.add(sep, gbc);
                gbc.gridwidth = 1;
                filaVisual++;
            }

            // numero de fila
            gbc.gridy = filaVisual;
            gbc.gridx = 0;
            grid.add(new JLabel(String.valueOf(i + 1)), gbc);

            // botones de asientos
            for (int j = 0; j < cols; j++) {
                gbc.gridx = j + 1;
                Asiento seat = avion.getAsientos()[i][j];
                final int fi = i, co = j;

                JButton btn = new JButton(String.valueOf(i + 1) + (char) ('A' + j));
                btn.setPreferredSize(new Dimension(40, 28));
                btn.setFont(new Font("Arial", Font.PLAIN, 9));
                btn.setMargin(new Insets(1, 1, 1, 1));
                colorearBoton(btn, seat);
                botonesAsientos[i][j] = btn;

                // solo los asientos libres tienen accion
                if (seat.getOcupado() == 0) {
                    btn.addActionListener(e -> seleccionarAsiento(seat, fi, co));
                }
                grid.add(btn, gbc);
            }
            filaVisual++;
        }

        panelMapa.setLayout(new BorderLayout());
        panelMapa.add(new JScrollPane(grid), BorderLayout.CENTER);
        panelMapa.revalidate();
        panelMapa.repaint();
    }

    // pinta el boton segun zona y estado del asiento
    private void colorearBoton(JButton btn, Asiento seat) {
        if (seat.getOcupado() == 1) {
            btn.setBackground(new Color(220, 80, 80));
            btn.setEnabled(false);
        } else if (seat.getZona().equals("Primera")) {
            btn.setBackground(new Color(255, 215, 0));
        } else if (seat.getZona().equals("Ejecutiva")) {
            btn.setBackground(new Color(100, 180, 255));
        } else {
            btn.setBackground(new Color(160, 220, 160));
        }
    }

    // cuando el usuario hace click en un asiento libre
    private void seleccionarAsiento(Asiento seat, int fi, int co) {
        // deseleccionar el anterior si habia
        if (asientoSeleccionado != null) {
            colorearBoton(
                    botonesAsientos[asientoSeleccionado.getFila()][asientoSeleccionado.getColumna()],
                    asientoSeleccionado
            );
        }
        asientoSeleccionado = seat;
        // resaltar el seleccionado en naranja
        botonesAsientos[fi][co].setBackground(new Color(255, 140, 0));

        // calcular precio: usa calcularPrecio de Volar y aplicarDescuento de Pasajero
        double precio = vueloSeleccionado.calcularPrecio(seat.getZona());
        Pasajero p = calculo.buscarPasajero(txtId.getText().trim());
        if (p != null) {
            precio = p.aplicarDescuento(precio);
        }

        lblAsiento.setText("Asiento: " + (fi + 1) + (char) ('A' + co) + " | " + seat.getZona());
        lblPrecio.setText(String.format("Precio: $%.2f", precio));
        btnComprar.setEnabled(true);
    }

    // busca un pasajero ya registrado y rellena el formulario
    private void buscarPasajero() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            return;
        }
        Pasajero p = calculo.buscarPasajero(id);
        if (p != null) {
            txtNombre.setText(p.getNombre());
            txtApellido.setText(p.getApellido());
            txtEmail.setText(p.getEmail());
            cmbNivel.setSelectedItem(p.getNivelSocio());
            JOptionPane.showMessageDialog(this, "Pasajero encontrado: " + p.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "No encontrado. Complete los datos para registrarlo.");
        }
    }

    // confirma la compra, crea el tiquete y marca el asiento como ocupado
    private void comprarTiquete() {
        if (vueloSeleccionado == null || asientoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vuelo y un asiento.");
            return;
        }
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String email = txtEmail.getText().trim();
        String nivel = (String) cmbNivel.getSelectedItem();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los datos del pasajero.");
            return;
        }

        // buscar o registrar pasajero
        Pasajero pasajero = calculo.buscarPasajero(id);
        if (pasajero == null) {
            pasajero = new Pasajero(id, nombre, apellido, email, nivel);
            calculo.agregarPasajero(pasajero);
        }

        // calcular precio final con descuento de lealtad
        double precio = vueloSeleccionado.calcularPrecio(asientoSeleccionado.getZona());
        precio = pasajero.aplicarDescuento(precio);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Confirmar compra:\n\n"
                + "Vuelo   : " + vueloSeleccionado.getCodigo() + "\n"
                + "Asiento : " + (asientoSeleccionado.getFila() + 1) + (char) ('A' + asientoSeleccionado.getColumna())
                + " (" + asientoSeleccionado.getZona() + ")\n"
                + "Pasajero: " + nombre + " " + apellido + "\n"
                + "Nivel   : " + nivel + "\n"
                + String.format("Precio  : $%.2f", precio),
                "Confirmar Compra", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // marcar asiento como ocupado usando ocupar() de Asiento
            asientoSeleccionado.ocupar(id);
            // crear el tiquete
            Tiket t = new Tiket(pasajero, vueloSeleccionado, asientoSeleccionado, precio);
            calculo.agregarTiquete(t);
            JOptionPane.showMessageDialog(this,
                    "Tiquete comprado!\nNumero: " + t.getNumero()
                    + "\nGuardelo para el Modulo 3.");
            cargarMapa();

        }
    }
}
