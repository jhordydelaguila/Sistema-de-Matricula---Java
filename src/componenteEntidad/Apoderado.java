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
public class Apoderado extends Persona{
    
    private String idApoderado;
    private String estadoCivil;

    public Apoderado(){
        
    }
    public Apoderado(String idApoderado, String nombres, String apellidos,
            String telefono, String direccion,String estadoCivil) {
        super(nombres, apellidos, telefono, direccion);
        this.idApoderado = idApoderado;
        this.estadoCivil = estadoCivil;
    }

    public String getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(String idApoderado) {
        this.idApoderado = idApoderado;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return   idApoderado +super.toString() + estadoCivil ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idApoderado);
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
        final Apoderado other = (Apoderado) obj;
        if (!Objects.equals(this.idApoderado, other.idApoderado)) {
            return false;
        }
        return true;
    }
    
    
}
