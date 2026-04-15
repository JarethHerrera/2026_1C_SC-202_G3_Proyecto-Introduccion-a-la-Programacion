/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
public class Comida {

    //nombre del producto
    String nombre;

    String categoria; //HAY QUE AGREGAR MAS OPCIONES JAJAJA PERO NO SE ME OCURRIO NADA // "Sinimpuesto", "Snack", "Bebida", "Electronica" 
    double precio;

    //IVA fijo que aplica solo a snacks y bebidas
    double IVA = 0.13;

    public Comida(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    //Devuelve 1 si esta libre de impuestos o si no 0
    public int TipoCategoria() {
        if (categoria.equals("DutyFree") || categoria.equals("Electronica")) {
            return 1;
        } else {
            return 0;
        }
    }

    //aplica el impuesto si es que debe
    public double getPrecioFinal() {
        if (TipoCategoria() == 1) {
            return precio;
        } else {
            return precio * (precio * IVA);
        }
    }

    //Devuelve el monto del impuesto
    public double Impuesto() {
        if (TipoCategoria() == 1) {
            return 0;
        } else {
            return precio * IVA;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

}
