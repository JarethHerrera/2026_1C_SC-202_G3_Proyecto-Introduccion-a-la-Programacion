/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyectoAerolinea;

/**
 *
 * @author eltro
 */
//Aqui se maneja toda la logica principal del sistema.
public class Calculo {

    private static Calculo instancia;

    Avion[] aviones;
    int cantAviones; //cuenta aviones registrados

    Volar[] vuelos;
    int cantVuelos; //cuantos vuelos hay registrados

    Pasajero[] pasajeros;
    int cantPasajeros;

    Tiket[] tiquetes;
    int cantTiquetes; //tikets comprados

    Comida[] catalogo;
    int cantProductos; //productos disponibles

    //variables para llevar el control de ingresos
    double ingresosTiquetes;
    double ingresosAbordo;
    double ingresosPenalizacionEquipaje;
    double ingresosPenalizacionCancelacion;

    private Calculo() {
        aviones = new Avion[100]; //maximo 100 aviones
        vuelos = new Volar[200]; // maximo 200 vuelos
        pasajeros = new Pasajero[500]; //maximo 500 pasajeros
        tiquetes = new Tiket[500]; //maximo 500 tikets
        catalogo = new Comida[50]; //maximo 50 prodcutos en el catalogo
        inicializarCatalogo(); 
    }

    //devuelve la unica instancia del calculo
    public static Calculo HayEstanciasiono() {
        if (instancia == null) {
            instancia = new Calculo();
        }
        return instancia;
    }

    //Organicé los productos por categoria
    private void inicializarCatalogo() {
        catalogo[cantProductos++] = new Comida("Perfume Chanel", "DutyFree", 85.00);
        catalogo[cantProductos++] = new Comida("Perfume Dior", "DutyFree", 95.00);
        catalogo[cantProductos++] = new Comida("Audífonos Bluetooth", "Electronica", 120.00);
        catalogo[cantProductos++] = new Comida("Cargador Universal", "Electronica", 35.00);
        catalogo[cantProductos++] = new Comida("Agua mineral", "Bebida", 3.50);
        catalogo[cantProductos++] = new Comida("Jugo de naranja", "Bebida", 4.00);
        catalogo[cantProductos++] = new Comida("Vino tinto", "Bebida", 8.00);
        catalogo[cantProductos++] = new Comida("Papas fritas", "Snack", 2.50);
        catalogo[cantProductos++] = new Comida("Chocolate", "Snack", 3.00);
        catalogo[cantProductos++] = new Comida("Maní salado", "Snack", 2.00);
    }


    //Aviones
    public void agregarAvion(Avion a) {
        aviones[cantAviones++] = a;
    }

    public Avion[] getAviones() {
        return aviones;
    }

    public int getCantAviones() {
        return cantAviones;
    }

    public Avion buscarAvion(String matricula) {
        for (int i = 0; i < cantAviones; i++) {
            if (aviones[i].getMatricula().equals(matricula)) {
                return aviones[i];
            }
        }
        return null;
    }


    public void agregarVuelo(Volar v) {
        vuelos[cantVuelos++] = v;
    }

    public Volar[] getVuelos() {
        return vuelos;
    }

    public int getCantVuelos() {
        return cantVuelos;
    }

    public Volar buscarVuelo(String codigo) {
        for (int i = 0; i < cantVuelos; i++) {
            if (vuelos[i].getCodigo().equals(codigo)) {
                return vuelos[i];
            }
        }
        return null;
    }

    // Pasajeros 
    public void agregarPasajero(Pasajero p) {
        pasajeros[cantPasajeros++] = p;
    }

    public Pasajero[] getPasajeros() {
        return pasajeros;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public Pasajero buscarPasajero(String id) {
        for (int i = 0; i < cantPasajeros; i++) {
            if (pasajeros[i].getId().equals(id)) {
                return pasajeros[i];
            }
        }
        return null;
    }

    // Tiquetes
    public void agregarTiquete(Tiket t) {
        tiquetes[cantTiquetes++] = t;
        ingresosTiquetes += t.getPrecioPagado();
    }

    public Tiket[] getTiquetes() {
        return tiquetes;
    }

    public int getCantTiquetes() {
        return cantTiquetes;
    }

    public Tiket buscarTiquete(String numero) {
        for (int i = 0; i < cantTiquetes; i++) {
            if (tiquetes[i].getNumero().equals(numero)) {
                return tiquetes[i];
            }
        }
        return null;
    }

    public Tiket buscarTiquetePorPasajero(String idPasajero) {
        for (int i = 0; i < cantTiquetes; i++) {
            if (tiquetes[i].getPasajero().getId().equals(idPasajero)
                    && tiquetes[i].getEstado().equals("Activo")) {
                return tiquetes[i];
            }
        }
        return null;
    }

    //catalogo
    public Comida[] getCatalogo() {
        return catalogo;
    }

    public int getCantProductos() {
        return cantProductos;
    }

    public void sumarIngresosAbordo(double monto) {
        ingresosAbordo += monto;
    }

    public void sumarPenalizacionEquipaje(double monto) {
        ingresosPenalizacionEquipaje += monto;
    }

    public void sumarPenalizacionCancelacion(double monto) {
        ingresosPenalizacionCancelacion += monto;
    }

    public double getIngresosTiquetes() {
        return ingresosTiquetes;
    }

    public double getIngresosAbordo() {
        return ingresosAbordo;
    }

    public double getIngresosPenalizacionEquipaje() {
        return ingresosPenalizacionEquipaje;
    }

    public double getIngresosPenalizacionCancelacion() {
        return ingresosPenalizacionCancelacion;
    }

    public double getTotalIngresos() {
        return ingresosTiquetes + ingresosAbordo + ingresosPenalizacionEquipaje + ingresosPenalizacionCancelacion;
    }
}
