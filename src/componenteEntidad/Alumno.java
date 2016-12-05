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
public class Alumno extends Persona{
    private String idAlumno;
    private Apoderado apoderado;
    private String sexo;
    private Date fechaNacimiento;

    public Alumno(){
        
    }
    public Alumno(String idAlumno, Apoderado apoderado, String sexo, String nombres, 
            String apellidos, String telefono, String direccion,Date fechaNacimiento) {
        super(nombres, apellidos, telefono, direccion);
        this.idAlumno = idAlumno;
        this.apoderado = apoderado;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return idAlumno + apoderado + sexo  +super.toString()+ fechaNacimiento ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idAlumno);
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
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.idAlumno, other.idAlumno)) {
            return false;
        }
        return true;
    }

    
    
}
