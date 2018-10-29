package interfaces.parqueadero;
//ejemplo prueba
import pdf.factura.parqueadero.FacturaRegistro;

import com.parqueaderosistemas.beans.DisponiblesPrepagado;
import com.parqueaderosistemas.beans.Precio;
import com.parqueaderosistemas.jdbc.PreciosJDBC;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.awt.Toolkit;

import java.sql.*;
import java.io.*;

import com.parqueaderosistemas.negocio.*;

import fecha.parqueadero.*;
//import java.awt.Desktop;
import java.awt.Font;
import pdf.reportediario.parqueadero.*;
/////////////////////
import java.util.*;
import javax.swing.JLabel;

import com.parqueaderosistemas.usuarios.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import conversiones.Conversiones;
import javax.swing.table.DefaultTableModel;

//la camara
import camara.parqueadero.WebcamViewerExample;
import static interfaces.parqueadero.ParqueaderoRegistroUsuarios.rutaFoto;
import java.awt.Color;
//import com.parqueaderosistemas.usuarios.Usuarios;
//////////la lista
/**
 *
 * @author JONATHAN
 */
public class ParqueaderoRegistroUsuarios2 extends javax.swing.JFrame {
    //fototomada
    public static String rutaFoto = null;
    public boolean banderaBuscar = false;
    public boolean banderaCalcular = false;
    //fechaHora va en el registro y nos ayuda a obtener la id en el metodo
    String fechaHora ="";
    String horaFechaE ="";
    String horaFechaS ="";
    HoraEntrada he;
    EntradaAmPm eap;
    /*
    HoraSalida hs;
    SalidaAmPm sap;    
    */
    String recordarFechaE = "";
    /**
     * Creates new form parqueaderoRegistro
     */
    private JComboBox comboBox1;
    private static String texto_area = "";
    //intentando q recuerde la lista si quedan autos
    List<String> listaParqueos = new ArrayList();
 
    String text2;
       
    private JComboBox tipoHoraE;
    //private JComboBox tipoHoraS;
    
    public JLabel labelHora;
    public JLabel labelHora2;

    public ParqueaderoRegistroUsuarios2() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBounds(0,0,800,573);
        comboBox1 = new JComboBox();
        comboBox1.setBounds(136, 198, 153, 30);
        add(comboBox1);

        comboBox1.addItem("");
        comboBox1.addItem("Carro");
        comboBox1.addItem("Vehiculo Pesado");
        comboBox1.addItem("Moto");
        
        //
        //txt_salida.setText("0");
        txt_dias.setText("0");
        txt_valor.setText("0");
        //
        /*
        tipoHoraS = new JComboBox();
        tipoHoraS.setBounds(220,388,55,30);
        tipoHoraS.setFont(new Font("Tahoma",1,11));
        add(tipoHoraS);
        
        tipoHoraS.addItem("AM");
        tipoHoraS.addItem("PM");
        tipoHoraS.setEnabled(false);
        */
        tipoHoraE = new JComboBox();
        tipoHoraE.setBounds(220,351,55,30);
        tipoHoraE.setFont(new Font("Tahoma",1,11));
        add(tipoHoraE);
        
        tipoHoraE.addItem("AM");
        tipoHoraE.addItem("PM");
        tipoHoraE.setEnabled(false);
        
        ///////
        labelHora = new JLabel("Dia(s)");
        labelHora.setBounds(233,409,50,30);
        labelHora.setFont(new Font("Dialog",1,10));
        add(labelHora);
        
        labelHora2 = new JLabel(" ");
        labelHora2.setBounds(283,409,50,30);
        labelHora2.setFont(new Font("Dialog",1,10));
        add(labelHora2);
        

        //hilos
        AsignarFecha af = new AsignarFecha(label_fecha);
        af.start();
        
        AsignarHora ah = new AsignarHora(label_hora);
        ah.start();
        
        
        he = new HoraEntrada(txt_entrada);
        he.start();
        eap = new EntradaAmPm(tipoHoraE);
        eap.start();
        
        /*
        hs = new HoraSalida(txt_salida);
        hs.start();
        hs.suspend();
        sap = new SalidaAmPm(tipoHoraS);
        sap.start();
        sap.suspend();
        */
        //estable el nombre de usuario ej fabio andres
        labelUsuario.setText(Usuarios.nombreUsuarioAlmacenado);
        
