/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.view;

import static reparto.controller.AppController.borrarCliente;
import static reparto.controller.AppController.editarCliente;
import static reparto.controller.AppController.listarCliente;
import static reparto.controller.AppController.registrarCliente;
import reparto.utils.UIUtilities;
import static reparto.utils.UIUtilities.espera;

/**
 *
 * @author Vinil
 */
public class ClientesVista {
    
    public static void clientes() {

        int opcioncliente;
        do {
            System.out.println("Bienvenido al menu Cliente");
            System.out.println("Seleccione una opción:");
            System.out.println("----------------------------");
            System.out.println("1.- Registrar Cliente");
            System.out.println("2.- Editar Cliente");
            System.out.println("3.- Eliminar Cliente");
            System.out.println("4.- Listado de Clientes");
            System.out.println("5.- Volver al menú principal");
            System.out.println("----------------------------");

            opcioncliente = UIUtilities.getInt();

            switch (opcioncliente) {

                case 1:
                    UIUtilities.clearScreen();
                    registrarCliente();
                    espera();
                    break;

                case 2:
                    UIUtilities.clearScreen();
                    editarCliente();
                    espera();
                    break;

                case 3:
                    UIUtilities.clearScreen();
                    borrarCliente();
                    espera();
                    break;

                case 4:
                    UIUtilities.clearScreen();
                    listarCliente();
                    espera();
                    break;
                case 5:
                    UIUtilities.clearScreen();
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
            }
        } while (opcioncliente != 5);
    }
    
}
