/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.view;

import reparto.utils.UIUtilities;
import static reparto.view.ClientesVista.clientes;
import static reparto.view.PedidosVista.pedidos;
import static reparto.view.ProductosVista.productos;
import static reparto.view.RepartidoresVista.repartidores;

/**
 *
 * @author Vinil
 */
public class Principalvista {
    
     public static void principal() {
        
        int opciones;
        do {
            UIUtilities.clearScreen();
            System.out.println("Bienvenido al Sistema de Reparto");
            System.out.println("¿Qué desea realizar?");
            System.out.println("---------------------------------");            
            System.out.println("1.- Gestión de Pedidos");
            System.out.println("2.- Gestion de Productos");
            System.out.println("3.- Gestion de Clientes");
            System.out.println("4.- Gestion de Repartidores");
            System.out.println("5.- Salir del Sistema de Reparto");
            System.out.println("---------------------------------");

            opciones = UIUtilities.getInt();
            switch (opciones) {
                case 1:
                    UIUtilities.clearScreen();
                    pedidos();
                    break;
                case 2:
                    UIUtilities.clearScreen();
                    productos();
                    break;
                case 3:
                    UIUtilities.clearScreen();
                    clientes();
                    break;
                case 4:
                    UIUtilities.clearScreen();
                    repartidores();
                case 5:
                    System.out.println("Saliendo del sistema de Reparto");

                    break;
                default:
                    System.out.println("Seleccione una opción válida");
            }

        } while (opciones != 5);
}
     
}