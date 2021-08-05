
package modelo;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sabina
 */
public class PersonaDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Persona p = new Persona();
    
    public List listar(){
    
        List <Persona> datos = new ArrayList<>();
    
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement("select * from persona");
            rs = ps.executeQuery();
            while (rs.next()) {
            Persona p = new Persona();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setCorreo(rs.getString(3));
            p.setCelular(rs.getString(4));
            datos.add(p);
            }
        
        
        }catch(Exception e){
            
        }
         return datos;
    
    }// fin listar
    
    public int agregar(Persona per){
        int r = 0;
        String sql = "insert into persona (Nombre,Correo,Celular) value (?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getCorreo());
            ps.setString(3, per.getCelular());
            r = ps.executeUpdate();
            if (r == 1){
            return 1;
        } 
            else {
            return 0;
            }

    } catch(Exception e){
} 
    return r ;
}//fin agregar
    
    public int Actualizar(Persona per){
    int r=0;
    String sql="update persona set Nombre=?,Correo=?,Celular=? where ID=?";
    try{
        con=conectar.getConnection();
        ps=con.prepareStatement(sql);
        r= ps.executeUpdate();
      
    }catch(Exception e){
    }
    return r;
    
    }
    
    public int Delete(int id){
    int r=0;
    String sql="delete from persona where Id= "+id;
    try{
    
        con=conectar.getConnection();
        ps=con.prepareStatement(sql);
        r= ps.executeUpdate();
        
    
    }catch(Exception e){
    
    }
    return r;
    }
    
} // fin clase DAO
