/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.CursoDAO;
import componenteEntidad.Curso;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class CursoCN {
    
    private static CursoCN instancia;
    public static CursoCN getInstancia(){
        if(instancia==null){
            instancia = new CursoCN();
        }
        return instancia;
    }
    
    public void insertar(Curso curso) throws SQLException{ 
        CursoDAO.getInstancia().insertar(curso);
    }
    
    public int numeroDeCursos() throws SQLException{
        return CursoDAO.getInstancia().numeroCursos();
    }
    
    public void actualizar(Curso curso) throws SQLException{
        CursoDAO.getInstancia().actualizar(curso);
    }
    
    public void eliminar(String idCurso) throws SQLException{
        CursoDAO.getInstancia().eliminar(idCurso);
    }
    
    public Curso buscarCurso(String idCurso) throws SQLException{
        return CursoDAO.getInstancia().buscarCurso(idCurso);
    }
    
    public ArrayList<Curso> mostrarCursosProfesor(String idProfesor) throws SQLException{
        return CursoDAO.getInstancia().mostrarCursosProfesor(idProfesor);
    }
    
    public ArrayList<Curso> mostrarCursos() throws SQLException {
         return CursoDAO.getInstancia().mostrarCursos();
    }
    
    public ArrayList<Curso> buscarPorNombre(String nombre) throws SQLException{
        return CursoDAO.getInstancia().buscarPorNombre(nombre);
    }
}
