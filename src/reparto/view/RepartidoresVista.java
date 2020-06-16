/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.view;

import static reparto.controller.AppController.borrarRepartidor;
import static reparto.controller.AppController.editarRepartidor;
import static reparto.controller.AppController.listarRepartidor;
import static reparto.controller.AppController.registrarRepartidor;
import reparto.utils.UIUtilities;
import static reparto.utils.UIUtilities.espera;

/**
 *
 * @author Vinil
 */
public class RepartidoresVista {
    
        public static void repartidores() {
        int opcionrepartidor;
        do {
            System.out.println("Bienvenido al menu Repartidores");
            System.out.println("Seleccione una opción:");
            System.out.println("-------------------------------");
            System.out.println("1.- Nuevo Repartidor");
            System.out.println("2.- Editar Repartidor");
            System.out.println("3.- Eliminar Repartidor");
            System.out.println("4.- Listar Repartidores");
            System.out.println("5.- Volver al menú principal");
            System.out.println("-------------------------------");
            
            opcionrepartidor = UIUtilities.getInt();

            switch (opcionrepartidor) {

                case 1:
                    UIUtilities.clearScreen();
                    registrarRepartidor();
                    espera();
                    break;

                case 2:
                    UIUtilities.clearScreen();
                    editarRepartidor();
                    espera();
                    break;

                case 3:
                    UIUtilities.clearScreen();
                    borrarRepartidor();
                    espera();
                    break;

                case 4:
                    UIUtilities.clearScreen();
                    listarRepartidor();
                    espera();
                    break;
        
                case 5:
                    break;
                default:
                    System.out.println("Seleccione una opción válida");

            }
        } while (opcionrepartidor != 5);
    }
    
    
}
