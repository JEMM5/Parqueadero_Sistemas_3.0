package pdf.factura.parqueadero;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import java.io.IOException;
import com.itextpdf.text.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JONATHAN
 */
public class FacturaRegistro {
    private String noFactura;
    private String horaEntrada;
    private String horaSalida;
    private String tipo;
    private String placa;
    private String modelo;
    private String total;
    private String recordarFechaE;
    private String fechaS;
    //String nombreCambiante=  noFactura;
    
    public FacturaRegistro(String noFactura,String horaEntrada,String recordarFechaE, String horaSalida,String fechaS, String tipo,
            String placa, String modelo, String total){
        this.noFactura = noFactura;
        this.horaEntrada = horaEntrada;
        this.recordarFechaE = recordarFechaE;
        this.horaSalida = horaSalida;
        this.fechaS = fechaS;
        this.tipo = tipo;
        this.placa = placa;
        this.modelo = modelo;
        this.total = total;
    }
    
    public void generarFactura(){
        Rectangle pageSize = new Rectangle(300f,500f);
        Document documento = new Document(pageSize);
        documento.setMargins(8, 8, 2, 2);
        try{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/tiquetes/tiqueteNo." + noFactura+".pdf"));
            
            Image header = Image.getInstance("src/interfaces/parqueadero/images/HEAD2.png");
            //Image header = Image.getInstance("src/interfaces/parqueadero/images/cabececero.png");
            header.scaleToFit(300,350);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            
            
            Image f = Image.getInstance("src/interfaces/parqueadero/images/FOOTER2.png");
            f.scaleToFit(300,350);
            f.setAlignment(Chunk.ALIGN_CENTER);
            
            
            documento.open();
            
            Paragraph parrafo = new Paragraph();
            Paragraph parrafo0 = new Paragraph();
            Paragraph parrafo1 = new Paragraph();
            Paragraph parrafo2 = new Paragraph();
            Paragraph parrafo3 = new Paragraph();
            
            
            parrafo0.setAlignment(Paragraph.ALIGN_LEFT);
            parrafo0.add("-----------------------------------------------------------------------\n");
            parrafo1.setAlignment(Paragraph.ALIGN_RIGHT);
            parrafo1.setFont(FontFactory.getFont("Tahoma",14));
            //parrafo1.add(".......................................\n");
            
            parrafo1.add("FACTURA No.[  "+noFactura+"  ]\n");
            parrafo.add("-----------------------------------------------------------------------\n");
            //parrafo1.add("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n");
            //parrafo.add("\n. ");
            //parrafo.add("\n. ");
            parrafo.setAlignment(Paragraph.ALIGN_LEFT);
            parrafo.setFont(FontFactory.getFont("Tahoma",12));
            parrafo.add("ENTRADA: ["+recordarFechaE+"] ["+horaEntrada+"]\n");
            parrafo.add("SALIDA    : ["+fechaS+"] ["+horaSalida+"]\n");
            //parrafo.add("\n. ");
            
            //parrafo.add("No. XXX");
           
            
            
            parrafo.add("         ");
            
            //parrafo.add("No. XXX");
            //parrafo.setFont(FontFactory.getFont("Tahoma",25));
            //parrafo.add(" .\n");
            //parrafo.add(" .\n");
            parrafo.setFont(FontFactory.getFont("Tahoma",25));
            parrafo.add("*****************************");
            parrafo.setFont(FontFactory.getFont("Tahoma",12));
            
            parrafo.add("TIPO:        '"+tipo+"'\n");
            parrafo.add("PLACA:     '"+placa+"'\n");
            parrafo.add("MODELO: '"+modelo+"'\n");
            //parrafo4.add(" .\n");
            parrafo.add(" .\n");
            parrafo.setFont(FontFactory.getFont("Tahoma",25));
            parrafo.add("*****************************");
            parrafo.setFont(FontFactory.getFont("Tahoma",12));
            //parrafo4.add(" .\n");
            parrafo2.setAlignment(Paragraph.ALIGN_RIGHT);
            parrafo2.setFont(FontFactory.getFont("Tahoma",14));;
            parrafo2.add("TOTAL $ [  "+total+"  ]");
            parrafo3.add("-----------------------------------------------------------------------\n");
            //parrafo3.add(" .\n");
            parrafo3.add(f);
            
            
            documento.add(header);
            documento.add(parrafo0);
            documento.add(parrafo1);
            documento.add(parrafo);
            
            documento.add(parrafo2);
            documento.add(parrafo3);
            
            documento.close();
        }catch(DocumentException | IOException e){
            e.printStackTrace();
        }
        String ruta = System.getProperty("user.home");
        try {
            Runtime.getRuntime().exec("cmd /c start "+ ruta+"/Desktop/tiquetes/tiqueteNo." + noFactura +".pdf");
        } catch (IOException ex) {
            Logger.getLogger(FacturaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
