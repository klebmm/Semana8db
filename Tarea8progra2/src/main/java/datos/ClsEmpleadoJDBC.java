/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Caleb
 */
public class ClsEmpleadoJDBC {
    
    private static final String SQL_SELECT = "select * from tb_empleados";
    private static final String SQL_UPDATE = "update tb_empleados set Nombre=?,Enero=?,Febrero=?,Marzo=? where codigo=?";
    private static final String SQL_INSERT = "insert into tb_empleados(Nombre,Enero,Febrero,Marzo) values(?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_empleados where codigo=?";
    
    
    public List<ClsEmpleado> seleccion(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsEmpleado empleado = null;
        List <ClsEmpleado> empleados = new ArrayList<ClsEmpleado>();
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("Nombre");
                double enero = rs.getDouble("Enero");
                double febrero = rs.getDouble("Febrero");
                double marzo = rs.getDouble("Marzo");
                
                empleado = new ClsEmpleado();
                empleado.setCodigo(codigo);
                empleado.setNombre(nombre);
                empleado.setEnero(enero);
                empleado.setFebrero(febrero);
                empleado.setMarzo(marzo);
                
                empleados.add(empleado);
                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return empleados; 
    }
    
    public int insert(ClsEmpleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    

    public int deleteDatos(ClsEmpleado empleado){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getCodigo());
            System.out.println("Dato eleminado..." + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Error al eliminar..." + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return rows;
    }
    
   /* public int update(ClsEmpleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            
            System.out.println("ejecutando query:" + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }*/
  
 Scanner i = new Scanner(System.in);
 Scanner i2 = new Scanner(System.in);
 String x;
 
 
 
 public void menu(){
        String opc = "";
        do{ClsEmpleadoJDBC empJDBC=new ClsEmpleadoJDBC();
            System.out.println("\n//////////////////\n///////Menu///////\n//////////////////\n");
            System.out.println("Elija una opcion (ingresando un numero)");
            System.out.println("1. Insertar Informacion");
            System.out.println("2. Ver Informacion");
            System.out.println("3. Eliminar Dato");
            System.out.println("4. Salir");
            opc = i.nextLine();
            switch(opc){
                
                case "1":
                    ClsEmpleado Insertar = new ClsEmpleado();
               
                    System.out.println("\nIngresa nombre de empleado: ");
                    x= i2.nextLine();
                    Insertar.setNombre(x);
                   
                    System.out.println("Ingrese mes de Enero: ");
                    x=i2.nextLine();
                    Insertar.setEnero(Integer.parseInt(x));
                   
                    System.out.println("Ingrese mes de Febrero: ");
                    x=i2.nextLine();
                    Insertar.setFebrero(Integer.parseInt(x));
                    
                    System.out.println("Ingrese mes de Marzo: ");
                    x=i2.nextLine();
                    Insertar.setMarzo(Integer.parseInt(x));
                    
                    
                    empJDBC.insert(Insertar);
 
                break;

                case "2":  
                List<ClsEmpleado>todos=empJDBC.seleccion();
                  
                for (ClsEmpleado empleado:todos){
                System.out.println("Empleados"+empleado);
                }
    
                break;   
                
                case "3":   
                ClsEmpleado delete = new ClsEmpleado();
                             
                System.out.println("\nIngrese el codigo aue desea eliminar:");
                x= i2.nextLine();
                delete.setCodigo(Integer.parseInt(x));
                empJDBC.deleteDatos(delete);
                break;
              
            }  

            }while(!opc.equals("4"));
    }
}
    

