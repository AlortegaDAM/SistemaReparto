/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.view;

import static reparto.controller.AppController.borrarProducto;
import static reparto.controller.AppController.editarProducto;
import static reparto.controller.AppController.listarProducto;
import static reparto.controller.AppController.registrarProducto;
import reparto.utils.UIUtilities;
import static reparto.utils.UIUtilities.espera;

/**
 *
 * @author Vinil
 */
public class ProductosVista {
    
        public static void productos() {
        int opcionproducto;
        do {
            System.out.println("Bienvenido al menu Productos");
            System.out.println("Seleccione una opción:");
            System.out.println("----------------------------");
            System.out.println("1.- Registrar Producto");
            System.out.println("2.- Editar Producto");
            System.out.println("3.- Eliminar Producto");
            System.out.println("4.- Listado de Productos");
            System.out.println("5.- Volver al menú principal");
            System.out.println("----------------------------");

            opcionproducto = UIUtilities.getInt();

            switch (opcionproducto) {

                case 1:
                    UIUtilities.clearScreen();
                    registrarProducto();
                    espera();
                    break;

                case 2:
                    UIUtilities.clearScreen();
                    editarProducto();
                    espera();
                    break;

                case 3:
                    UIUtilities.clearScreen();
                    borrarProducto();
                    espera();
                    break;
                case 4:
                    UIUtilities.clearScreen();
                    listarProducto();
                    espera();
                    break;
                case 5:
                    UIUtilities.clearScreen();
                    break;
                default:
                    System.out.println("Seleccione una opción válida");

            }
        } while (opcionproducto != 5);
    }
    
}
