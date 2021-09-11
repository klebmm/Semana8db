/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caleb
 */
public class ClsConexion {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbempleados?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PWD = "524532";
     
     //crear metodo conexion
     public static Connection getConnection() throws SQLException{
         return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PWD);
         
     }
         
     //cerrar conexion
     public static void close(ResultSet rs){
         try {
             rs.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
      public static void close(PreparedStatement stmt){
         try {
             stmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
       public static void close(Connection cn){
         try {
             cn.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
         
   
}
