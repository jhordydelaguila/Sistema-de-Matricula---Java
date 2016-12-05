/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import componenteEntidad.Aula;
import componenteEntidad.Horario;
import componenteEntidad.Programacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JhordyAlexi
 */
public class ProgramacionDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static ProgramacionDAO instancia;

    public static ProgramacionDAO getInstancia()
    {
        if(instancia==null)
            instancia=new ProgramacionDAO();
        return instancia;
    }
    public void insertar(Programacion programacion)throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("INSERT INTO programacion " +
                    "(idprogramacion" +
                    ",horario_idhorario"+
                    ",aula_idaula) "+
                    "VALUES(?,?,?)");
            ps.setString(1, programacion.getIdProgramacion());
            ps.setString(2, programacion.getHorario().getIdHorario());
            ps.setString(3, programacion.getAula().getIdAula());
            ps.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Error insertar programacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
    }
    public void actualizar(Programacion programacion) throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        
        try{
            ps = cnn.prepareStatement("UPDATE programacion "+ 
                    "SET horario_idhorario = ?" +
                    ",aula_idaula = ?" +
                    "WHERE idprogramacion = ?");
            ps.setString(1, programacion.getHorario().getIdHorario());
            ps.setString(2, programacion.getAula().getIdAula());
            ps.setString(3, programacion.getIdProgramacion());
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error actualizar programacion: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public void eliminar(String idProgramacion)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM programacion WHERE idprogramacion = ?");
            ps.setString(1, idProgramacion);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar programacion" + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public Programacion buscarProgramacion(String idProgramacion) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Programacion programacion = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM programacion WHERE idprogramacion = ?");
            ps.setString(1, idProgramacion);
            rs = ps.executeQuery();
            
            if (rs.next()){          
                String idHorario = rs.getString("horario_idhorario");
                Horario horario = HorarioDAO.getInstancia().buscarHorario(idHorario);
                String idAula = rs.getString("aula_idaula");
                Aula aula = AulaDAO.getInstancia().buscaAula(idAula);
                
                programacion = new Programacion(idProgramacion, horario, aula);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar programacion:  " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return programacion;
    }
    
    public ArrayList<Programacion> mostrarProgramacion() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Programacion> lista = new ArrayList<Programacion>();
        ResultSet rs1 = null;
        try{
            ps = cnn.prepareStatement("SELECT * FROM programacion");
            rs1 = ps.executeQuery();
            
            while(rs1.next()){
                String idProgramacion = rs1.getString("idprogramacion");
                Programacion programacion = buscarProgramacion(idProgramacion);
                lista.add(programacion);
            }   
        }catch(SQLException e){
            System.out.println("Error mostrar programacion: " + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
}
