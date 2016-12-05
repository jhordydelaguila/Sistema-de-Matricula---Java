package componenteReporte;

import componenteDatos.Conexion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ReporteAlumnos {

    private Connection cnn;

    public ReporteAlumnos() {
        cnn = Conexion.getInstancia().miConexion();
    }

    public void runReporteAlumnos() {
 
        try {

//            String master = "C:\\Users\\JhordyAlexi\\Documents\\NetBeansProjects\\ProyectoBD02\\src\\componenteReporte\\reporteAlumnos.jasper";
//            //System.out.println("master: " + master);
//            if (master  == null) {  
//                System.out.println("no encuentro el archivo de reporte maestro");
//                System.exit(2);
//            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject("src\\componenteReporte\\reporteAlumnos.jasper");

            } catch (JRException e) {
                System.out.println("error cargando el reporte maestro:" + e.getMessage());
                System.exit(3);
            }
            Map parametro = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, cnn);
            JasperViewer jviewer = new JasperViewer(jasperPrint);
            jviewer.setTitle("REPORTE ALUMNOS");
            jviewer.setVisible(true);

        } catch (Exception j) {
            System.out.println("mensaje de error: " + j.getMessage());
        }
    }
}
