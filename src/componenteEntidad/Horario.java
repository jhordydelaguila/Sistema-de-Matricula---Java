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
public class Horario {
    
    private String idHorario;
    private String grado;
    private String turno;
    private Date fechaInicio;
    private String horaInicio;
    private String horaSalida;

    public Horario(){
        
    }

    public Horario(String idHorario,String grado, String turno, Date fechaInicio, 
            String horaInicio, String horaSalida) {
        this.idHorario = idHorario;
        this.grado = grado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horaSalida = horaSalida;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getFecha() {
        return fechaInicio;
    }

    public void setFecha(Date fecha) {
        this.fechaInicio = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idHorario);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.idHorario, other.idHorario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idHorario + turno + fechaInicio + horaInicio ;
    }
    
    
}
