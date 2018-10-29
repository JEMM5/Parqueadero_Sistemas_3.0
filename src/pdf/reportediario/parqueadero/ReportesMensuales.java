package pdf.reportediario.parqueadero;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.sql.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conversiones.Conversiones;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ReportesMensuales {
    String año;
    String mes;
    String fechaBuscar; 
    
    String mesNumero;
    public ReportesMensuales(String año, String mes){
        this.año = año;
        this.mes = mes;
        
        mesNumero = mes.substring(0,2);        
        fechaBuscar = this.año+"-"+mesNumero; //le pongo el 15 temportalmente dia de hoy
        //System.out.println("**"+fechaBuscar);
    }
    
    public void GenerarReporteMensual(){
        //parqueaderoRegistro pr = new parqueaderoRegistro();
        //String nombrePdf = pr.label_fecha.getText();
        //System.out.println(fechaHoy);
        Rectangle pageSize = new Rectangle(1200f,700f);
        
        Document documento = new Document(pageSize);
        
        
        //
        //String ruta = System.getProperty("user.home");
        //System.out.println(ruta + "\\Desktop\\tiquetes\\reportes\\"+nombrePdf+".pdf");
        try{
            
        String ruta = System.getProperty("user.home");
        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "\\Desktop\\reporte-"+fechaBuscar+".pdf"));
        Image header = Image.getInstance("src/interfaces/parqueadero/images/cabececero.png");
        header.scaleToFit(450,800);
        header.setAlignment(Chunk.ALIGN_CENTER);
        
        //
        Anchor imagenCond = null;
        Font fuente = new Font();
        Font fcab = new Font();
        
        documento.open();
        fuente.setColor(BaseColor.BLUE);
        fuente.setStyle(Font.UNDERLINE);
        fcab.setStyle(Font.BOLD);
        //fcab.setStyle();
        //imagenCond = new Anchor(rs.getString(10)+"\n ",fuente);        
        documento.add(header);
        
        PdfPTable tabla = new PdfPTable(13);
        //float[] medidas = {0.55f,2.25f,0.55f,0.55f};
        //tabla.setWidths(medidas);
        tabla.setWidthPercentage(105);
        Anchor t1 = new Anchor("PARQUEO #",fcab);        
        tabla.addCell(t1);
        Anchor t2 = new Anchor("PLACA",fcab);
        tabla.addCell(t2);
        Anchor t3 = new Anchor("TIPO VEHICULO",fcab);
        tabla.addCell(t3);
        Anchor t4 = new Anchor("MODELO",fcab);
        tabla.addCell(t4);
        Anchor t5 = new Anchor("COLOR",fcab);
        tabla.addCell(t5);
        //FECHA
        Anchor t6 = new Anchor("FECHA ENTRADA",fcab);
        tabla.addCell(t6);
        Anchor t7 = new Anchor("HORA ENTRADA",fcab);
        tabla.addCell(t7);
        Anchor t8 = new Anchor("FECHA SALIDA",fcab);
        tabla.addCell(t8);
        Anchor t9 = new Anchor("HORA SALIDA",fcab);
        tabla.addCell(t9);
        Anchor t10 = new Anchor("Horas:Min.",fcab);
        tabla.addCell(t10);
        Anchor t11 = new Anchor("VALOR A PAGAR",fcab);
        tabla.addCell(t11);
        Anchor t12 = new Anchor("ESTADO",fcab);
        tabla.addCell(t12);
        Anchor t13 = new Anchor("Responsable",fcab);
        tabla.addCell(t13);
           
           try{
               Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
               //PreparedStatement pst = cn.prepareStatement("select * from park where ");
               PreparedStatement pst = cn.prepareStatement("select * from parq where HoraEntrada like '%"+fechaBuscar+"%'"+"AND Estado like '%"+"Retirado"+"%'");
               ResultSet rs = pst.executeQuery();
               
               if(rs.next()){
                   do{
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        //
                        
                        String fecha = Conversiones.convertirFecha(rs.getString(6));
                        tabla.addCell(fecha+"\n ");
                        String hora = Conversiones.convertirHora(rs.getString(6));
                        tabla.addCell(hora);
                        
                        String fecha2 = Conversiones.convertirFecha(rs.getString(7));
                        tabla.addCell(fecha2);
                        String hora2 = Conversiones.convertirHora(rs.getString(7));
                        tabla.addCell(hora2);
                        
                        /*
                        tabla.addCell(rs.getString(7));
                        tabla.addCell(rs.getString(7));
                        */
                        
                        tabla.addCell(rs.getString(8));
                        tabla.addCell(rs.getString(9));
                        
                        
                        //tabla.addCell(rs.getString(10)+ " " + imagenCond);
                        
                        //tabla.addCell("ww.dfd".);
                        
                        
                        if(rs.getString(10).equals("Retirado S.T.")){
                            
                            imagenCond = new Anchor(rs.getString(10)+"\n ",fuente);                        
                            imagenCond.setReference("file:///"+rs.getString(12));
                            tabla.addCell(imagenCond);
                            
                        }else{                           
                            tabla.addCell(rs.getString(10));
                            }
                        
                        tabla.addCell(rs.getString(11));
                       
                   }while(rs.next());
                   documento.add(tabla);
               }
           }catch(DocumentException | SQLException e){
               e.printStackTrace();
           }
           documento.close();
           JOptionPane.showMessageDialog(null, "Reporte Creado.");
           //
        }catch(DocumentException | HeadlessException | FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ReportesMensuales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
