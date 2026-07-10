package com.ejemplo.contador;

public class Resumen {

    private int cantidad;
    private int suma;
    private double promedio;
    private int mayor;
    private int menor;

    public Resumen(
            int cantidad,
            int suma,
            double promedio,
            int mayor,
            int menor) {

        this.cantidad = cantidad;
        this.suma = suma;
        this.promedio = promedio;
        this.mayor = mayor;
        this.menor = menor;

    }

    public int getCantidad() {
        return cantidad;
    }

    public int getSuma() {
        return suma;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getMayor() {
        return mayor;
    }

    public int getMenor() {
        return menor;
    }
}