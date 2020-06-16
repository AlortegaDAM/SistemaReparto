/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reparto.controller;

import java.util.ArrayList;
import java.util.List;
import reparto.model.Cliente;
import reparto.model.ClienteDAO;
import reparto.model.Pedido;
import reparto.model.PedidoDAO;
import reparto.model.Producto;
import reparto.model.ProductoDAO;
import reparto.model.Repartidor;
import reparto.model.RepartidorDAO;
import reparto.utils.UIUtilities;
import static reparto.utils.UIUtilities.espera;
import static reparto.utils.UIUtilities.seleccionarInt;

/**
 *
 * @author Vinil
 */
public class AppController {
    static ProductoDAO prdao=new ProductoDAO();
    static PedidoDAO pdao=new PedidoDAO();
    static ClienteDAO cdao=new ClienteDAO();
    static RepartidorDAO rdao=new RepartidorDAO();
    
    public static void registrarCliente(){
        System.out.println("Para registrar un cliente, es necesario añadir su nombre, dirección, tlf movil y mail.");
        System.out.println("Introduzca el nombre:");
        String nombre = UIUtilities.getString();
        System.out.println("Introduzca la dirección:");
        String direccion = UIUtilities.getString();
        System.out.println("Introduzca el movil:");
        double movil = UIUtilities.getDouble();
        System.out.println("Introduzca el mail:");
        String mail = UIUtilities.getString();
        System.out.println("Creando cliente...");

        
        Cliente c=new Cliente(nombre,direccion, movil, mail);
        
        cdao.add(c);
    }
    public static void registrarProducto(){
    System.out.println("Para registrar un producto, es necesario añadir su código, nombre, descripción y precio.");
        System.out.println("Introduzca el código:");
        int codigo = UIUtilities.getInt();
        System.out.println("Introduzca el nombre:");
        String nombre = UIUtilities.getString();
        System.out.println("Introduzca la descripción:");
        String descripcion = UIUtilities.getString();
        System.out.println("Introduzca el precio:");
        double precio = UIUtilities.getDouble();
        System.out.println("Registrando Producto...");

        
        Producto p=new Producto(codigo, nombre,descripcion, precio);
        
        prdao.add(p);
    }
    public static void registrarRepartidor(){
    System.out.println("Para registrar un repartidor, es necesario añadir su nombre y turno.");
        System.out.println("Introduzca el nombre:");
        String nombre = UIUtilities.getString();
        System.out.println("Introduzca el turno:");
        String turno = UIUtilities.getString(); 
        Repartidor r=new Repartidor(nombre, turno);
        
        rdao.add(r);
    }
    public static void nuevoPedido(){
    System.out.println("Para registrar un pedido, es necesario añadir el id delCliente, el id del Repartidor y los productos contenidos.");
    espera();
    Pedido aux=new Pedido();
    int idc;
    listarCliente();
    List<Cliente> listaclientes=cdao.load();
                
                System.out.println("Seleccione el id del cliente que realiza el pedido");
                idc=seleccionarInt();
                for (int ic=0;ic<listaclientes.size();ic++){
                       if (listaclientes.get(ic).getId()==idc){
                           aux.setIdCliente(idc);
                           int idr;
                           List<Repartidor> listarepartidores=rdao.load();
                           listarRepartidor();
                            System.out.println("Seleccione el id del repartidor que se encargará del pedido");
                            idr=seleccionarInt();
                           for (int ir=0;ir<listarepartidores.size();ir++){
                                if (listarepartidores.get(ir).getId()==idr){
                                aux.setIdRepartidor(idr);
                                List<Producto> productos=rellenarPedido();
                                aux.setProductos(productos);
                                pdao.add(aux);                                
                                
                                }else{
                                }
                           
                            }
                       }else{          
                       }
                }
            }
    
