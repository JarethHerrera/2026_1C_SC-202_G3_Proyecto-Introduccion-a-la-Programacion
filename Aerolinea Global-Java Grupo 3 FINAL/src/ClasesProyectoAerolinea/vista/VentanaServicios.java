/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea.vista;

import ClasesProyectoAerolinea.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Modulo 3 - Permite agregar menu especial y productos a bordo a un tiquete
 *
 * @author eltro
 */
public class VentanaServicios extends JFrame {

    // acceso unico a los datos del sistema
    Calculo calculo = Calculo.HayEstanciasiono();

    // tiquete que se esta editando
    Tiket tiketActual = null;

    // componentes de pantalla
    JTextField txtBuscar = new JTextField(12);
    JLabel lblInfo = new JLabel("Ingrese el numero de tiquete");

    JComboBox<String> cmbMenu = new JComboBox<>(new String[]{"Estandar", "Vegetariano", "Kosher", "Sin Gluten"});
    JButton btnGuardarMenu = new JButton("Guardar Menu");

    JList<String> listaCatalogo;
    DefaultTableModel modeloCarrito;

    JLabel lblSubtotal = new JLabel("$0.00");
    JLabel lblImpuestos = new JLabel("$0.00");
    JLabel lblTotal = new JLabel("$0.00");

    public VentanaServicios() {
        setTitle("Modulo 3 - Servicios Adicionales");
        setSize(820, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Buscar tiket
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busqueda.setBorder(BorderFactory.createTitledBorder("Buscar Tiquete"));
        JButton btnBuscar = new JButton("Buscar");
        busqueda.add(new JLabel("N Tiquete:"));
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);
        busqueda.add(lblInfo);

        // dos pestanas: menu especial y compras
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Menu Especial", crearTabMenu());
        tabs.addTab("Compras a Bordo", crearTabCarrito());

        main.add(busqueda, BorderLayout.NORTH);
        main.add(tabs, BorderLayout.CENTER);
        setContentPane(main);

        // deshabilitar hasta buscar tiquete
        cmbMenu.setEnabled(false);
        btnGuardarMenu.setEnabled(false);

        btnBuscar.addActionListener(e -> buscarTiket());
        btnGuardarMenu.addActionListener(e -> guardarMenu());
    }

    // Elegir menu, vegano o normal
    private JPanel crearTabMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Tipo de Menu:"), gbc);
        gbc.gridx = 1;
        panel.add(cmbMenu, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(btnGuardarMenu, gbc);

        return panel;
    }

    // Comprar
    private JPanel crearTabCarrito() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // catalogo de productos
        DefaultListModel<String> modeloCat = new DefaultListModel<>();
        for (int i = 0; i < calculo.getCantProductos(); i++) {
            Comida c = calculo.getCatalogo()[i];
            // usa getPrecioFinal() y TipoCategoria() de Comida
            modeloCat.addElement(String.format("%-22s  %-10s  $%.2f%s",
                    c.getNombre(), c.getCategoria(), c.getPrecioFinal(),
                    c.TipoCategoria() == 1 ? "" : " (+ IVA incluido)"));
        }
        listaCatalogo = new JList<>(modeloCat);
        listaCatalogo.setFont(new Font("ARIAL", Font.PLAIN, 11));
        JScrollPane scrollCat = new JScrollPane(listaCatalogo);
        scrollCat.setBorder(BorderFactory.createTitledBorder("Catalogo de Productos"));
        scrollCat.setPreferredSize(new Dimension(0, 140));

        JButton btnAgregar = new JButton("Agregar al Carrito");
        btnAgregar.setBackground(new Color(0, 120, 215));
        btnAgregar.setForeground(Color.WHITE);

        // tabla del carrito
        String[] columnas = {"Producto", "Categoria", "Precio Base", "IVA", "Total"};
        modeloCarrito = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        JTable tabla = new JTable(modeloCarrito);
        tabla.setFont(new Font("Arial", Font.PLAIN, 11));
        JScrollPane scrollCarrito = new JScrollPane(tabla);
        scrollCarrito.setBorder(BorderFactory.createTitledBorder("Carrito"));

        // totales
        JPanel totales = new JPanel(new GridLayout(3, 2, 5, 3));
        totales.setBorder(BorderFactory.createTitledBorder("Resumen"));
        totales.add(new JLabel("Subtotal:"));
        totales.add(lblSubtotal);
        totales.add(new JLabel("IVA (13%):"));
        totales.add(lblImpuestos);
        totales.add(new JLabel("TOTAL:"));
        totales.add(lblTotal);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotal.setForeground(new Color(0, 120, 0));

        JButton btnFactura = new JButton("Generar Factura");
        btnFactura.setBackground(new Color(200, 100, 0));
        btnFactura.setForeground(Color.WHITE);

        JPanel sur = new JPanel(new BorderLayout(5, 5));
        sur.add(totales, BorderLayout.CENTER);
        sur.add(btnFactura, BorderLayout.SOUTH);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(btnAgregar);

        JPanel medio = new JPanel(new BorderLayout());
        medio.add(btnPanel, BorderLayout.NORTH);
        medio.add(scrollCarrito, BorderLayout.CENTER);
        medio.add(sur, BorderLayout.SOUTH);

