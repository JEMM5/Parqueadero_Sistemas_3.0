package com.parqueaderosistemas.jdbc;
import java.sql.*;
import java.util.*;
import com.parqueaderosistemas.beans.Usuario;
/**
 *
 * @author JONATHAN
 */
public class UsuariosJDBC {
    
    private static String SQL_INSERT = "INSERT INTO usuarios_park (id,nombre,usuario,contrasena,tipo_usuario) "
            + " VALUES (?,?,?,?,?) ";
    private static String SQL_UPDATE = "UPDATE usuarios_park SET nombre=?, usuario=?, "
            + "contrasena=?, tipo_usuario=? WHERE id=";
    private static String SQL_DELETE = "DELETE FROM usuarios_park WHERE id=?";
    private static String SQL_SELECT = "SELECT * FROM usuarios_park ORDER BY id";
    
    public void insert(Usuario pUsuario){
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setString(1, "0");
            pst.setString(2, pUsuario.getNombre());
            pst.setString(3, pUsuario.getUsuario());
            pst.setString(4, pUsuario.getContrasena());
            pst.setString(5, pUsuario.getTipoUsuario());
            pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(pst);
            Conexion.close(cn);
        }
    }
    
    public void update(Usuario pUsuario){
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_UPDATE+Integer.toString(pUsuario.getId()));
            pst.setString(1, pUsuario.getNombre());
            pst.setString(2, pUsuario.getUsuario());
            pst.setString(3, pUsuario.getContrasena());
            pst.setString(4, pUsuario.getTipoUsuario());
            
            //pst.setInt(1, pUsuario.getId());
            pst.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(pst);
            Conexion.close(cn);
        }
    }
    public void delete(int id){
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(pst);
            Conexion.close(cn);
        }
    }
    
    public List<Usuario> select(){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        Usuario usuario = null;
        List<Usuario>lista = new ArrayList();
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_SELECT);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String user = rs.getString(3);
                String contrasena = rs.getString(4);
                String tipoUsuario = rs.getString(5);
                //javabean
                usuario = new Usuario();
                usuario.setId(id);
                usuario.setNombre(nombre);
                usuario.setUsuario(user);
                usuario.setContrasena(contrasena);
                usuario.setTipoUsuario(tipoUsuario);
                lista.add(usuario);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(pst);
            Conexion.close(cn);
        }
        return lista;
    }
}