    public static void listarCliente(){
        int i;
        System.out.println("#############################################--LISTADO DE CLIENTES--#############################################");
        List<Cliente> lista=cdao.load();
        for(i=0;i<lista.size();i++){
        System.out.println(lista.get(i));
        }
        System.out.println("#############################################--LISTADO DE CLIENTES--#############################################");

    }
    public static void listarRepartidor(){
        int i;
        System.out.println("#############################################--LISTADO DE REPARTIDORES--#############################################");
        List<Repartidor> lista=rdao.load();
        for(i=0;i<lista.size();i++){
        System.out.println(lista.get(i));
        }
        System.out.println("#############################################--LISTADO DE REPARTIDORES--#############################################");

    }
    public static void listarProducto(){
        int i;
        System.out.println("#############################################--LISTADO DE PRODUCTOS--#############################################");
        List<Producto> lista=prdao.load();
        for(i=0;i<lista.size();i++){
        System.out.println(lista.get(i));
        }
        System.out.println("#############################################--LISTADO DE PRODUCTOS--#############################################");

    }
    public static void listarPedidos(){
        int i;
        System.out.println("#############################################--LISTADO DE PEDIDOS--#############################################");
        List<Pedido> lista=pdao.load();
        for(i=0;i<lista.size();i++){
        System.out.println(lista.get(i));
        }
        System.out.println("#############################################--LISTADO DE PEDIDOS--#############################################");

    }
    
    public static void editarCliente(){
        int c;
        listarCliente();
        List<Cliente> listaclientes=cdao.load();
        Cliente clientemodificado=new Cliente();
        System.out.println("Seleccione el cliente que desea modificar mediante la id del mismo");
        c=seleccionarInt();
        clientemodificado.setId(c);
        for (int i=0;i<listaclientes.size();i++){
             if (listaclientes.get(i).getId()==clientemodificado.getId()){
                System.out.println("Para modificar un cliente, es necesario modificar su nombre, dirección, tlf movil y mail.");
                System.out.println("Nombre actual: "+listaclientes.get(i).getNombre() );
                System.out.println("Introduzca el nuevo nombre:");
                String nombre = UIUtilities.getString();
                System.out.println("Dirección actual: "+listaclientes.get(i).getDireccion());
                System.out.println("Introduzca la nueva dirección:");
                String direccion = UIUtilities.getString();
                System.out.println("Movil actual: "+listaclientes.get(i).getMovil());
                System.out.println("Introduzca el movil nuevo:");
                double movil = UIUtilities.getDouble();
                System.out.println("Mail actual: "+listaclientes.get(i).getMail());
                System.out.println("Introduzca el nuevo mail:");
                String mail = UIUtilities.getString();
                System.out.println("Modificando cliente...");
                 
               
                clientemodificado.setNombre(nombre);
                clientemodificado.setDireccion(direccion);
                clientemodificado.setMovil(movil);
                clientemodificado.setMail(mail);
                 
                 
                 cdao.update(clientemodificado);
             }
             else{}    
        }   
    }
    public static void editarRepartidor(){
        int c;
        listarRepartidor();
        List<Repartidor> listarp=rdao.load();
        Repartidor aux=new Repartidor();//repartidor que nos servirá para modificar uno existente
        System.out.println("Seleccione el repartidor que desea modificar mediante la id del mismo");
        c=seleccionarInt();
        aux.setId(c);
        for (int i=0;i<listarp.size();i++){           
            if (listarp.get(i).getId()==aux.getId()){
                System.out.println("Para editar un repartidor, es necesario añadir su nombre y turno.");
                System.out.println("Nombre actual: "+listarp.get(i).getNombre() );
                System.out.println("Introduzca el nuevo nombre:");
                String nombre = UIUtilities.getString();
                System.out.println("Turno actual: "+listarp.get(i).getTurno());
                System.out.println("Introduzca el nuevo turno:");
                String turno = UIUtilities.getString(); 
                System.out.println("Modificando cliente..."); 
                aux.setNombre(nombre);
                aux.setTurno(turno);
                         
                rdao.update(aux);
            }
            else{}     
        }
    }
    public static void editarProducto(){
        int c;
        listarProducto();
        List<Producto> listapr=prdao.load();
        Producto aux=new Producto();//producto que nos servirá para modificar uno existente
        System.out.println("Seleccione el producto que desea modificar mediante la id del mismo");
        c=seleccionarInt();
        aux.setId(c);
        for (int i=0;i<listapr.size();i++){           
            if (listapr.get(i).getId()==aux.getId()){
                System.out.println("Para modificar un producto, es necesario modificar su código, nombre, descripción y precio.");
                System.out.println("Código actual: "+listapr.get(i).getCodigo());
                System.out.println("Introduzca el nuevo código:");
                int codigo = UIUtilities.getInt();
                System.out.println("Nombre actual: "+listapr.get(i).getNombre());
                System.out.println("Introduzca el nuevo nombre:");
                String nombre = UIUtilities.getString();
                System.out.println("Descripción actual: "+listapr.get(i).getDescripcion());
                System.out.println("Introduzca la nueva descripción:");
                String descripcion = UIUtilities.getString();
                System.out.println("Precio actual: "+listapr.get(i).getPrecio());
                System.out.println("Introduzca el precio actualizado:");
                double precio = UIUtilities.getDouble();
                System.out.println("Actualizando Producto...");
                aux.setCodigo(codigo);
                aux.setNombre(nombre);
                aux.setDescripcion(descripcion);
                aux.setPrecio(precio);
                         
                prdao.update(aux);
            }
            else{}     
        }
    }
    public static void editarPedido(){
        int id;
        listarPedidos();
        List<Pedido> listap=pdao.load();
        Pedido aux=new Pedido();
        System.out.println("Seleccione el pedido que desea editar mediante la id del mismo");
        id=seleccionarInt();
        for (int i=0;i<listap.size();i++){           
            if (listap.get(i).getIdPedido()==id){
                aux.setIdPedido(id);
                System.out.println("Para modificar el Pedido necesitamos:");
                System.out.println("-Modificar el id del Cliente");
                System.out.println("-Modificar el id del Repartidor");
                System.out.println("-Modificar los productos que lo componen");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                int idc;
                listarCliente();
                List<Cliente> listaclientes=cdao.load();
                System.out.println("Cliente actual: "+listap.get(i).getIdCliente());
                System.out.println("Seleccione el id del cliente que realiza el pedido");
                idc=seleccionarInt();
                for (int ic=0;ic<listaclientes.size();ic++){
                       if (listaclientes.get(ic).getId()==idc){
                           aux.setIdCliente(idc);
                           int idr;
                           List<Repartidor> listarepartidores=rdao.load();
                           listarRepartidor();
                           System.out.println("Repartidor actual: "+listap.get(i).getIdRepartidor());
                           System.out.println("Seleccione el id del repartidor que se encargará del pedido");
                           idr=seleccionarInt();
                           for (int ir=0;ir<listarepartidores.size();ir++){
                                if (listarepartidores.get(ir).getId()==idr){
                                aux.setIdRepartidor(idr);
                                List<Producto> productos=rellenarPedido(listap.get(i).getProductos());
                                aux.setProductos(productos);
                                pdao.update(aux);                                
                                
                                }else{
                                }
                           
                            }
                       }else{          
                       }
                }
            }
        }
        
        
    }
    
