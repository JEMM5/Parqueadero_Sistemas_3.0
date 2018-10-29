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

/**
 *
 * @author JONATHAN
 */
public class FacturaRegistro1 {
    String nombreCambiante="";
    
    public FacturaRegistro1(){
    //pasamos los parametros
    }
    
    public void generarFactura(){
        Rectangle pageSize = new Rectangle(270f,450f);
        Document documento = new Document(pageSize);
        documento.setMargins(2, 2, 2, 2);
        try{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/tiquetes/tiqueteNo.1" + nombreCambiante+".pdf"));
            
            Image header = Image.getInstance("src/interfaces/parqueadero/images/ET.png");
            header.scaleToFit(300,350);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //Image header = Image.getInstance("src/interfaces/parqueadero/images/cabececero.png");
            /*
            
            
            Image fv = Image.getInstance("src/interfaces/parqueadero/images/.png");            
            fv.scaleToFit(80, 30);
            fv.setAlignment(Chunk.ALIGN_RIGHT);
            */
            /*
            Image tt = Image.getInstance("src/interfaces/parqueadero/images/tt.png");
            tt.scaleToFit(130, 30);
            tt.setAlignment(Chunk.ALIGN_RIGHT);
            
            Image f = Image.getInstance("src/interfaces/parqueadero/images/FOOTER2.png");
            f.scaleToFit(300,350);
            f.setAlignment(Chunk.ALIGN_CENTER);
            */
            
            documento.open();
            
            Paragraph parrafo = new Paragraph();
           
            //parrafo.setAlignment(Paragraph.);
            parrafo.add("mmmmmmmm");
            parrafo.add(header);
            parrafo.add("mmmmmmmm");
            /*
            parrafo.add("  ENTRADA _____________________");
            //parrafo.add("         ");
            parrafo.add(fv);
            //parrafo.setFont(FontFactory.getFont("Tahoma",25));
            //parrafo.add(" .\n");
            //parrafo.add(" .\n");
            parrafo.setFont(FontFactory.getFont("Tahoma",25));
            parrafo.add("******************************");
            parrafo.setFont(FontFactory.getFont("Tahoma",12));
            parrafo.add("  TIPO:        _________________________________");
            parrafo.add("  PLACA:     _________________________________");
            parrafo.add("  MODELO: _________________________________");
            parrafo.add(" .\n");
            parrafo.add(" .\n");
            parrafo.setFont(FontFactory.getFont("Tahoma",25));
            parrafo.add("******************************");
            parrafo.setFont(FontFactory.getFont("Tahoma",12));
            parrafo.add(" .\n");
            parrafo.add(tt);
            parrafo.add("--------------------------------------------------------------------------\n");
            parrafo.add(" .\n");
            parrafo.add(f);
            */
            
            //documento.add(header);
            documento.add(parrafo);
            documento.close();
        }catch(DocumentException | IOException e){
            e.printStackTrace();
        }
    }
    
}
