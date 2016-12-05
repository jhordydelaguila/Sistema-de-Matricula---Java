/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.AulaDAO;
import componenteEntidad.Aula;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class AulaCN {
    
    private static AulaCN instancia;
    public static AulaCN getInstancia(){
        if(instancia==null){
            instancia = new AulaCN();
        }
        return instancia;
    }
    
    public void insertar(String idAula, int numeroAula, int capacidad) throws SQLException{ 
        AulaDAO.getInstancia().insertar(idAula, numeroAula, capacidad);
    }
    
    public Aula buscaAula(String idAula) throws SQLException{
        return AulaDAO.getInstancia().buscaAula(idAula);
    }
    
    public void actualizar(String idAula, int numeroAula, int capacidad)throws SQLException{
        AulaDAO.getInstancia().actualizar(idAula, numeroAula, capacidad);
    }
    
    public void eliminar(String idAula) throws SQLException{
        AulaDAO.getInstancia().eliminar(idAula);
    }
    
    public ArrayList<Aula> mostrarAulas() throws SQLException {
         return AulaDAO.getInstancia().mostrarAulas();
    }

    public ArrayList<Aula> buscarPorNumerodeAula(int numero) throws  SQLException{
        return AulaDAO.getInstancia().buscarPorNumero(numero);
    }
}
