/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author JhordyAlexi
 */
public class Convertir {
    public static java.sql.Date convertJavaDateTOSQLDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
    public static String convertirFechaString(Date date){	
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
