/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.view;



import static reparto.controller.AppController.borrarPedido;
import static reparto.controller.AppController.editarPedido;
import static reparto.controller.AppController.listarPedidos;
import static reparto.controller.AppController.nuevoPedido;
import reparto.utils.UIUtilities;
import static reparto.utils.UIUtilities.espera;

/**
 *
 * @author Vinil
 */
public class PedidosVista {
    
        public static void pedidos() {
        int opcionpedido;
        do {
            System.out.println("Bienvenido al menu Pedidos");
            System.out.println("Seleccione una opción:");
            System.out.println("----------------------------");
            System.out.println("1.- Nuevo Pedido");
            System.out.println("2.- Editar Pedido");
            System.out.println("3.- Eliminar Pedido");
            System.out.println("4.- Listado de Pedidos");
            System.out.println("5.- Volver al menú principal");
            System.out.println("----------------------------");
            
            opcionpedido = UIUtilities.getInt();

            switch (opcionpedido) {

                case 1:
                    UIUtilities.clearScreen();
                    nuevoPedido();
                    break;

                case 2:
                    UIUtilities.clearScreen();
                    editarPedido();
                    break;

                case 3:
                    UIUtilities.clearScreen();
                    borrarPedido();
                    break;

                case 4:
                    UIUtilities.clearScreen();
                    listarPedidos();
                    espera();
                    break;
        
                case 5:
                    break;
                default:
                    System.out.println("Seleccione una opción válida");

            }
        } while (opcionpedido != 5);
    }
    
}
