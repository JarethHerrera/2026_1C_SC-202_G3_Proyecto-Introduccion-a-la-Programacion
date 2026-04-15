/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
public class Volar {

    private String codigo;
    private Avion avion;
    private String origen;
    private String destino;
    private String fecha;
    private double precioBase;

    public Volar(String codigo, Avion avion, String origen, String destino, String fecha, double precioBase) {
        this.codigo = codigo;
        this.avion = avion;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }

    public double calcularPrecio(String zona) {
        double precio = precioBase;
        // Ajuste por demanda
        if (avion.getPorcentajeOcupacion() > 80) {
            precio *= 1.20;
        }
        // Ajuste por zona
        if (zona.equals("Primera")) {
            precio = precio * 2.0; // +100%
        } else if (zona.equals("Ejecutiva")) {
            precio = precio * 1.5; // +50%
        }
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}