/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.ApoderadoDAO;
import componenteEntidad.Apoderado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class ApoderadoCN {
    
    private static ApoderadoCN instancia;
    public static ApoderadoCN getInstancia(){
        if(instancia==null){
            instancia = new ApoderadoCN();
        }
        return instancia;
    }
    
    public void insertar(String idApoderado, String nombres, String apellidos,
            String telefono, String direccion,String estadoCivil) throws SQLException{ 
        ApoderadoDAO.getInstancia().insertar(idApoderado, nombres, apellidos, telefono, direccion, estadoCivil);
    }
    
    public int numeroApoderados() throws SQLException{
        return ApoderadoDAO.getInstancia().numeroApoderados();
    }
    
    public Apoderado buscarApoderado(String idApoderado) throws SQLException{
        return ApoderadoDAO.getInstancia().buscarApoderado(idApoderado);
    }
    
    public void actualizar(String idApoderado, String nombres, String apellidos,
            String telefono, String direccion,String estadoCivil)throws SQLException{
        ApoderadoDAO.getInstancia().actualizar(direccion, nombres, apellidos, telefono, direccion, estadoCivil);
    }
    
    public void eliminar(String idApoderado) throws SQLException{
        ApoderadoDAO.getInstancia().eliminar(idApoderado);
    }
    
    public ArrayList<Apoderado> mostrarApoderados() throws SQLException {
         return ApoderadoDAO.getInstancia().mostrarApoderados();
    }
    
    public ArrayList<Apoderado> buscarPorNombre(String nombre) throws SQLException{
        return ApoderadoDAO.getInstancia().buscarPorNombre(nombre);
    }
}
