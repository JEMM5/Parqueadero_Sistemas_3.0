package fecha.parqueadero;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
/**
 *
 * @author JONATHAN
 */
public class HoraSalida extends Thread{
    private JTextField txt_salida;
    
    public HoraSalida(JTextField txt_salida){
        this.txt_salida = txt_salida;
    }
    @Override
    public void run(){
        while(true){
            Date horaSalida = new Date();
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
            txt_salida.setText(sdf2.format(horaSalida));
        
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
