/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import componenteEntidad.Alumno;
import componenteEntidad.Apoderado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JhordyAlexi
 */
public class AlumnoDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static AlumnoDAO instancia;

    public static AlumnoDAO getInstancia()
    {
        if(instancia==null)
            instancia=new AlumnoDAO();
        return instancia;
    }
    public void insertar(Alumno alumno)throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("INSERT INTO alumno " +
                    "(idalumno" +
                    ",apoderado_idapoderado"+
                    ",nombres"+
                    ",apellidos"+
                    ",sexo"+
                    ",telefono"+
                    ",direccion"+
                    ",fechaNacimiento) "+
                    "VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, alumno.getIdAlumno());
            ps.setString(2, alumno.getApoderado().getIdApoderado());
            ps.setString(3, alumno.getNombres());
            ps.setString(4, alumno.getApellidos());
            ps.setString(5, alumno.getSexo());
            ps.setString(6, alumno.getTelefono());
            ps.setString(7, alumno.getDireccion());
            ps.setDate(8, Convertir.convertJavaDateTOSQLDate(alumno.getFechaNacimiento()));
            ps.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Error insertar alumno: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
    }
    public int numeroAlumnos() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM alumno");
            rs = ps.executeQuery();
            if(rs.next()){
               return rs.getInt("num");
            }else {
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Error numero de alumnos: " + e.getMessage());
            return 0;
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void actualizar(Alumno alumno) throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("UPDATE alumno "+ 
                    "SET apoderado_idapoderado = ?" +
                    ",nombres = ?" +
                    ",apellidos = ?" +
                    ",sexo = ?" +
                    ",telefono = ?" +
                    ",direccion = ?" +
                    ",fechaNacimiento = ?"+
                    "WHERE idalumno = ?");
            ps.setString(1, alumno.getApoderado().getIdApoderado());
            ps.setString(2, alumno.getNombres());
            ps.setString(3, alumno.getApellidos());
            ps.setString(4, alumno.getSexo());
            ps.setString(5, alumno.getTelefono());
            ps.setString(6, alumno.getDireccion());
            ps.setDate(7, Convertir.convertJavaDateTOSQLDate(alumno.getFechaNacimiento()));
            ps.setString(8, alumno.getIdAlumno());
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error actualizar alumno: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void eliminar(String idAlumno)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM alumno WHERE idalumno = ?");
            ps.setString(1, idAlumno);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar alumno" + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public Alumno buscarAlumno(String idAlumno) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Alumno alumno = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM alumno WHERE idalumno = ?");
            ps.setString(1, idAlumno);
            rs = ps.executeQuery();
            
            if (rs.next()){          
                String idApoderado = rs.getString("apoderado_idapoderado");
                Apoderado apoderado = ApoderadoDAO.getInstancia().buscarApoderado(idApoderado);
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String sexo = rs.getString("sexo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                alumno = new Alumno(idAlumno, apoderado, sexo, nombres, apellidos, telefono, direccion, fechaNacimiento);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar alumno:  " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return alumno;
    }
    
    public ArrayList<Alumno> mostrarAlumnos() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        ResultSet rs1 = null;
        try{
            ps = cnn.prepareStatement("SELECT * FROM alumno");
            rs1 = ps.executeQuery();
            
            while(rs1.next()){
                String idAlumno = rs1.getString("idalumno");
                Alumno alumno = buscarAlumno(idAlumno);
                lista.add(alumno);
            }   
        }catch(SQLException e){
            System.out.println("Error mostrar alumno: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return lista;
    }
    public ArrayList<Alumno> buscarPorNombre(String nom) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM alumno where nombres like ?");
            ps.setString(1, nom+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idAlumno=rs.getString("idalumno");
                String idApoderado = rs.getString("apoderado_idapoderado");
                Apoderado apoderado = ApoderadoDAO.getInstancia().buscarApoderado(idApoderado);
                String nombres=rs.getString("nombres");
                String apellidos=rs.getString("apellidos");
                String telefono=rs.getString("telefono");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                Alumno alumno= new Alumno(idAlumno, apoderado, sexo, nombres, apellidos, telefono, direccion, fechaNacimiento);
                lista.add(alumno);
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
