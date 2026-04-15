/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
public class Asiento {

    //posicion en la matriz de los asientos
    private int fila;
    private int columna;

    private String zona; // "Primera", "Ejecutiva", "Economica"

    private int ocupado;

    //cedula del pasajero, null si es que esta libre
    private String pasajeroId;

    //constructor que crea el asiento libre
    public Asiento(int fila, int columna, String zona) {
        this.fila = fila;
        this.columna = columna;
        this.zona = zona;
        this.ocupado = 0;
        this.pasajeroId = null;
    }

    //Se marca el asiento como ocupado y guarda la cedula del que lo ocupa
    public void ocupar(String pasajeroId) {
        this.ocupado = 1;
        this.pasajeroId = pasajeroId;
    }

    //Libera el asiento cuando se cancela un ticket
    public void liberar() {
        this.ocupado = 0;
        this.pasajeroId = null;
    }

    public String getCodigoAsiento() {
        return "Fila" + (fila + 1) + "Columna" + (columna + 1);
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getOcupado() {
        return ocupado;
    }

    public void setOcupado(int ocupado) {
        this.ocupado = ocupado;
    }

    public String getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(String pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

}
