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

    int fila;
    int columna;
    String zona; // "Primera", "Ejecutiva", "Economica"
    int ocupado;
    String pasajeroId;

    public Asiento(int fila, int columna, String zona) {
        this.fila = fila;
        this.columna = columna;
        this.zona = zona;
        this.ocupado = 0;
        this.pasajeroId = null;
    }

    public void ocupar(String pasajeroId) {
        this.ocupado = 1;
        this.pasajeroId = pasajeroId;
    }

    public void liberar() {
        this.ocupado = 0;
        this.pasajeroId = null;
    }

    public String CodigoAsi() {
        char col = (char) ('A' + columna);
        return (fila + 1) + "" + col;
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
