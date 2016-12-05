/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.HorarioDAO;
import componenteEntidad.Horario;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class HorarioCN {
    
    private static HorarioCN instancia;
    public static HorarioCN getInstancia(){
        if(instancia==null){
            instancia = new HorarioCN();
        }
        return instancia;
    }
    
    public void insertar(String idHorario,String grado ,String turno, Date fecha, String horaInicio,String horaSalida) throws SQLException{ 
        HorarioDAO.getInstancia().insertar(idHorario, grado,turno, fecha, horaInicio,horaSalida);
    }
    public Horario buscaHorario(String idHorario) throws SQLException{
        return HorarioDAO.getInstancia().buscarHorario(idHorario);
    }
    public void actualizar(String idHorario,String grado,String turno, Date fecha, String horaInicio,String horaSalida)throws SQLException{
        HorarioDAO.getInstancia().actualizar(idHorario,grado,turno, fecha, horaInicio,horaSalida);
    }
    public void eliminar(String idHorario) throws SQLException{
       HorarioDAO.getInstancia().eliminar(idHorario);
    }
    public ArrayList<Horario> mostrarHorarios() throws SQLException {
         return HorarioDAO.getInstancia().mostrarHorarios();
    }
    public ArrayList<Horario> buscarPorTurno(String turno) throws SQLException{
        return HorarioDAO.getInstancia().buscarPorTurno(turno);
    }
}
