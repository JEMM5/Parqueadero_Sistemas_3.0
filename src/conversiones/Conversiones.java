
package conversiones;

/**
 *
 * @author JONATHAN
 */
public class Conversiones {
    String fecha;
    String hora;
    //2018-10-23 19:48:15

    public Conversiones(){
    
    }
    public static String convertirHora(String fechaHora){
        String horaP = fechaHora.substring(11,19);
        int horaN = Integer.parseInt(horaP.substring(0,2));
        //String horaM = horaP.substring(0,2);
        String horaNueva = Integer.toString(horaN);
        
        String aMpM="";
        if(horaN >= 13){
            aMpM = "PM";
        }else if(horaN < 13){
            aMpM = "AM";
        }
    
        if(horaN == 13){
            horaNueva ="01";
        }else if(horaN == 14){
            horaNueva ="02";
        }else if(horaN == 15){
            horaNueva ="03";
        }else if(horaN == 16){
            horaNueva ="04";
        }else if(horaN == 17){
            horaNueva ="05";
        }else if(horaN == 18){
            horaNueva ="06";
        }else if(horaN == 19){
            horaNueva ="07";
        }else if(horaN == 20){
            horaNueva ="08";
        }else if(horaN == 21){
            horaNueva ="09";
        }else if(horaN == 22){
            horaNueva ="10";
        }else if(horaN == 23){
            horaNueva ="11";
        }else if(horaN == 24){
            horaNueva ="12";
        }else if(horaN == 00){
            horaNueva ="12";
        }
        
        if(horaN == 1){
            horaNueva ="01";
        }else if(horaN == 2){
            horaNueva ="02";
        }else if(horaN == 3){
            horaNueva ="03";
        }else if(horaN == 4){
            horaNueva ="04";
        }else if(horaN == 5){
            horaNueva ="05";
        }else if(horaN == 6){
            horaNueva ="06";
        }else if(horaN == 7){
            horaNueva ="07";
        }else if(horaN == 8){
            horaNueva ="08";
        }else if(horaN == 9){
            horaNueva ="09";
        }else if(horaN == 10){
            horaNueva ="10";
        }else if(horaN == 11){
            horaNueva ="11";
        }else if(horaN == 12){
            horaNueva ="12";
        }
        
        
        
        return horaNueva+":"+horaP.substring(3,8)+" "+ aMpM;
    }
    public static String convertirFecha(String fechaHora){
        //2018-10-23 19:48:15
        String fecha = fechaHora.substring(0,10);
        return fecha;
    }
    
}
