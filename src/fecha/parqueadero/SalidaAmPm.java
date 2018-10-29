package fecha.parqueadero;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
/**
 *
 * @author JONATHAN
 */
public class SalidaAmPm extends Thread{
    private JComboBox tipoHoraS;
    
    public SalidaAmPm(JComboBox tipoHoraS){
        this.tipoHoraS = tipoHoraS;
    }
    @Override
    public void run(){
        while(true){
            Date tipo2 = new Date();
            SimpleDateFormat sdft = new SimpleDateFormat("aa");
            tipoHoraS.setSelectedItem(sdft.format(tipo2));
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
