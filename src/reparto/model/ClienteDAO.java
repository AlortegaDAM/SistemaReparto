
package reparto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements CRUD{
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int add(Object element) {
        int r=0;
        if(element instanceof Cliente){
            Cliente c=(Cliente) element;
            
            String sql="insert into Cliente (nombre, direccion, movil, mail) values(?, ?, ?, ?)";
            try{
                //establecemos conexion
                con=cn.Conectar();
                //preparamos la sentencia SQL
                ps=con.prepareStatement(sql);
                //asignamos valores a los distintos argumentos
                ps.setString(1,c.getNombre());
                ps.setString(2,c.getDireccion());
                ps.setDouble(3,c.getMovil());
                ps.setString(4,c.getMail());
                //ejecutar
                r=ps.executeUpdate();
                con.close();
                
            }catch(SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return r;
    }

    @Override
    public int update(Object element) {
        int r=0;
        if(element instanceof Cliente){
        Cliente c=(Cliente) element;
        
        String sql="update cliente set nombre=?, direccion=?, movil=?, mail=? where idCliente=?";
        try{
            //conexion
            con=cn.Conectar();
            //preparamos la sentencia SQL
            ps=con.prepareStatement(sql);
            //asignamos valores a los argumentos
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getDireccion());
            ps.setDouble(3,c.getMovil());
            ps.setString(4,c.getMail());
            ps.setInt(5,c.getId());
            
            //ejecutamos
            r=ps.executeUpdate();
            con.close();
         
        }catch(SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return r;
    }

    @Override
    public void delete(int id) {
        
       String sql="delete from cliente where idCliente=?";
       try{
       //conexion
       con=cn.Conectar();
       //preparamos la sentencia SQL
       ps=con.prepareStatement(sql);
       //definimos los argumentos
       ps.setInt(1,id);
       //ejecutamos
       ps.executeUpdate();
       con.close();
       }
       catch(SQLException ex){
           Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public List load() {
        // Cargamos la base de datos en el módelo de datos de nuestra aplicación JAVA
        List <Cliente> elementos=new ArrayList<>();
        String sql="select * from cliente";
        try{
            //establecer conexion
            con=cn.Conectar();
            //preparación de la sentencia SQL
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            
            //Recorremos el conjunto de resultados de la QUERY
            while (rs.next()){
                //Creación de objetos en nuestra Aplicación JAVA con los datos de la tabla
                Cliente c=new Cliente();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDireccion(rs.getString(3));
                c.setMovil(rs.getDouble(4));
                c.setMail(rs.getString(5));
                
                elementos.add(c);
            
            }con.close();
            }catch(SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return elementos;
    }
    
    
    
}
