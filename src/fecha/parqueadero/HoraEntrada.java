package fecha.parqueadero;
import javax.swing.JTextField;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.text.DateFormat;
/**
 *
 * @author JONATHAN
 */
public class HoraEntrada extends Thread{
    private JTextField txt_entrada;
   
    
    public HoraEntrada(JTextField txt_entrada){
        this.txt_entrada = txt_entrada;
    }
    @Override
    public void run(){
        while(true){
            Date horaEntrada = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            txt_entrada.setText(sdf.format(horaEntrada));

            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
}
