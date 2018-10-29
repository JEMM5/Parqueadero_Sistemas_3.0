package com.parqueaderosistemas.jdbc;
import java.sql.*;
import com.parqueaderosistemas.beans.Precio;
/**
 *
 * @author JONATHAN
 */
public class PreciosJDBC {
    //private static String SQL_INSERT = "INSERT INTO precios_park (tipo_vehiculo, valor) VALUES (?,?)";
    private static String SQL_UPDATE = "UPDATE precios_park SET tipo_vehiculo = ?, valor = ? WHERE indice=";
    private static String SQL_SELECT = "SELECT * FROM precios_park WHERE indice=?";
    
    public void update(Precio precio){
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_UPDATE + precio.getIndice());
            pst.setString(1, precio.getTipo());
            pst.setDouble(2, precio.getValor());
            pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(pst);
            Conexion.close(cn);
        }    
    }
    
    public Precio select(int ind){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Precio precio = null;
        try{
            cn = Conexion.getConnection();
            pst = cn.prepareStatement(SQL_SELECT);
            pst.setInt(1, ind);
            rs = pst.executeQuery();
            if(rs.next()){
                String tipoVeh = rs.getString("tipo_vehiculo");
                double valorT = rs.getDouble("valor");
                
                //System.out.println("**"+tipoVeh+valorT);
                
                precio = new Precio();
                precio.setTipo(tipoVeh);
                precio.setValor(valorT);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return precio;
    }
}