        panel.add(scrollCat, BorderLayout.NORTH);
        panel.add(medio, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarAlCarrito());
        btnFactura.addActionListener(e -> generarFactura());

        return panel;
    }

    // busca el tiquete ingresado y habilita los servicios
    private void buscarTiket() {
        String num = txtBuscar.getText().trim().toUpperCase();
        // usa buscarTiquete() de Calculo
        tiketActual = calculo.buscarTiquete(num);
        if (tiketActual == null) {
            lblInfo.setText("Tiquete no encontrado.");
            lblInfo.setForeground(Color.RED);
            cmbMenu.setEnabled(false);
            btnGuardarMenu.setEnabled(false);
        } else {
            // usa getters de Tiket, Pasajero, Volar y Asiento
            lblInfo.setText("OK: " + tiketActual.getPasajero().getNombre()
                    + " " + tiketActual.getPasajero().getApellido()
                    + " | Vuelo: " + tiketActual.getVuelo().getCodigo()
                    + " | Asiento: " + tiketActual.getAsiento().getCodigoAsiento());
            lblInfo.setForeground(new Color(0, 100, 0));
            cmbMenu.setEnabled(true);
            btnGuardarMenu.setEnabled(true);
            // usa getMenuEspecial() de Tiket
            cmbMenu.setSelectedItem(tiketActual.getMenuEspecial());
        }
    }

    // guarda la preferencia de menu en el tiquete usando setMenuEspecial()
    private void guardarMenu() {
        if (tiketActual == null) {
            return;
        }
        String menu = (String) cmbMenu.getSelectedItem();
        tiketActual.setMenuEspecial(menu);
        JOptionPane.showMessageDialog(this, "Menu guardado: " + menu);
    }

    // agrega el producto seleccionado al carrito del tiquete
    private void agregarAlCarrito() {
        if (tiketActual == null) {
            JOptionPane.showMessageDialog(this, "Primero busque un tiquete valido.");
            return;
        }
        int idx = listaCatalogo.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto del catalogo.");
            return;
        }
        Comida c = calculo.getCatalogo()[idx];
        // usa agregarProducto() de Tiket
        tiketActual.agregarProducto(c);

        // agrega fila a la tabla usando getPrecio(), Impuesto() y getPrecioFinal() de Comida
        modeloCarrito.addRow(new Object[]{
            c.getNombre(),
            c.getCategoria(),
            String.format("$%.2f", c.getPrecio()),
            String.format("$%.2f", c.Impuesto()),
            String.format("$%.2f", c.getPrecioFinal())
        });
        actualizarTotales();
    }

    // recalcula y muestra los totales del carrito
    private void actualizarTotales() {
        if (tiketActual == null) {
            return;
        }
        double subtotal = 0, iva = 0, total = 0;
        for (int i = 0; i < tiketActual.getCantProductos(); i++) {
            Comida c = tiketActual.getProductosAbordo()[i];
            subtotal += c.getPrecio();
            iva += c.Impuesto();
            total += c.getPrecioFinal();
        }
        lblSubtotal.setText(String.format("$%.2f", subtotal));
        lblImpuestos.setText(String.format("$%.2f", iva));
        lblTotal.setText(String.format("$%.2f", total));
    }

    // genera y muestra la factura a bordo en pantalla
    private void generarFactura() {
        if (tiketActual == null || tiketActual.getCantProductos() == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos en el carrito.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("        FACTURA A BORDO\n");
        sb.append("\n");
        sb.append("Tiquete : ").append(tiketActual.getNumero()).append("\n");
        sb.append("Pasajero: ")
                .append(tiketActual.getPasajero().getNombre()).append(" ")
                .append(tiketActual.getPasajero().getApellido()).append("\n");
        sb.append("Vuelo   : ").append(tiketActual.getVuelo().getCodigo()).append("\n");
        sb.append("Asiento : ").append(tiketActual.getAsiento().getCodigoAsiento()).append("\n");
        sb.append("\n");
        sb.append(String.format("%-20s %6s %6s %7s\n", "Producto", "Precio", "IVA", "Total"));
        sb.append("\n");

        double subtotal = 0, iva = 0, total = 0;
        for (int i = 0; i < tiketActual.getCantProductos(); i++) {
            Comida c = tiketActual.getProductosAbordo()[i];
            sb.append(String.format("%-20s %6.2f %6.2f %7.2f\n",
                    c.getNombre(), c.getPrecio(), c.Impuesto(), c.getPrecioFinal()));
            subtotal += c.getPrecio();
            iva += c.Impuesto();
            total += c.getPrecioFinal();
        }
        sb.append("\n");
        sb.append(String.format("%-20s %6.2f %6.2f %7.2f\n", "TOTAL", subtotal, iva, total));
        sb.append("\n");
        sb.append("Gracias por volar con Aerolinea Java mae!\n");

        // registrar los ingresos en Calculo
        calculo.sumarIngresosAbordo(total);

        JTextArea area = new JTextArea(sb.toString());
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setEditable(false);
        JOptionPane.showMessageDialog(this,
                new JScrollPane(area), "Factura a Bordo", JOptionPane.PLAIN_MESSAGE);
    }
}
