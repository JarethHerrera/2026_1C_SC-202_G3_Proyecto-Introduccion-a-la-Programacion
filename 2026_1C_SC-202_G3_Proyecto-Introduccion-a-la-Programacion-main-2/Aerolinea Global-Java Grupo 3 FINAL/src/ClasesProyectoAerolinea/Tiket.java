/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
public class Tiket {

    // contador compartido para generar numeros de tiquete unicos
    private static int contador = 1000;
    // numero unico del tiquete, ej: TK1001
    private String numero;
    // pasajero que compro el tiquete
    private Pasajero pasajero;
    // vuelo al que pertenece el tiquete
    private Volar vuelo;
    // asiento asignado al pasajero
    private Asiento asiento;
    // cuanto pago el pasajero despues de descuentos
    private double precioPagado;
    // preferencia de comida del pasajero
    private String menuEspecial;
    // productos comprados a bordo
    private Comida[] productosAbordo;
    // cuantos productos tiene el carrito
    private int cantProductos;
    // estado del tiquete: "Activo", "Cancelado", "Check-in"
    private String estado;

    // constructor que crea el tiquete con numero automatico
    public Tiket(Pasajero pasajero, Volar vuelo, Asiento asiento, double precioPagado) {
        this.numero = "TK" + (++contador);
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.precioPagado = precioPagado;
        this.menuEspecial = "Estándar";
        this.productosAbordo = new Comida[50];
        this.cantProductos = 0;
        this.estado = "Activo";
    }

    // agrega un producto al carrito del pasajero
    public void agregarProducto(Comida p) {
        if (cantProductos < productosAbordo.length) {
            productosAbordo[cantProductos++] = p;
        }
    }

    // suma el precio final de todos los productos del carrito
    public double getTotalProductos() {
        double total = 0;
        for (int i = 0; i < cantProductos; i++) {
            total += productosAbordo[i].getPrecioFinal();
        }
        return total;
    }

    // devuelve el numero del tiquete
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // devuelve el pasajero dueno del tiquete
    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    // devuelve el vuelo del tiquete
    public Volar getVuelo() {
        return vuelo;
    }

    public void setVuelo(Volar vuelo) {
        this.vuelo = vuelo;
    }

    // devuelve el asiento asignado
    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    // devuelve lo que pago el pasajero
    public double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(double precioPagado) {
        this.precioPagado = precioPagado;
    }

    // devuelve la preferencia de menu del pasajero
    public String getMenuEspecial() {
        return menuEspecial;
    }

    public void setMenuEspecial(String menuEspecial) {
        this.menuEspecial = menuEspecial;
    }

    // devuelve el arreglo de productos comprados
    public Comida[] getProductosAbordo() {
        return productosAbordo;
    }

    public void setProductosAbordo(Comida[] productosAbordo) {
        this.productosAbordo = productosAbordo;
    }

    // devuelve cuantos productos hay en el carrito
    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    // devuelve el estado actual del tiquete
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
