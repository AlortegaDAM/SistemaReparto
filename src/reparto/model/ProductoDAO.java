
package reparto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDAO implements CRUD{
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int add(Object element) {
        int r=0;
        if(element instanceof Producto){
          Producto p = (Producto) element;
        
           
        
        String sql="insert into producto(codigo, nombre, descripcion, precio) values (?,?,?,?)";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            //primer argumento ? segundo argumento objeto
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            //ejecutar
            r=ps.executeUpdate();
            con.close();
            }catch(SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                
        }
        }
        return r;
        
    }

    @Override
    public int update(Object element) {
        int r=0;
        if(element instanceof Producto){
          Producto p = (Producto) element;
       
        String sql="update producto set codigo=?, nombre=?, descripcion=?, precio=? where idProducto=?";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            //primer argumento ? segundo argumento objeto
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getId());
            //ejecutar
            r=ps.executeUpdate();
            con.close();;
            }catch(SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                
        }
        }
        return r;
    }

    @Override
    public void delete(int id) {
        String sql="delete from producto where idProducto=?";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            //primer argumento ? segundo argumento objeto
            ps.setInt(1,id);
            //ejecutar
            ps.executeUpdate();
            con.close();
            }catch(SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public List load() {
        // Cargamos la base de datos en el módelo de datos de nuestra aplicación JAVA
        List <Producto> elementos=new ArrayList<>();
        String sql="select * from producto";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            
            //Recorremos el conjunto de resultados de la QUERY
            while (rs.next()){
                //Creación de objetos en nuestra Aplicación JAVA con los datos de la tabla
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setCodigo(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                
                elementos.add(p);
            
            }
            con.close();
            }catch(SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return elementos;
    }
    
}