    public static void borrarProducto(){
        int aux;
        listarProducto();
        List<Producto> listapr=prdao.load();
        System.out.println("Seleccione el producto que desea eliminar mediante la id del mismo");
        aux=seleccionarInt();
        for (int i=0;i<listapr.size();i++){           
            if (listapr.get(i).getId()==aux){
                prdao.delete(aux);
        
            }
        }
    }
    public static void borrarCliente(){
        boolean existepedido=false;
        int aux;
        listarCliente();
        List<Cliente> listac=cdao.load();
        List<Pedido> listap=pdao.load();
        System.out.println("Seleccione el cliente que desea eliminar mediante la id del mismo");
        aux=seleccionarInt();
        for(int ip=0;ip<listap.size();ip++){
            if(listap.get(ip).getIdCliente()==aux){
                System.out.println("El cliente que desea borrar tiene actualmente pedidos activos");
                existepedido=true;
            }
        }
        for (int i=0;i<listac.size();i++){           
            if (listac.get(i).getId()==aux && existepedido!=true){
                cdao.delete(aux);
            }
        }
    }
    public static void borrarRepartidor(){
        boolean existepedido=false;
        int aux;
        List<Pedido> listap=pdao.load();
        listarRepartidor();
        List<Repartidor> listarp=rdao.load();
        System.out.println("Seleccione el repartidor que desea eliminar mediante la id del mismo");
        aux=seleccionarInt();
        for(int ip=0;ip<listap.size();ip++){
            if(listap.get(ip).getIdRepartidor()==aux){
                System.out.println("El repartidor que desea borrar tiene actualmente pedidos activos");
                existepedido=true;
            }
        }
        for (int i=0;i<listarp.size();i++){           
            if (listarp.get(i).getId()==aux && existepedido!=true){
                rdao.delete(aux);
        
            }
        }
    }
    public static void borrarPedido(){
        int aux;
        listarPedidos();
        List<Pedido> listap=pdao.load();
        System.out.println("Seleccione el pedido que desea eliminar mediante la id del mismo");
        aux=seleccionarInt();
        for (int i=0;i<listap.size();i++){           
            if (listap.get(i).getIdPedido()==aux){
                pdao.delete(aux);
        
            }
        }
    }
    public static List<Producto> rellenarPedido(List<Producto> producto){
        boolean result=false;
        List<Producto> listapr=prdao.load();
        List<Producto> productos=new ArrayList<>();
        System.out.println("Para rellenar el pedido de productos, escoja el producto por su id y seleccione la cantidad de artículos de un mismo producto incluidos en el pedido.");
        
        do{
            System.out.println("1.Añadir productos");
            System.out.println("2.Cesta de productos");
            System.out.println("3.Finalizar el pedido");
            int opcionrelleno= UIUtilities.getInt();
            switch(opcionrelleno){
                case 1:
                    listarProducto();
                    System.out.println("Para rellenar el pedido de productos, escoja el producto por su id y seleccione la cantidad de artículos de un mismo producto incluidos en el pedido.");
                    int idp=UIUtilities.getInt();
                    
                    for (int i=0;i<listapr.size();i++){           
                        if (listapr.get(i).getId()==idp){
                            System.out.println("¿Cuantas unidades de dicho artículo desea?");
                            int pc=UIUtilities.getInt();
                            Producto ppedido=listapr.get(i);
                            ppedido.setCantidad(pc);
                            ppedido.getSubtotal();
                            System.out.println(ppedido);
                            productos.add(ppedido);
                            break;

                        }
                       
                    
                    }
                case 2:
                    System.out.println("Contenido del pedido anteriormente: "+ producto);
                    System.out.println(productos);
                    break;
                case 3:
                        System.out.println("Finalizando pedido");
                        result=true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }
        }while(result!=true);
    
    return productos;
    }
    public static List<Producto> rellenarPedido(){
        boolean result=false;
        List<Producto> listapr=prdao.load();
        List<Producto> productos=new ArrayList<>();
        System.out.println("Para rellenar el pedido de productos, escoja el producto por su id y seleccione la cantidad de artículos de un mismo producto incluidos en el pedido.");
        
        do{
            System.out.println("1.Añadir productos");
            System.out.println("2.Cesta de productos");
            System.out.println("3.Finalizar el pedido");
            int opcionrelleno= UIUtilities.getInt();
            switch(opcionrelleno){
                case 1:
                    listarProducto();
                    System.out.println("Para rellenar el pedido de productos, escoja el producto por su id y seleccione la cantidad de artículos de un mismo producto incluidos en el pedido.");
                    int idp=UIUtilities.getInt();
                    
                    for (int i=0;i<listapr.size();i++){           
                        if (listapr.get(i).getId()==idp){
                            System.out.println("¿Cuantas unidades de dicho artículo desea?");
                            int pc=UIUtilities.getInt();
                            Producto ppedido=listapr.get(i);
                            ppedido.setCantidad(pc);
                            ppedido.getSubtotal();
                            System.out.println(ppedido);
                            productos.add(ppedido);
                            break;

                        }
                       
                    
                    }
                case 2:
                    System.out.println(productos);
                    break;
                case 3:
                        System.out.println("Finalizando pedido");
                        result=true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
                    break;
            }
        }while(result!=true);
    
    return productos;
    }
    
}    
