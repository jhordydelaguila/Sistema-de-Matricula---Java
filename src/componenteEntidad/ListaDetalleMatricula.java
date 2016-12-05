/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author JhordyAlexi
 */
public class ListaDetalleMatricula {
    private Vector<DetalleMatricula> L;

    public ListaDetalleMatricula() {
        L = new Vector<DetalleMatricula>();
    }
    public void agregar(DetalleMatricula e){
        L.add(e);
    }
    public int buscar(DetalleMatricula e){
        return L.indexOf(e);
    }
    public void ordenar(Comparator<DetalleMatricula> cs){
        Collections.sort(L,cs);
    }
    public void eliminar(int i){
        L.remove(i);
    }
    public DetalleMatricula getElemento(int i){
        return L.get(i);
    }
    public int getN(){
        return L.size();
    }
    public void inserta(int i, DetalleMatricula e){
        L.add(i,e);
    }
    public void reemplaza(int i, DetalleMatricula e){
         L.set(i,e);
    }
    public Vector<DetalleMatricula> getL(){
        return L;
    }
    public Object[][] devuelvedatos(){
      Object datos[][]=new Object[L.size()][8];

      for(int i=0;i<L.size();i++){
        DetalleMatricula detalleMatricula = L.get(i);
        datos[i][0] = detalleMatricula.getCurso().getIdCurso();
        datos[i][1] = detalleMatricula.getCurso().getNombre();
//        datos[i][2] = detalleMatricula.getCurso().getProfesor().getIdProfesor();
//        datos[i][3] = detalleMatricula.getCurso().getProfesor().getDni();
//        datos[i][4] = detalleMatricula.getCurso().getProfesor().getApellidos();
//        datos[i][5] = detalleMatricula.getCurso().getProfesor().getNombres();
//        datos[i][6] = detalleMatricula.getCurso().getProfesor().getTelefono();
//        datos[i][7] = detalleMatricula.getCurso().getProfesor().getDireccion();
      }
      return datos;
    }
}
