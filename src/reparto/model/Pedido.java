
package reparto.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pedido {
    private int idPedido;
    private double total;
    private int idCliente;
    private int idRepartidor;
    private List<Producto> productos;

    public Pedido() {
        this.idPedido= -1;
        this.productos= new ArrayList<>();
    }

    public Pedido(int idCliente, int idRepartidor) {
        this.idPedido=-1;
        this.total = 0;
        this.idCliente = idCliente;
        this.idRepartidor = idRepartidor;
        this.productos = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getTotal() {
        for(int i=0;i<productos.size();i++){
                double t=productos.get(i).getSubtotal();
                this.total=total+t;
           }
        
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void addProducto(Producto p){
        if(p!= null){
            if(p instanceof Producto){
                this.productos.add(p);
            }
        }
        
    }
    
    public void deleteProducto(int index){
       if(index >=0){
           this.productos.remove(index);
       }
    }

    @Override
    public String toString() {
        return "PEDIDO{" + "ID-PEDIDO=" + idPedido + ", TOTAL=" + total + ", ID-CLIENTE=" + idCliente + ", ID-REPARTIDOR=" + idRepartidor + ", PRODUCTOS=" + productos + '}';
    }
    
    
    
    
    
}
