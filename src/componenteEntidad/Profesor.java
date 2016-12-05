/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

import java.util.Objects;

/**
 *
 * @author JhordyAlexi
 */
public class Profesor extends Persona{
    
    private String idProfesor;
    private String dni;

    public Profesor(){
        
    }
    public Profesor(String idProfesor, String dni, String apellidos, String nombres,
            String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.idProfesor = idProfesor;
        this.dni = dni;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idProfesor);
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.idProfesor, other.idProfesor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProfesor  + dni + super.toString() ;
    }
    
    
}
