/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import componenteEntidad.Apoderado;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class ApoderadoDAO {
    private Connection cnn=null;
    private ResultSet rs = null;
    
    private static ApoderadoDAO instancia;
    public static ApoderadoDAO getInstancia(){
        if(instancia==null){
            instancia = new ApoderadoDAO();
        }
        return instancia;
    }
    
    public void insertar(String idApoderado, String nombres, String apellidos,
            String telefono, String direccion,String estadoCivil) throws SQLException{
        
       cnn = Conexion.getInstancia().miConexion();
       PreparedStatement ps = null;
       
       try{
            ps = cnn.prepareStatement("INSERT INTO apoderado"+
                   "(idapoderado" +
                   ",nombres" +
                   ",apellidos" +
                   ",telefono" +
                   ",direccion"+
                   ",estadoCivil) "+
                    "VALUES(?,?,?,?,?,?)"); 
           ps.setString(1, idApoderado);
           ps.setString(2, nombres);
           ps.setString(3, apellidos);
           ps.setString(4, telefono);
           ps.setString(5, direccion);
           ps.setString(6, estadoCivil);
           ps.executeUpdate();
           
       }catch(SQLException e){
           System.out.println("Error insertar apoderado: " + e.getMessage());
       }finally{
           cnn.close();
           ps.close();
       } 
    }

    public int numeroApoderados() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM apoderado");
            rs = ps.executeQuery();
            if(rs.next()){
               return rs.getInt("num");
            }else {
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Error numero de apoderados: " + e.getMessage());
            return 0;
        }finally{
            cnn.close();
            ps.close();
        }
    }
    
    public Apoderado buscarApoderado(String idApoderado) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Apoderado apoderado = null;
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM apoderado WHERE idapoderado = ?");
            ps.setString(1, idApoderado);
            rs = ps.executeQuery();
            if(rs.next()){
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String estadoCivil = rs.getString("estadoCivil");
                
                apoderado = new Apoderado(
                        idApoderado, 
                        nombres, 
                        apellidos, 
                        telefono, 
                        direccion, 
                        estadoCivil);
                       
            }
        }catch(SQLException e){
            System.out.println("Error buscar apoderado: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return apoderado;
    }
    
    public void actualizar(String idApoderado, String nombres, String apellidos,
            String telefono, String direccion,String estadoCivil) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("UPDATE apoderado "+ 
                    "SET nombres = ?" +
                    ",apellidos = ?" +
                    ",telefono = ?" +
                    ",direccion = ?" +
                    ",estadoCivil = ?"+
                    "WHERE idapoderado = ?");
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, estadoCivil);
            ps.setString(6, idApoderado);          
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error actualizar apoderado: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void eliminar(String idApoderado)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM apoderado " +
                    "WHERE idapoderado = ?");
            ps.setString(1, idApoderado);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar apoderado: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    
    public ArrayList<Apoderado> mostrarApoderados() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Apoderado> lista = new ArrayList<Apoderado>();
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM apoderado");
            rs = ps.executeQuery();
            
            while(rs.next()){
                String idApoderado = rs.getString("idapoderado");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String estadoCivil = rs.getString("estadoCivil");
                
                Apoderado apoderado = new Apoderado(
                        idApoderado, 
                        nombres, 
                        apellidos, 
                        telefono, 
                        direccion, 
                        estadoCivil);
                
                lista.add(apoderado);
            }   
        }catch(SQLException e){
            System.out.println("Error mostrar apoderados: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return lista;
    }
    public ArrayList<Apoderado> buscarPorNombre(String nom) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Apoderado> lista = new ArrayList<Apoderado>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM apoderado where nombres like ?");
            ps.setString(1, nom+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idApoderado=rs.getString("idapoderado");
                String nombres=rs.getString("nombres");
                String apellidos=rs.getString("apellidos");
                String telefono=rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String estadoCivil = rs.getString("estadoCivil");
                Apoderado apoderado= new Apoderado(idApoderado, nombres, apellidos, telefono, direccion, estadoCivil);
                lista.add(apoderado);
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar por nombre: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
