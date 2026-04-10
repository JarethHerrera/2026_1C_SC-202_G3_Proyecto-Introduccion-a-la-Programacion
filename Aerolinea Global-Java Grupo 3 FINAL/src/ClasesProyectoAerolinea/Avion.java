/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */

public class Avion {

    String matricula;
    String modelo;
    int filasPrimera;
    int filasEjecutiva;
    int filasEconomica;
    int columnas;
    
    Asiento[][] asientos;

    public Avion(String matricula, String modelo, int filasPrimera, int filasEjecutiva, int filasEconomica, int columnas) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.filasPrimera = filasPrimera;
        this.filasEjecutiva = filasEjecutiva;
        this.filasEconomica = filasEconomica;
        this.columnas = columnas;
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        int totaldeFilas = filasPrimera + filasEjecutiva + filasEconomica;
        asientos = new Asiento[totaldeFilas][columnas];
        for (int i = 0; i < totaldeFilas; i++) {
            for (int j = 0; j < columnas; j++) {
                String zona;
                if (i < filasPrimera) {
                    zona = "Primera";
                } else if (i < filasPrimera + filasEjecutiva) {
                    zona = "Ejecutiva";
                } else {
                    zona = "Economica";
                }
                asientos[i][j] = new Asiento(i, j, zona); //DUDA TIFF
            }
        }
    }

    public int Todoslosasietos() {
        return (filasPrimera + filasEjecutiva + filasEconomica) * columnas;
    }

    public int AsientoOcupados() {
        int contadordeunsolouso = 0;
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (asientos[i][j].getOcupado() == 1) {
                    contadordeunsolouso++;
                }
            }
        }
    return contadordeunsolouso;
}

    public double getPorcentajeOcupacion() {
        return (double) AsientoOcupados() / Todoslosasietos() * 100;
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getFilasPrimera() {
        return filasPrimera;
    }

    public void setFilasPrimera(int filasPrimera) {
        this.filasPrimera = filasPrimera;
    }

    public int getFilasEjecutiva() {
        return filasEjecutiva;
    }

    public void setFilasEjecutiva(int filasEjecutiva) {
        this.filasEjecutiva = filasEjecutiva;
    }

    public int getFilasEconomica() {
        return filasEconomica;
    }

    public void setFilasEconomica(int filasEconomica) {
        this.filasEconomica = filasEconomica;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Asiento[][] getAsientos() {
        return asientos;
    }

    public void setAsientos(Asiento[][] asientos) {
        this.asientos = asientos;
    }


}
