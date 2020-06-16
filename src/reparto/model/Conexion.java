
package reparto.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    Connection con;
    final String url="jdbc:mysql://localhost:3306/sistemareparto";
    final String user="root";
    final String pass="";
    final String timezone="?useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public Connection Conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url+timezone, user, pass);
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger("DBcon").log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    return con;
    }

    
    
    
}
