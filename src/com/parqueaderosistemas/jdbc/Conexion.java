package com.parqueaderosistemas.jdbc;
import java.sql.*;

/**
 *
 * @author JONATHAN
 */
public class Conexion {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost/parking?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "";
    private static Driver driver = null;
    
    public static synchronized Connection getConnection() throws SQLException{
        if(driver == null){
            try{
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }
    
    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement pst){
        try{
            if(pst != null){
                pst.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void close(Connection cn){
        try{
            if(cn != null){
                cn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
