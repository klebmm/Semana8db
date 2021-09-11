/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tarea8progra2;

import datos.ClsEmpleadoJDBC;
import dominio.ClsEmpleado;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Caleb
 */
public class ClsEjemplo {
    public static void ej1(){
    
        
        //paso 1 definir el string de conexion
        String url = "jdbc:mysql://localhost:3306/dbempleados?zeroDateTimeBehavior=CONVERT_TO_NULL";
        try{
         //paso 2 crear el obj conexion
         Connection conexion = DriverManager.getConnection(url,"root","524532");
         
         //paso 3 crear el objeto statement
         Statement sentencia = conexion.createStatement();
         
         //paso 4 crear instruccion
         String sql = "select * from tb_empleados";
         
         //paso 5 ejecutamos el query
         ResultSet resultado = sentencia.executeQuery(sql);
         
         //paso 6 procesar el resultado
         while(resultado.next()){
             System.out.println("codigo = "+resultado.getInt(1));
             System.out.println("Nombre = "+resultado.getString(2));
             System.out.println("Enero = "+resultado.getInt(3));
             System.out.println("Febrero = "+resultado.getInt(4));
             System.out.println("Marzo = "+resultado.getInt(5));
             
         }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);  
        }
    }

    
     
    public static void main(String[] args) {
    ClsEmpleadoJDBC emp = new ClsEmpleadoJDBC();
    
    emp.menu();
    
   
  }
}
    
