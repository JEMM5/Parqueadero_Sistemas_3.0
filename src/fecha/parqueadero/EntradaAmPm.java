package fecha.parqueadero;
import javax.swing.JComboBox;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author JONATHAN
 */
public class EntradaAmPm extends Thread{
    private JComboBox tipoHoraE;
    
    public EntradaAmPm(JComboBox tipoHoraE){
        this.tipoHoraE = tipoHoraE;
    }
    
    @Override
    public void run(){
        while(true){
            Date tipo = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("aa");
            tipoHoraE.setSelectedItem(sdf.format(tipo));
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
            
    } 
}
