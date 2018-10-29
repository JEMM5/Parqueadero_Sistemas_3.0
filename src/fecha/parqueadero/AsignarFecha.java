package fecha.parqueadero;
import java.util.Date;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;

public class AsignarFecha extends Thread{
    private JLabel label_fecha;
    
    public AsignarFecha(JLabel label_fecha){
        this.label_fecha = label_fecha;
    }
    
    @Override
    public void run(){
        while(true){
            Date hoy = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            label_fecha.setText(sdf.format(hoy));
            
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        /*
        while(true){
            //Date hoy = new Date();
            //SimpleDateFormat sdf = new SimpleDateFormat("dd:mm:yy");
            //label_fecha.setText(sdf.format(hoy));
            try{
                AsignarFecha.sleep(1000000000);
            }catch(Exception e){}
        }
        */
    }
    
}
