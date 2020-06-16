
package reparto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepartidorDAO implements CRUD{
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    

    @Override
    public int add(Object element) {
        int r=0;
        if(element instanceof Repartidor){
        Repartidor rp=(Repartidor) element;
        
        String sql="insert into repartidor (nombre, turno) values(?,?)";
        
       try{
       //conexion
       con=cn.Conectar();
       //preparamos la sentencia SQL
       ps=con.prepareStatement(sql);
       ps.setString(1,rp.getNombre());
       ps.setString(2,rp.getTurno());
       //ejecutamos
       r=ps.executeUpdate();
       }catch(SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return r;
    }

    @Override
    public int update(Object element) {
        int r=0;
        if(element instanceof Repartidor){
            Repartidor rp=(Repartidor) element;
            String sql="update repartidor set nombre=?, turno=? where idRepartidor=?";
            try{
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, rp.getNombre());
            ps.setString(2, rp.getTurno());
            ps.setInt(3, rp.getId());
            r=ps.executeUpdate();
            }
            catch(SQLException ex){
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return r;
    }

    @Override
    public void delete(int id) {
        String sql="delete from repartidor where idRepartidor=?";
        
        try{
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(SQLException ex){
        Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List load() {
        List <Repartidor> elementos=new ArrayList<>();
        String sql="select * from repartidor";
        try{
        //establecemos conexion
        con=cn.Conectar();
        //preparación de la sentencia SQL
        ps=con.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        
        //Recorremos el conjunto de resultados de la QUERY
        while(rs.next()){
            //Creación de objetos en nuestra Aplicación JAVA con los datos de la tabla
            Repartidor rp=new Repartidor();
            rp.setId(rs.getInt(1));
            rp.setNombre(rs.getString(2));
            rp.setTurno(rs.getString(3));                                       
            elementos.add(rp);
        }
        con.close();
        }catch (SQLException ex){
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementos;
    }
    
    
}
