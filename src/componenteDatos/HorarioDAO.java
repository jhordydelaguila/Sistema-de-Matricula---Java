/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import componenteEntidad.Apoderado;
import componenteEntidad.Horario;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class HorarioDAO {
    private Connection cnn=null;
    private ResultSet rs = null;
    
    private static HorarioDAO instancia;
    public static HorarioDAO getInstancia(){
        if(instancia==null){
            instancia = new HorarioDAO();
        }
        return instancia;
    }
    
    public void insertar(String idHorario,String grado, String turno,Date fechaInicio, 
            String horaInicio, String horaSalida) throws SQLException{
        
       cnn = Conexion.getInstancia().miConexion();
       PreparedStatement ps = null;
       
       try{
            ps = cnn.prepareStatement("INSERT INTO horario"+
                   "(idhorario" +
                    ",grado"+
                   ",turno" +
                   ",fechaInicio" +
                   ",horaEntrada"+
                   ",horaSalida) "+
                    "VALUES(?,?,?,?,?,?)"); 
           ps.setString(1, idHorario);
           ps.setString(2, grado);
           ps.setString(3, turno);
           ps.setDate(4, fechaInicio);
           ps.setString(5, horaInicio);
           ps.setString(6, horaSalida);
           ps.executeUpdate();
           
       }catch(SQLException e){
           System.out.println("Error insertar horario: " + e.getMessage());
       }finally{
           cnn.close();
           ps.close();
       } 
    }

    public Horario buscarHorario(String idHorario) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Horario horario = null;
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM horario WHERE idHorario = ?");
            ps.setString(1, idHorario);
            rs = ps.executeQuery();
            if(rs.next()){
                String turno = rs.getString("turno");
                String grado = rs.getString("grado");
                Date fecha = rs.getDate("fechaInicio");
                String horaInicio = rs.getString("horaEntrada");
                String horaSalida = rs.getString("horaSalida");

                
                horario = new Horario(
                        idHorario, 
                        grado,
                        turno, 
                        fecha, 
                        horaInicio,
                        horaSalida);
                       
            }
        }catch(SQLException e){
            System.out.println("Error buscar horario: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return horario;
    }
    
    public void actualizar(String idHorario,String grado, String turno,Date fechaInicio, 
            String horaInicio, String horaSalida) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("UPDATE horario "+ 
                    "SET grado = ?"+
                    ",turno = ?" +
                    ",fechaInicio = ?" +
                    ",horaEntrada = ?" +
                    ",horaSalida = ?" +
                    "WHERE idhorario = ?");
            ps.setString(1,grado);
            ps.setString(2, turno);
            ps.setDate(3, fechaInicio);
            ps.setString(4, horaInicio);
            ps.setString(5, horaSalida);
            ps.setString(6, idHorario);         
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error actualizar horario: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void eliminar(String idHorario)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM horario WHERE idhorario = ?");
            ps.setString(1, idHorario);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar horario" + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    
    public ArrayList<Horario> mostrarHorarios() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Horario> lista = new ArrayList<Horario>();
        
        try{
            ps = cnn.prepareStatement("SELECT * FROM horario");
            rs = ps.executeQuery();
            
            while(rs.next()){
                String idhorario = rs.getString("idhorario");
                String grado = rs.getString("grado");
                String turno = rs.getString("turno");
                Date fecha = rs.getDate("fechaInicio");
                String horaInicio = rs.getString("horaEntrada");
                String horaSalida = rs.getString("horaSalida");
                
                Horario horario = new Horario(
                        idhorario,
                        grado,
                        turno, 
                        fecha, 
                        horaInicio,
                        horaSalida);
                
                lista.add(horario);
            }   
        }catch(SQLException e){
            System.out.println("Error mostrar horarios: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return lista;
    }
    public ArrayList<Horario> buscarPorTurno(String tur) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM horario where turno like ?");
            ps.setString(1, tur+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idHorario = rs.getString("idhorario");
                String grado = rs.getString("grado");
                String turno = rs.getString("turno");
                Date fecha = rs.getDate("fechaInicio");
                String horaInicio = rs.getString("horaEntrada");
                String horaSalida = rs.getString("horaSalida");
                Horario horario= new Horario(idHorario,grado, turno, fecha, horaInicio,horaSalida);
                lista.add(horario);
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar por turno: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
