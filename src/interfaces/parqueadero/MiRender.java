package interfaces.parqueadero;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JONATHAN
 */
public class MiRender extends DefaultTableCellRenderer{
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column){
        
        JLabel cell =(JLabel) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        
        //
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date dHoy = cal.getTime();
        /*
        Date d1 = null;
        try {
            d1 = dateFormat.parse((String) value);
            //
        } catch (ParseException ex) {
            Logger.getLogger(MiRender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int d1p = (int) d1.getTime();
        int dHoyp = (int) dHoy.getTime();
        
        if(column == 5){
            if(d1p <= dHoyp){
                cell.setForeground(Color.red);
                //JTable_Disponibles.
            }else{
                cell.setForeground(Color.black);
            }
        }
        */
        /*
        if(value instanceof Object){
            //String int = (String) value;
            cell.setForeground(Color.red);
        }
        */
        /*
        if(column == 0){
            cell.setForeground(Color.red);
        }
        */
        if(value instanceof String){
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cell.setForeground(Color.black);
            //cell.setBackground(Default);
            if(row%2 == 0){
                cell.setBackground(Color.white);
            }else{
                cell.setBackground(new Color(245,245,245));
            }
            try {
                String valorD = (String) value;
                if(valorD.length() > 18){
                    
                    Date d = dateFormat.parse(valorD);
                    
                    //System.out.println("**"+d.getTime());
                    if(d instanceof Date && column ==4){
                        int d1p = (int) d.getTime();
                        int dHoyp = (int) dHoy.getTime();
                    
                        if(d1p <= dHoyp){
                        cell.setForeground(Color.red);
                        cell.setBackground(Color.yellow);
                        
                        
                        }
                        
                        //cell.setForeground(Color.red);
                    }                
                }                    
                
            } catch (ParseException ex) {
                Logger.getLogger(MiRender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return cell; 
    }
    
    
}
