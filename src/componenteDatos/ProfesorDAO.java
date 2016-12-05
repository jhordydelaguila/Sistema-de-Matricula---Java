/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import componenteEntidad.Profesor;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class ProfesorDAO {
    private Connection cnn=null;
    private ResultSet rs = null;
    
    private static ProfesorDAO instancia;
    public static ProfesorDAO getInstancia(){
        if(instancia==null){
            instancia = new ProfesorDAO();
        }
        return instancia;
    }
    
    public void insertar(String idProfesor, String dni, String apellidos, String nombres,
            String telefono, String direccion) throws SQLException{
        
       cnn = Conexion.getInstancia().miConexion();
       PreparedStatement ps = null;
       
       try{
            ps = cnn.prepareStatement("INSERT INTO profesor"+
                   "(idprofesor" +
                   ",dni" +
                   ",apellidos" +
                    ",nombres"+
                   ",telefono" +
                   ",direccion)"+
                    "VALUES(?,?,?,?,?,?)"); 
           ps.setString(1, idProfesor);
           ps.setString(2, dni);
           ps.setString(3, apellidos);
           ps.setString(4, nombres);
           ps.setString(5, telefono);
           ps.setString(6, direccion);
           ps.executeUpdate();
           
       }catch(SQLException e){
           System.out.println("Error insertar profesor: " + e.getMessage());
       }finally{
           cnn.close();
           ps.close();
       } 
    }

    public int numeroProfesores() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM profesor");
            rs = ps.executeQuery();
            if(rs.next()){
               return rs.getInt("num");
            }else {
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Error numero de profesores: " + e.getMessage());
            return 0;
        }finally{
            cnn.close();
            ps.close();
        }
    }
    
    public Profesor buscarProfesor(String idProfesor) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Profesor profesor = null;
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM profesor WHERE idprofesor = ?");
            ps.setString(1, idProfesor);
            rs = ps.executeQuery();
            if(rs.next()){
                String dni = rs.getString("dni");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                
                profesor = new Profesor(
                        idProfesor, 
                        dni, 
                        apellidos, 
                        nombres, 
                        telefono, 
                        direccion);
                       
            }
        }catch(SQLException e){
            System.out.println("Error buscar profesor: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return profesor;
    }
    
    public void actualizar(String idProfesor, String dni, String apellidos, String nombres,
            String telefono, String direccion) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("UPDATE profesor "+ 
                    "SET dni = ?" +
                    ",apellidos = ?" +
                    ",nombres = ?" +
                    ",telefono = ?" +
                    ",direccion = ?"+
                    "WHERE idprofesor = ?");
            ps.setString(1, dni);
            ps.setString(2, apellidos);
            ps.setString(3, nombres);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, idProfesor);          
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error actualizar profesor: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void eliminar(String idProfesor)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM profesor WHERE idprofesor = ?");
            ps.setString(1, idProfesor);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar profesor" + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    
    public ArrayList<Profesor> mostrarProfesores() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Profesor> lista = new ArrayList<Profesor>();
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM profesor");
            rs = ps.executeQuery();
            
            while(rs.next()){
                String idProfesor = rs.getString("idprofesor");
                String dni = rs.getString("dni");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                
                Profesor profesor = new Profesor(
                        idProfesor, 
                        dni, 
                        apellidos, 
                        nombres, 
                        telefono, 
                        direccion);
                
                lista.add(profesor);
            }   
        }catch(SQLException e){
            System.out.println("Error mostrar profesoress: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return lista;
    }
    public ArrayList<Profesor> bucarPorApellidos(String ape) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Profesor> lista = new ArrayList<Profesor>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM profesor where apellidos like ?");
            ps.setString(1, ape+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idProfesor=rs.getString("idprofesor");
                String dni=rs.getString("dni");
                String apellidos=rs.getString("apellidos");
                String nombres=rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Profesor profesor= new Profesor(idProfesor, dni, apellidos, nombres, telefono, direccion);
                lista.add(profesor);
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar por apellidos: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