        //fecha_add.setDate(new Date());
        JTable_Disponibles.setDefaultRenderer(Object.class, new MiRender());
        mostrarDisponibleEnTabla();
        
        
        
    }
    public void mostrarDisponibleEnTabla(){
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DisponiblesPrepagado> lDisp = obtenerDisponibles();
        DefaultTableModel modelo = (DefaultTableModel) JTable_Disponibles.getModel();
        modelo.setRowCount(0);
        //JTable_Disponibles.setForeground(Color.red);
        Object[] fila = new Object[5];
        //
        
        /*
        Calendar cal = Calendar.getInstance();
        Date dHoy = cal.getTime();
        Date d1 = null;
        */
        
        for(int i=0; i<lDisp.size();i++){
            /*
            try {
                d1 = dateFormat.parse(lDisp.get(i).getHoraSalida());
            } catch (ParseException ex) {
                Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
            }
            int d1p = (int) d1.getTime();
            int dHoyp = (int) dHoy.getTime();
            if(d1p <= dHoyp){
                //JTable_Disponibles.setForeground(Color.red);
                //JTable_Disponibles.
            }else{
                JTable_Disponibles.setForeground(Color.black);
            }
            */
                fila[0] = lDisp.get(i).getId();
                fila[1] = lDisp.get(i).getPlaca();
                fila[2] = lDisp.get(i).getTipoVehiculo();
                fila[3] = lDisp.get(i).getHoraEntrada();
                fila[4] = lDisp.get(i).getHoraSalida();
                modelo.addRow(fila);
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_placa = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txt_modelo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_color = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_entrada = new javax.swing.JTextField();
        boton_generar = new javax.swing.JButton();
        boton_buscar = new javax.swing.JButton();
        botonRegistrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        botonCerrar = new javax.swing.JButton();
        label_status = new javax.swing.JLabel();
        txt_dias = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_numero = new javax.swing.JLabel();
        boton_limpiar = new javax.swing.JButton();
        label_fecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        label_hora = new javax.swing.JLabel();
        txt_minutos = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Disponibles = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        fecha_add = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        jLabel6.setText("Registro Prepagado");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/parkingCP.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel4.setText("ARQUEADERO");

        jLabel5.setText("Modelo:");

        jLabel8.setText("Color:");

        jLabel9.setText("Placa:");

        jLabel7.setText("Hora de Entrada:");

        txt_entrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_entrada.setEnabled(false);

        boton_generar.setText("Validar");
        boton_generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_generarActionPerformed(evt);
            }
        });

        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });

        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        jLabel11.setText("Valor a Pagar:");

        txt_valor.setBackground(new java.awt.Color(204, 204, 204));
        txt_valor.setEnabled(false);
        txt_valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valorActionPerformed(evt);
            }
        });

        jLabel12.setText("Tipo:");

        botonCerrar.setBackground(new java.awt.Color(183, 183, 183));
        botonCerrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cerrar.png"))); // NOI18N
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });

        txt_dias.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_dias.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel14.setText("PARQUEO NO.");

        txt_numero.setBackground(new java.awt.Color(204, 204, 204));
        txt_numero.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txt_numero.setForeground(new java.awt.Color(255, 0, 51));

        boton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/borrador.png"))); // NOI18N
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });

        label_fecha.setText(" ");
        label_fecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel15.setText("HORA:");

        label_hora.setText(" ");

        txt_minutos.setEnabled(false);
        txt_minutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_minutosActionPerformed(evt);
            }
        });

        txtBuscar.setCaretColor(new java.awt.Color(0, 0, 255));
        txtBuscar.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Usuario: ");

        labelUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelUsuario.setText("XXXXXXXXXXX");

        JTable_Disponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FACTURA #", "PLACA", "T. VEHICULO", "ENTRADA", "SALIDA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTable_Disponibles);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/camara.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cambio.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        fecha_add.setDateFormatString("yyyy-MM-dd");

        jLabel10.setText("Fecha de Salida:");

        jButton1.setText("$");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP2.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_generar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fecha_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_dias, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_minutos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)
                                .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(label_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(label_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelUsuario))))))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_fecha)
                                            .addComponent(label_hora))))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boton_buscar))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_status, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txt_dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_minutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonRegistrar)
                                    .addComponent(jButton1))
                                .addComponent(boton_generar))
                            .addComponent(boton_limpiar))
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valorActionPerformed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
               
        Usuarios usuarios = new Usuarios();
        usuarios.setBounds(0,0,583,376);
        usuarios.setVisible(true);
        usuarios.setResizable(false);
        usuarios.setLocationRelativeTo(null);
        
        this.setVisible(false);
    }//GEN-LAST:event_botonCerrarActionPerformed
    //boton registrar modificado
    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
    

    if(comboBox1.getSelectedItem().toString().equals("") || txt_placa.getText().trim().equals("") || fecha_add.getDate() == null 
            || !banderaCalcular) {
        JOptionPane.showMessageDialog(null,"Por favor ingresa los campos obligatorios y correctos");
        banderaCalcular = false;
        }
        else{
            //llena el resto de campos con sus valores respectivos
            //generarValor();
            String SQL_INSERT = "INSERT INTO parq (ID,Placa,TipoVehiculo,Modelo,Color,HoraEntrada,"
                    + "HoraSalida,Horas,ValorPagar,Estado,Responsable) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            fechaHora = dateFormat.format(date);

            //obtener de nuevo la fechaHoraAcua
            SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat spf2 = new SimpleDateFormat("YYYY-MM-dd");
            Calendar c =Calendar.getInstance();
            Date d = c.getTime();
            String horaDeHoy = spf.format(d);
            String fechaCliente = spf2.format(fecha_add.getDate());

            try{
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement(SQL_INSERT);

                pst.setString(1,"0");
                pst.setString(2,txt_placa.getText().trim());
                pst.setString(3,comboBox1.getSelectedItem().toString());
                pst.setString(4,txt_modelo.getText().trim());
                pst.setString(5,txt_color.getText().trim());
                pst.setString(6,fechaHora);
                //pst.setString(6,"0000-00-00 00:00:00");
                pst.setString(7,fechaCliente +" "+ horaDeHoy);
                int horas = Integer.parseInt(txt_dias.getText()) * 60;
                String horas2 = Integer.toString(horas);
                pst.setString(8,txt_dias.getText() + " dia(s) " + "[" +horas2+" horas]");
                pst.setString(9,txt_valor.getText());

                pst.setString(10,"Prepagado");

                pst.setString(11,labelUsuario.getText().trim());
                pst.executeUpdate();



            }catch(Exception e){

            }

            //actualizar la tabla


            mostrarDisponibleEnTabla();
            String idRecuperada ="";
            String HoraEntradaR ="";
            String HoraSalidaR = "";
            try{
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement("select * from parq where Placa like '%"+txt_placa.getText()+"%'"
                        +"AND HoraEntrada like '%"+fechaHora+"%'");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    idRecuperada = rs.getString("ID");
                    HoraEntradaR = rs.getString("HoraEntrada");
                    HoraSalidaR = rs.getString("HoraSalida");
                }
            }catch(Exception e){
            }

            //nueva factura PDF
                String hE = txt_entrada.getText()+ " "+tipoHoraE.getSelectedItem().toString();
                String hES = HoraEntradaR.substring(11,19);
                FacturaRegistro fr = new FacturaRegistro(idRecuperada,hES,HoraEntradaR.substring(0,10),hES,HoraSalidaR.substring(0,10),
                        comboBox1.getSelectedItem().toString(), txt_placa.getText(), 
                        txt_modelo.getText(), txt_valor.getText());
                fr.generarFactura();


                txt_placa.setText("");
                comboBox1.setSelectedItem("");
                txt_modelo.setText("");
                txt_color.setText("");
                txt_entrada.setText("");
                //txt_salida.setText("0");
                txt_dias.setText("0");
                txt_valor.setText("0");
                label_status.setText("Registro Exitoso");
                banderaCalcular = false;
        
    
    }
        
            
            
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        
        he.suspend();
        eap.suspend();
        //hs.resume();
        //sap.resume();
        //txt_entrada.setText("funciona");
        
        try{
            
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
            PreparedStatement pst = cn.prepareStatement("select * from parq where ID = ? AND Estado like '%Prepagado%'");
            pst.setString(1, txtBuscar.getText().trim());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                txt_numero.setText(rs.getString(1));
                txt_placa.setText(rs.getString("Placa"));
                comboBox1.setSelectedItem(rs.getString("TipoVehiculo"));
                txt_modelo.setText(rs.getString("Modelo"));
                txt_color.setText(rs.getString("Color"));
                //
                horaFechaE = rs.getString("HoraEntrada");
                
                //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");
                //----------------------------------------------------------------------
                
                //String horaAmPm = sdf.format(horaFechaE);
                String horaAmPm = Conversiones.convertirHora(horaFechaE);
                txt_entrada.setText(horaAmPm.substring(0,8));
                tipoHoraE.setSelectedItem(horaAmPm.substring(9,11));
                
                fecha_add.setDate(rs.getDate("horaSalida"));
                //System.out.println("**"+horaAmPm);
                //recordarFechaE recuerda la fecha de entrada para añadirla al pdf de reportes
                recordarFechaE = Conversiones.convertirFecha(horaFechaE);
                //
                //txt_entrada.setText(rs.getString("HoraEntrada").substring(0,5));
                //tipoHoraE.setSelectedItem(rs.getString("HoraEntrada").substring(6,8));
                //rs.getString("HoraEntrada").substring(6,8);
                //txt_salida.setText(rs.getString("HoraSalida"));
                banderaBuscar = true;
            }else{
                JOptionPane.showMessageDialog(null, "# de Parqueo no registrado.");
                he.resume();
                eap.resume();
                banderaBuscar = false;
                
                //hs.suspend();                
                //sap.suspend();
                //txt_salida.setText("0");
            }
        }catch(Exception e){}
        
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_generarActionPerformed
    if(banderaBuscar){    
        if(rutaFoto == null){

            //generarValor();

            //lo mismo q en el valor de entrada
            //DECIDO NO MODIFICAR FECHA ENTRADA Y HORA ENTRADA / CONSERVAR LOS DATOS INGRESADOS EN REGISTRAR
            try{
                //agregue la iomagen
                String cadena = "update parq set Estado = ?, Responsable = ? where ID = "; 

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement(cadena + txt_numero.getText());

                /*
                pst.setString(1,horaFechaS);


                pst.setString(2,txt_horas.getText().trim()+ " : " + txt_minutos.getText());
                pst.setString(3,txt_valor.getText().trim());
                */
                pst.setString(1,"Retirado");
                pst.setString(2,labelUsuario.getText());
                //pst.setString(8,label_numero.getText());
                
                pst.executeUpdate();

                label_status.setText("Datos guardados");

            }catch(Exception e){}
        }else{
            
            generarValor();

            //lo mismo q en el valor de entrada
            //DECIDO NO MODIFICAR FECHA ENTRADA Y HORA ENTRADA / CONSERVAR LOS DATOS INGRESADOS EN REGISTRAR
            try{
                String cadena = "update parq set Estado = ?, Responsable = ?, Imagen=?  where ID = "; 

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement(cadena + txt_numero.getText());

                //String ruta = System.getProperty("user.home");
                //String nombreImagen = recordarFechaE+"_"+txt_placa.getText();
                /*
                pst.setString(1,horaFechaS);


                pst.setString(2,txt_horas.getText().trim()+ " : " + txt_minutos.getText());
                pst.setString(3,txt_valor.getText().trim());
                */
                //pst.setString(4,"Retirado S.T.\n"+"file:///"+ruta+"\\Desktop\\tiquetes\\imagenes\\"+nombreImagen+".jpg");
                pst.setString(1,"Retirado S.T.");
                pst.setString(2,labelUsuario.getText());
                //pst.setString(8,label_numero.getText());
                //LA IMAGEN                
                //InputStream img = new FileInputStream(new File(rutaFoto));
                //pst.setBlob(6,img);
                pst.setString(3, rutaFoto);
                
                pst.executeUpdate();

                label_status.setText("Datos guardados");

            }catch(Exception e){}   
            rutaFoto = null;
        }
        mostrarDisponibleEnTabla();   
        ReportesDiarios rp = new ReportesDiarios(recordarFechaE); 
        rp.GenerarReporteComun();
        banderaBuscar = false;
    }else{
        JOptionPane.showMessageDialog(null, "Por favor busca el parqueo a validar");
        banderaBuscar = false;
    }
        /*
        //System.out.println("bF" + banderaFoto);
        //rutaFoto = "file:///C:/Users/JONATHAN/Desktop/cambio.png";
        if(rutaFoto == null){

            generarValor();

            //lo mismo q en el valor de entrada
            //DECIDO NO MODIFICAR FECHA ENTRADA Y HORA ENTRADA / CONSERVAR LOS DATOS INGRESADOS EN REGISTRAR
            try{
                //agregue la iomagen
                String cadena = "update parq set HoraSalida = ?, Horas = ?, ValorPagar = ?, Estado = ?, Responsable = ? where ID = "; 

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement(cadena + txt_numero.getText());


                pst.setString(1,horaFechaS);


                pst.setString(2,txt_horas.getText().trim()+ " : " + txt_minutos.getText());
                pst.setString(3,txt_valor.getText().trim());
                pst.setString(4,"Retirado");
                pst.setString(5,labelUsuario.getText());
                //pst.setString(8,label_numero.getText());
                
                pst.executeUpdate();

                label_status.setText("Datos guardados");

            }catch(Exception e){}
        }else{
            
            generarValor();

            //lo mismo q en el valor de entrada
            //DECIDO NO MODIFICAR FECHA ENTRADA Y HORA ENTRADA / CONSERVAR LOS DATOS INGRESADOS EN REGISTRAR
            try{
                String cadena = "update parq set HoraSalida = ?, Horas = ?, ValorPagar = ?, Estado = ?, Responsable = ?, Imagen=?  where ID = "; 

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement(cadena + txt_numero.getText());

                //String ruta = System.getProperty("user.home");
                //String nombreImagen = recordarFechaE+"_"+txt_placa.getText();
                pst.setString(1,horaFechaS);


                pst.setString(2,txt_horas.getText().trim()+ " : " + txt_minutos.getText());
                pst.setString(3,txt_valor.getText().trim());
                //pst.setString(4,"Retirado S.T.\n"+"file:///"+ruta+"\\Desktop\\tiquetes\\imagenes\\"+nombreImagen+".jpg");
                pst.setString(4,"Retirado S.T.");
                pst.setString(5,labelUsuario.getText());
                //pst.setString(8,label_numero.getText());
                //LA IMAGEN                
                //InputStream img = new FileInputStream(new File(rutaFoto));
                //pst.setBlob(6,img);
                pst.setString(6, rutaFoto);
                
                pst.executeUpdate();

                label_status.setText("Datos guardados");

            }catch(Exception e){}   
            rutaFoto = null;
        }
        
        //GENERAR EL ARCHIVO DE TEXTO
        String nombreCambiante = txt_numero.getText();
        String ruta = System.getProperty("user.home");
        File archivo1 = new File(ruta + "\\Desktop\\tiquetes\\tiqueteNo." + nombreCambiante+".txt");
        try{
            PrintWriter salida = new PrintWriter(new FileWriter(archivo1));
            salida.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        
        //IMPRIMIR LOS DATOS EN EL ARCHIVO 
        
        File archivo = new File(ruta +"\\Desktop\\tiquetes\\tiqueteNo." + nombreCambiante + ".txt");
        try{
            PrintWriter salida = new PrintWriter(new FileWriter(archivo,true));
            
            salida.println("*************************");
            salida.println("****PARQUEADERO DURAN****");
            salida.println("*************************");
            salida.println("**FECHA = " + label_fecha.getText()+"  ***");
            salida.println("*************************");
            salida.println("Tipo   = " + comboBox1.getSelectedItem().toString());
            salida.println("Placa  = " + txt_placa.getText());
            salida.println("Modelo = " + txt_modelo.getText());
            salida.println("Color  = " + txt_color.getText());
            salida.println("-------------------------");
            salida.println("Hora Entrada = " + txt_entrada.getText()+ " "+tipoHoraE.getSelectedItem().toString());
            salida.println("Hora Salida  = ");  //txt_salida.getText()+ " "+tipoHoraS.getSelectedItem().toString());
            salida.println("-------------------------");
            salida.println("Horas = " + txt_horas.getText()+ " : " + txt_minutos.getText());
            salida.println("-------------------------");            
            salida.println("VALOR A PAGAR = " + txt_valor.getText());    
            salida.close();
            
            //open(archivo);
            Runtime.getRuntime().exec("cmd /c start "+ "C:\\Users\\JONATHAN\\Desktop\\tiquetes\\tiqueteNo." + nombreCambiante + ".txt");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        /*   
        ReportesDiarios rp = new ReportesDiarios(recordarFechaE); 
        rp.GenerarReporteComun();
        */
         //texto_area="";
         
        
        
        
        /*
        //generar reportes automaticos en txt
        String cadena = "# PARQUEO : [ " + txt_numero.getText() + " ] " +"Placa: " + txt_placa.getText()+ " | " + "Tipo: " + comboBox1.getSelectedItem().toString() 
                    + " | Modelo: " + txt_modelo.getText() + " | Color: " + txt_color.getText() + " | Entrada: "
                    + txt_entrada.getText() + " | Hora Salida: " + txt_salida.getText() + " | Horas: " + txt_horas.getText()
                    + " | Valor a Pagar: " + txt_valor.getText();
        String nombreArchivo = "C:\\Users\\JONATHAN\\Desktop\\tiquetes\\reportes\\13-09-2018.txt";
        RegistroVehiculos rv = new RegistroVehiculosImpl();
        rv.agregarParqueo(nombreArchivo, cadena);
        rv.listarParqueo(nombreArchivo);
        //revisar los metodos de arriba despues
        */
        //////////////////////////////////////////////////////
        
        //actualizar la tabla
        /*
        mostrarDisponibleEnTabla();   
        ReportesDiarios rp = new ReportesDiarios(recordarFechaE); 
        rp.GenerarReporteComun();
        */
    }//GEN-LAST:event_boton_generarActionPerformed

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
          
                
             
                txt_placa.setText("");
                comboBox1.setSelectedItem("");
                txt_modelo.setText("");
                txt_color.setText("");
                //txt_entrada.setText("");
                //txt_salida.setText("0");
                txt_dias.setText("0");
                txt_valor.setText("0");
            
                txtBuscar.setText("");
                txt_numero.setText("");
                txt_minutos.setText("0");
                fecha_add.setDate(null);
                //HoraEntrada he2 = new HoraEntrada(txt_entrada);
                
                //EntradaAmPm eap2 = new EntradaAmPm(tipoHoraE);
                
                he.resume();
                eap.resume();
                banderaBuscar = false;
                
                //hs.suspend();
                //sap.suspend();
                //tipoHoraS.setSelectedItem("AM");
                
                //he = he2;
                //eap = eap2;
           
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void txt_minutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_minutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_minutosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String pImagen = recordarFechaE+"_"+txt_placa.getText()+txt_entrada.getText().replaceAll(":", "_");
        WebcamViewerExample wve = new WebcamViewerExample(pImagen);
        //wve.setBounds(0,0,400,400);
        wve.setVisible(true);
        //wve.setResizable(false);
        wve.run();
        //wve.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ParqueaderoRegistroUsuarios prp = new ParqueaderoRegistroUsuarios();
        prp.setBounds(0, 0, 800, 573);
        prp.setVisible(true);
        prp.setResizable(false);
        prp.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(fecha_add.getDate() != null){
            
            generarValor();
            banderaCalcular = true;
        }else{
            JOptionPane.showMessageDialog(null, "Ingresa la fecha a calcular");
            banderaCalcular = false;
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParqueaderoRegistroUsuarios2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Disponibles;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_generar;
    private javax.swing.JButton boton_limpiar;
    private com.toedter.calendar.JDateChooser fecha_add;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelUsuario;
    public javax.swing.JLabel label_fecha;
    private javax.swing.JLabel label_hora;
    private javax.swing.JLabel label_status;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txt_color;
    private javax.swing.JTextField txt_dias;
    private javax.swing.JTextField txt_entrada;
    private javax.swing.JTextField txt_minutos;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JLabel txt_numero;
    private javax.swing.JTextField txt_placa;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("interfaces/parqueadero/images/parkingCP.png"));
        return retValue;
    }
    
    public void generarValor(){
    double ValorAPagar = 0.0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //hora y fecha escogida por usuario
        
        SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat spf2 = new SimpleDateFormat("YYYY-MM-dd");
        Calendar c =Calendar.getInstance();
        Date d = c.getTime();
        String horaDeHoy = spf.format(d);
        String fechaCliente = spf2.format(fecha_add.getDate());
        Date de = null;
        System.out.println("--"+horaDeHoy+"**"+fechaCliente);
        try {
            de = dateFormat.parse(fechaCliente+" "+horaDeHoy);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
               
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaPHoy = label_fecha.getText();
        Date date= null;
        System.out.println("--"+horaDeHoy+"**"+fechaPHoy);
        try {
            //la hora tomo la de arriba
            date = dateFormat.parse(fechaPHoy+" "+ horaDeHoy);
        } catch (ParseException ex) {
            Logger.getLogger(ParqueaderoRegistroUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
        }

        //la operacion para determinar los minutos en el parqueadero
        
        int minutosEnTotal = (int) (de.getTime() - date.getTime())/60000;
        System.out.println("***"+minutosEnTotal);
    //******************************************************************************
    //VA LA CONVERSION DE HORA A MINUTOS
    //******************************************************************************
            int indicePrimario = 0;
            if(comboBox1.getSelectedItem().toString().equals("Carro")){
                indicePrimario = 1;
            }else if(comboBox1.getSelectedItem().toString().equals("Vehiculo Pesado")){
                indicePrimario = 2;
            }else if(comboBox1.getSelectedItem().toString().equals("Moto")){
                indicePrimario = 3;
            }
            
            PreciosJDBC preciosJDBC = new PreciosJDBC();
            Precio precioRec = preciosJDBC.select(indicePrimario);
            double vHoraNew = precioRec.getValor();
    
            double vMinutoNew = vHoraNew /60;
            BigDecimal bd = new BigDecimal(vMinutoNew);
            bd = bd.setScale(2, RoundingMode.CEILING);
            vMinutoNew = bd.doubleValue();
            //String vM = Double.toString(vMinutoNew); 
    
    //******************************************************************************
    if(comboBox1.getSelectedItem().toString().equals("Carro")){
        double valorPagar = minutosEnTotal * vMinutoNew;
        //--------------------------------
            BigDecimal b2 = new BigDecimal(valorPagar);
            b2 =b2.setScale(2, RoundingMode.DOWN);
            valorPagar = b2.doubleValue();
        //--------------------------------        
        String valorPagarCadena = Double.toString(valorPagar);
        txt_valor.setText(valorPagarCadena);
    }else if(comboBox1.getSelectedItem().toString().equals("Vehiculo Pesado")){
        double valorPagar = minutosEnTotal * vMinutoNew;
        //------------------------
            BigDecimal b3 = new BigDecimal(valorPagar);
            b3 = b3.setScale(2, RoundingMode.DOWN);
            valorPagar = b3.doubleValue();
        //------------------------
        String valorPagarCadena = Double.toString(valorPagar);
        txt_valor.setText(valorPagarCadena);
    }else if(comboBox1.getSelectedItem().toString().equals("Moto")){
        double valorPagar = minutosEnTotal * vMinutoNew;
        //------------------------------------
            BigDecimal b4 = new BigDecimal(valorPagar);
            b4 = b4.setScale(2, RoundingMode.DOWN);
            valorPagar = b4.doubleValue();
        //------------------------------------
        String valorPagarCadena = Double.toString(valorPagar);
        txt_valor.setText(valorPagarCadena);
    }
    ///////////////
    //OJO CONVERSION DE MINUTOS A HORA: MINUTOS
    int horaFinal = minutosEnTotal / 60 /24;
    
    String valorCadena = Integer.toString(horaFinal);
    txt_dias.setText(valorCadena);
    /*
    int minutosRestantes = minutosEnTotal - (horaFinal * 60);
    String valorCadena2 = Integer.toString(minutosRestantes);
    txt_minutos.setText(valorCadena2);
    */
    }
    
    public List<DisponiblesPrepagado> obtenerDisponibles(){
        DisponiblesPrepagado disponiblesPrepagado = null;
        List<DisponiblesPrepagado> listaDisponiblesPrepagado = new ArrayList();
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
            String filtrado = "select * from parq where Estado like '%"+"Prepagado"+"%'"; 
                    
            PreparedStatement pst = cn.prepareStatement(filtrado);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String pId = rs.getString("ID");
                String pPlaca = rs.getString(2);
                String pTV = rs.getString(3);
                String pHE = rs.getString("HoraEntrada");
                String pHS = rs.getString("HoraSalida");
                
                disponiblesPrepagado = new DisponiblesPrepagado();
                disponiblesPrepagado.setId(pId);
                disponiblesPrepagado.setPlaca(pPlaca);
                disponiblesPrepagado.setTipoVehiculo(pTV);
                disponiblesPrepagado.setHoraEntrada(pHE);
                disponiblesPrepagado.setHoraSalida(pHS);
                
                listaDisponiblesPrepagado.add(disponiblesPrepagado);
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return listaDisponiblesPrepagado;
    }
    /*
    public static void abrirRecibo(String archivo){
        try{
            File objFile = new File(archivo);
            
            //Desktop.getDesktop().open(objFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    */
    }
    
       

    

