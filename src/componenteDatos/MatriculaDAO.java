/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package componenteDatos;
import componenteEntidad.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Programador
 */
public class MatriculaDAO {
     private Connection cnn = null;
    private ResultSet rs=null;

    private static MatriculaDAO instancia;

    public static MatriculaDAO getInstancia()
    {
        if(instancia==null)
            instancia=new MatriculaDAO();
        return instancia;
    }
    
    public void insertar(Matricula matricula) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO matricula " +
                    "(idmatricula" +
                    ",alumno_idalumno" +
                    ",programacion_idprogramacion" +
                    ",fecha)"+
                    "VALUES (?,?,?,?)");            
            ps.setString(1, matricula.getIdMatricula());
            ps.setString(2, matricula.getAlumno().getIdAlumno());
            ps.setString(3, matricula.getProgramacion().getIdProgramacion());
            ps.setDate(4, Convertir.convertJavaDateTOSQLDate(matricula.getFecha()));
            ps.executeUpdate();
            
            Object datos[][] = matricula.getLM().devuelvedatos();
            for(int i=0;i<datos.length;i++)
            {
                ps1=cnn.prepareStatement(
                        "INSERT INTO detalleMatricula(" +
                        "matricula_idmatricula" +
                        ",curso_idcurso)" +
                        "VALUES (?,?)");
                ps1.setString(1, matricula.getIdMatricula());
                ps1.setString(2, datos[i][0].toString());
                ps1.executeUpdate();                
            }            
            
        } catch (SQLException ex) {
            System.out.println("error insertar matricula: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
            ps1.close();
        }
    }
    public int cantidad() throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM matricula");
            rs = ps.executeQuery();
            if(rs.next()){
               return rs.getInt("num");
            }else {
                return 0;
            }
        }catch(SQLException e){
            System.out.println("Error cantidad matricula: " + e.getMessage());
            return 0;
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public Matricula buscarMatricula(String idMatricula) throws SQLException
    {
         cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        Matricula  matricula = null;
        try {
            ps1 = cnn.prepareStatement("SELECT * FROM matricula WHERE idmatricula = ?");
            ps1.setString(1, idMatricula);
            rs = ps1.executeQuery();
            
            if (rs.next()) {
                String idAlumno = rs.getString("alumno_idalumno");
                Alumno alumno = AlumnoDAO.getInstancia().buscarAlumno(idAlumno);
                String idProgramacion = rs.getString("programacion_idprogramacion");
                Programacion programacion = ProgramacionDAO.getInstancia().buscarProgramacion(idProgramacion);
                Date fecha = rs.getDate("fecha");
                matricula = new Matricula(idMatricula,fecha,programacion,alumno);
                ps2=cnn.prepareStatement("SELECT * FROM detalleMatricula where matricula_idmatricula = ?");
                ps2.setString(1, idMatricula);
                rs=ps2.executeQuery();
                while(rs.next()){
                    String idCurso = rs.getString("curso_idcurso");
                    Curso curso = CursoDAO.getInstancia().buscarCurso(idCurso);
                    matricula.registrarDetalleMatricula(curso);
                }
                ps2.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar matricula : " + ex.getMessage());
        } finally {
            cnn.close();
            ps1.close();        
        }
        return matricula;

    }
    public ArrayList<Matricula> mostrarMatriculas()throws SQLException
    {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ResultSet rs1=null;
        ArrayList<Matricula> lista = new ArrayList<Matricula>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM matricula");
            rs1=ps.executeQuery();
            while (rs1.next()) {
                String idMatricula = rs1.getString("idmatricula");
                Matricula matricula = buscarMatricula(idMatricula);
                lista.add(matricula);
            }
        } catch (SQLException ex) {
            System.out.println("error mostrar matriculas: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    public void eliminar(String idMatricula)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try{
            ps = cnn.prepareStatement("DELETE FROM matricula WHERE idmatricula = ?");
            ps.setString(1, idMatricula);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error eliminar matricula" + e.getMessage());
        }finally{
            cnn.close();
            ps.close();
        }
    }
    public ArrayList<Matricula> mostrarAlumnosEnAula(String idaula) throws SQLException{
        
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ResultSet rs1=null;
        ArrayList<Matricula> lista = new ArrayList<Matricula>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM matricula");
            rs1=ps.executeQuery();
            while (rs1.next()) {
                String idMatricula = rs1.getString("idmatricula");
                Matricula matricula = buscarMatricula(idMatricula);
                lista.add(matricula);
            }
        } catch (SQLException ex) {
            System.out.println("error mostrar matriculas: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
