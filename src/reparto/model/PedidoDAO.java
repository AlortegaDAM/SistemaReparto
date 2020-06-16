
package reparto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO implements CRUD{
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    PreparedStatement ps2;
    PreparedStatement psb;
    ResultSet rs;
    int actual_id_pedido=0;

    @Override
    public int add(Object element) {
        int r=0;
        if(element instanceof Pedido){
            Pedido p=(Pedido) element;
            
            String sql="insert into pedido (total, Cliente_idCliente,  Repartidor_idRepartidor) values(?, ?, ?)";
            String sql2="insert into pedido_has_producto (Pedido_idPedido, Producto_idProducto, cantidad, subtotal) values(?,?,?,?)";
            try{
                con=cn.Conectar();

                // PRIMERA SENTENCIA -> PEDIDO
                ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1,p.getTotal());
                ps.setInt(2,p.getIdCliente());
                ps.setInt(3,p.getIdRepartidor());

                ps.execute();

                // recogemos id
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    actual_id_pedido = rs.getInt(1);
                }
                // SEGUNDA SENTENCIA -> detalle pedido (pedido has producto)

                for(int i=0;i<p.getProductos().size();i++){
                    ps2=con.prepareStatement(sql2);
                    ps2.setInt(1,actual_id_pedido);
                    ps2.setInt(2,p.getProductos().get(i).getId());
                    ps2.setDouble(3,p.getProductos().get(i).getCantidad());
                    ps2.setDouble(4,p.getProductos().get(i).getSubtotal());
                    
                    ps2.executeUpdate();
                }
                con.close();
                r=1;
            }catch(SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return r;
    }

    @Override
    public int update(Object element) {
        int r=0;
        if(element instanceof Pedido){
            Pedido p=(Pedido) element;
            
            String sql="update pedido set total=?, Cliente_idCliente=?,  Repartidor_idRepartidor=? where idPedido=?";
            String sqlborrar="delete from pedido_has_producto where Pedido_idPedido=?";
            String sql2="insert into pedido_has_producto (Pedido_idPedido, Producto_idProducto, cantidad, subtotal) values(?,?,?,?)";
            try{
                con=cn.Conectar();

                // PRIMERA SENTENCIA -> PEDIDO
                ps=con.prepareStatement(sql);
                ps.setDouble(1,p.getTotal());
                ps.setInt(2,p.getIdCliente());
                ps.setInt(3,p.getIdRepartidor());
                ps.setInt(4,p.getIdPedido());

                ps.executeUpdate();
                // Segunda sentencia, borro registros de la tabla n:m
                psb=con.prepareStatement(sqlborrar);
                psb.setInt(1, p.getIdPedido());
                psb.executeUpdate();

                // SEGUNDA SENTENCIA -> detalle pedido (pedido has producto)

                for(int i=0;i<p.getProductos().size();i++){
                    ps2=con.prepareStatement(sql2);
                    ps2.setInt(1,p.getIdPedido());
                    ps2.setInt(2,p.getProductos().get(i).getId());
                    ps2.setDouble(3,p.getProductos().get(i).getCantidad());
                    ps2.setDouble(4,p.getProductos().get(i).getSubtotal());
                    
                    ps2.executeUpdate();
                }
                con.close();
                r=1;
            }catch(SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return r;
    }

    @Override
    public void delete(int id) {
            String sql="delete from pedido where idPedido=?";
        
        try{
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            
            con.close();
        }catch(SQLException ex){
        Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List load() {
        // Cargamos la base de datos en el módelo de datos de nuestra aplicación JAVA
        List <Pedido> elementos=new ArrayList<>();
        String sql="select * from pedido";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            //Recorremos el conjunto de resultados de la QUERY
            while (rs.next()){
                //Creación de objetos en nuestra Aplicación JAVA con los datos de la tabla
                Pedido p=new Pedido();
                p.setIdPedido(rs.getInt(1));
                p.setTotal(rs.getDouble(2));
                p.setIdCliente(rs.getInt(3));
                p.setIdRepartidor(rs.getInt(4));
                //p.setProductos(loadProductos(rs.getInt(1), con));
                
                elementos.add(p);
            }
            for(int i=0;i<elementos.size();i++){
                elementos.get(i).setProductos(loadProductos(elementos.get(i).getIdPedido(), con));
                
                
            }
            con.close();
            }catch(SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return elementos;
    }
    //id del pedido
    public List loadProductos(int id, Connection con){
        List <Producto> elementos=new ArrayList<>();
        String sqlp="SELECT T1.Producto_idProducto, T2.codigo, T2.nombre, T2.descripcion, T2.precio, T1.cantidad\n" +
                    "FROM pedido_has_producto T1, producto T2\n" +
                    "WHERE T1.Producto_idProducto =  T2.idProducto\n" +
                    "    AND T1.Pedido_idPedido =?";
        try{
            //establecer conexion
           //con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sqlp);
            ps.setInt(1,id);
            
            rs=ps.executeQuery();
            
            //Recorremos el conjunto de resultados de la QUERY
            while (rs.next()){
                //Creación de objetos en nuestra Aplicación JAVA con los datos de la tabla
                //Producto_idProducto, T2.nombre, T2.descripcion, T2.precio, T1.cantidad
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setCodigo(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setCantidad(rs.getInt(6));
                
                elementos.add(p);
            
            }
            //con.close();
            }catch(SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return elementos;
    }
}
