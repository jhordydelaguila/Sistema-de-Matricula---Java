/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author JhordyAlexi
 */
public class Matricula {
    
    private String idMatricula;
    private Date fecha;
    private Programacion programacion;
    private Alumno alumno;
    private ListaDetalleMatricula LM;

    public Matricula(){
        LM = new ListaDetalleMatricula();
    }
    public Matricula(String idMatricula, Date fecha, Programacion programacion, Alumno alumno) {
        this.idMatricula = idMatricula;
        this.fecha = fecha;
        this.programacion = programacion;
        this.alumno = alumno;
        LM = new ListaDetalleMatricula();
    }

    public ListaDetalleMatricula getLM() {
        return LM;
    }

    public void setLM(ListaDetalleMatricula LM) {
        this.LM = LM;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Programacion getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idMatricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.idMatricula, other.idMatricula)) {
            return false;
        }
        return true;
    }

    public void registrarDetalleMatricula(Curso curso)
    {
        DetalleMatricula detalleMatricula = new DetalleMatricula(curso);
        LM.agregar(detalleMatricula);
    }
    public Object[][] devuelveDetalleMatricula()
    {
        return LM.devuelvedatos();
    }

    @Override
    public String toString() {
        return idMatricula + fecha +programacion+ alumno.getNombres();
    }
    
    
}
