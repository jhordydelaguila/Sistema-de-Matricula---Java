/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.ProfesorDAO;
import componenteEntidad.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class ProfesorCN {
    
    private static ProfesorCN instancia;
    public static ProfesorCN getInstancia(){
        if(instancia==null){
            instancia = new ProfesorCN();
        }
        return instancia;
    }
    
    public void insertar(String idProfesor, String dni, String apellidos, String nombres,
            String telefono, String direccion) throws SQLException{ 
        ProfesorDAO.getInstancia().insertar(idProfesor, dni, apellidos, nombres, telefono, direccion);
    }
    public int numeroProfesores() throws SQLException{
        return ProfesorDAO.getInstancia().numeroProfesores();
    }
    
    public Profesor buscarProfesor(String idProfesor) throws SQLException{
        return ProfesorDAO.getInstancia().buscarProfesor(idProfesor);
    }
    
    public void actualizar(String idProfesor, String dni, String apellidos, String nombres,
            String telefono, String direccion)throws SQLException{
        ProfesorDAO.getInstancia().actualizar(idProfesor, dni, apellidos, nombres, telefono, direccion);
    }
    
    public void eliminar(String idProfesor) throws SQLException{
        ProfesorDAO.getInstancia().eliminar(idProfesor);
    }
    
    public ArrayList<Profesor> mostrarProfesores() throws SQLException {
         return ProfesorDAO.getInstancia().mostrarProfesores();
    }
    
    public ArrayList<Profesor> buscarPorApellidos(String apellidos) throws SQLException{
        return ProfesorDAO.getInstancia().bucarPorApellidos(apellidos);
    }
}
