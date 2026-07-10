package com.ejemplo.contador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        NumeroDAO numeroDAO = new NumeroDAO();

        ResumenDAO resumenDAO = new ResumenDAO();

        int cantidad = 58;

        int suma = 0;

        int mayor = Integer.MIN_VALUE;

        int menor = Integer.MAX_VALUE;

        System.out.println("INGRESE 58 NUMEROS");

        for (int i = 1; i <= cantidad; i++) {

            System.out.print("Numero " + i + ": ");

            int numero = sc.nextInt();

            suma += numero;

            if (numero > mayor) {

                mayor = numero;

            }

            if (numero < menor) {

                menor = numero;

            }

            numeroDAO.guardarNumero(new Numero(numero));

        }

        double promedio = (double) suma / cantidad;

        Resumen resumen = new Resumen(

                cantidad,

                suma,

                promedio,

                mayor,

                menor

        );

        resumenDAO.guardar(resumen);

        System.out.println();

        System.out.println("RESULTADOS");

        System.out.println("----------------");

        System.out.println("Cantidad : " + cantidad);

        System.out.println("Suma : " + suma);

        System.out.println("Promedio : " + promedio);

        System.out.println("Mayor : " + mayor);

        System.out.println("Menor : " + menor);

        numeroDAO.listar();

        resumenDAO.mostrar();
    }
}   