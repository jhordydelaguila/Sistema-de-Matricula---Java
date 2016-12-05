/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.AlumnoDAO;
import componenteEntidad.Alumno;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class AlumnoCN {
    
    private static AlumnoCN instancia;
    public static AlumnoCN getInstancia(){
        if(instancia==null){
            instancia = new AlumnoCN();
        }
        return instancia;
    }
    
    public void insertar(Alumno alumno) throws SQLException{ 
        AlumnoDAO.getInstancia().insertar(alumno);
    }
    
    public int numeroAlumnos() throws  SQLException{
        return AlumnoDAO.getInstancia().numeroAlumnos();
    }
    
    public void actualizar(Alumno alumno) throws SQLException{
        AlumnoDAO.getInstancia().actualizar(alumno);
    }
    
    public void eliminar(String idAlumno) throws SQLException{
        AlumnoDAO.getInstancia().eliminar(idAlumno);
    }
    
    public Alumno buscarAlumno(String idAlumno) throws SQLException{
        return AlumnoDAO.getInstancia().buscarAlumno(idAlumno);
    }
    
    public ArrayList<Alumno> mostrarAlumnos() throws SQLException {
         return AlumnoDAO.getInstancia().mostrarAlumnos();
    }
    
    public ArrayList<Alumno> buscarPorNombre(String nombre) throws SQLException{
        return AlumnoDAO.getInstancia().buscarPorNombre(nombre);
    }
}
