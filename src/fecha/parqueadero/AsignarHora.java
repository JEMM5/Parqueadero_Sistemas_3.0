package fecha.parqueadero;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AsignarHora extends Thread{
    private JLabel label_hora;
    
    public AsignarHora(JLabel label_hora){
        this.label_hora = label_hora;
    }
    
    @Override
    public void run(){
        while(true){
            Date hoy = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            label_hora.setText(sdf.format(hoy));
            
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }    
    }
}
