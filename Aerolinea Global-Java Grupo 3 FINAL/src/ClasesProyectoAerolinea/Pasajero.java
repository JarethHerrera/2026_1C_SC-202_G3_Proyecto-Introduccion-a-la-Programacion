/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
public class Pasajero {

    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String nivelSocio; // "Platino", "Oro", "Regular"

    public Pasajero(String id, String nombre, String apellido, String email, String nivelSocio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nivelSocio = nivelSocio;
    }

    //Platino= 10%, Oro= 5%, Regular sin descuento
    public double aplicarDescuento(double precio) { //Tiff use .equals, es lo mismo que == pero este si funciona con el programa
        if (nivelSocio.equals("Platino")) {
            return precio * 0.90;
        } else if (nivelSocio.equals("Oro")) {
            return precio * 0.95;
        } else {
            return precio;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivelSocio() {
        return nivelSocio;
    }

    public void setNivelSocio(String nivelSocio) {
        this.nivelSocio = nivelSocio;

    }

}
