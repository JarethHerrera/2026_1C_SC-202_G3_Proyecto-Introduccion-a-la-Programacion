/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoaerolinea;
import javax.swing.JOptionPane;
/**
 *
 * @author tiffanyporrasmarin
 */
public class ProyectoAerolinea {
    //Arreglos de cada objeto con capacidad pequeña para mas facilidad
        static Avion[] aviones = new Avion[5];
        static int numAviones = 0;
        
        static Vuelo[] vuelos = new Vuelo[5];
        static int numVuelos = 0;
        
        static Pasajero[] pasajeros = new Pasajero[10];
        static int numPasajeros = 0;
        
        static Tiquete[] tiquetes 0 new Tiquete[20];
        static int numTiquetes = 0;
        
        static int contadorTiquete = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0;
        
        do{
            String entrada = JOptionPane.showInputDialog("==AEROLINEA JAVA==\n\n"+
                    "1. Modulo 1- Administracion y Flota\n"
            +"2. Modulo 2 - Venta de Tiquetes\n"
            +"3. Modulo 3 - Servicios Adicionales\n"
            +"4. Salir");
            
            if(entrada == null)
                break;
            opcion = Integer.parseInt(entrada);
            switch (opcion){
                case 1:
                    menuModulo1();
                    break;
                case 2:
                    menuModulo2();
                    break;
                case 3:
                    menuModulo3();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Hasta pronto!");
                    break;
                default: JOptionPane.showMessageDialog(null, "Opcion INVALIDA.");
            }
            
            
          
        }
        while (opcion != 4);
        
       
        
    }
    //MODULO 1
    static void menuModulo1(){
        String entrada = JOptionPane.showInputDialog("== MODULO 1: Administracion y flota ==\n\n"
        +"1. Registrar avion\n"
        +"2. Registrar vuelo\n"
        +"3. Ver aviones\n"
        +"4. Ver vuelos\n"
        +"5. Ver mapa de asientos\n"
        +"6. Volver");
        
        if (entrada == null) 
            return;
        
        switch (Integer.parseInt(entrada)){
            case 1: 
                registrarAvion();
            break;
            case 2:
                registrarVuelo();
                break;
            case 3: 
                verAviones();
                break;
            case 4:
                verVuelos();
                break;
            case 5:
                verMapa();
                break;
            
        }
    }
    
    static void registrarAvion(){
        if (numAviones >= aviones.length){
            JOptionPane.showMessageDialog(null, "Capacidad maxima de aviones alcanzada.");
            return;
        }
        String matricula = JOptionPane.showInputDialog("Matricula del avion:");
        if (matricula == null) return;
        
        String modelo = JOptionPane.showInputDialog("Modelo del avion:");
        if (modelo == null) return;
        
        aviones[numAviones] = new Avion(matricula.toUpperCase(), modelo);
        numAviones++;
        
        JOptionPane.showMessageDialog(null, "Avion registrado\n"
        + "Matricula:"+ matricula.toUpperCase()+ "\n"
        + "Modelo:" + modelo + "\n"
        + "Distribucion de asientos:\n"
        + "Filas 1-2: Primera clase (7 asientos)\n"
        + "Filas 3-4: Clase Ejecutiva (7 asientos)\n"
        + "Filas 5-7: Clase economica(11 asientos)\n"
        + "Total:" + Avion.FILAS * Avion.COLUMNAS + "asientos," + Avion.COLUMNAS 
        + "columnas por fila");
    }
    static void registrarVuelo(){
        if (numVuelos >= vuelos.length){
            JOptionPane.showMessageDialog(null, "Capacidad maxima de vuelos alcanzada");
            return;
            
            
        }
        if (numAviones == 0){
            JOptionPane.showMessageDialog(null, "Primero registre un avion");
            return;
        }
        
        //Se muestran los aviones a escoger
        String lista = "Aviones disponibles:\n";
        for (int i = 0; i < numAviones; i++) {
            lista += (i + 1) + "." + aviones[i].getMatricula() + "-" + aviones[i].getModelo() + "\n";
            
                    
            
        }
        
        String numStr = JOptionPane.showInputDialog(lista + "\nElija el numero del avion:");
        if (numStr == null) return;
        
        int idx = Integer.parseInt(numStr) - 1;
        if (idx < 0 || idx >= numAviones){
            JOptionPane.showMessageDialog(null, "Numero invalido");
            return;
        }
        
        String codigo = JOptionPane.showInputDialog("Codigo de vuelo (ej: GJ-101):");
        if (codigo == null)
            return;
        String
    }
}
