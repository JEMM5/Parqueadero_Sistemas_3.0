package interfaces.parqueadero;
import com.parqueaderosistemas.beans.Disponibles;
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
import camara.parqueadero.WebcamViewerExample1;
import pdf.factura.parqueadero.FacturaRegistro;
import pdf.factura.parqueadero.recibo;
//import com.parqueaderosistemas.usuarios.Usuarios;
//////////la lista
/**
 *
 * @author JONATHAN
 */
public class ParqueaderoRegistroUsuarios extends javax.swing.JFrame {
    //fototomada
    public static String rutaFoto = null;
    public boolean banderaBuscar = false;

    //fechaHora va en el registro y nos ayuda a obtener la id en el metodo
    String fechaHora ="";
    String horaFechaE ="";
    String horaFechaS ="";
    HoraEntrada he;
    EntradaAmPm eap;
    
    HoraSalida hs;
    SalidaAmPm sap;    
    
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
    private JComboBox tipoHoraS;
    
    public JLabel labelHora;
    public JLabel labelHora2;

    public ParqueaderoRegistroUsuarios() {
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
        txt_salida.setText("0");
        txt_horas.setText("0");
        txt_valor.setText("0");
        //
        tipoHoraS = new JComboBox();
        tipoHoraS.setBounds(220,398,55,30);
        tipoHoraS.setFont(new Font("Tahoma",1,11));
        add(tipoHoraS);
        
        tipoHoraS.addItem("AM");
        tipoHoraS.addItem("PM");
        tipoHoraS.setEnabled(false);
        
        tipoHoraE = new JComboBox();
        tipoHoraE.setBounds(220,358,55,30);
        tipoHoraE.setFont(new Font("Tahoma",1,11));
        add(tipoHoraE);
        
        tipoHoraE.addItem("AM");
        tipoHoraE.addItem("PM");
        tipoHoraE.setEnabled(false);
        
        ///////
        labelHora = new JLabel("Hora");
        labelHora.setBounds(218,417,50,30);
        labelHora.setFont(new Font("Dialog",1,10));
        add(labelHora);
        
        labelHora2 = new JLabel("Min.");
        labelHora2.setBounds(281,417,50,30);
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
        
        
        hs = new HoraSalida(txt_salida);
        hs.start();
        hs.suspend();
        sap = new SalidaAmPm(tipoHoraS);
        sap.start();
        sap.suspend();
        //estable el nombre de usuario ej fabio andres
        labelUsuario.setText(Usuarios.nombreUsuarioAlmacenado);
        
        String tipoUsuario = Usuarios.tipoUsuarioEnviar;
        System.out.println(tipoUsuario);
        
        if(tipoUsuario.equals("Super Usuario")){
            boton_atras.setEnabled(true);
        }else if(tipoUsuario.equals("Usuario")){
            boton_atras.setEnabled(false);
        }
        
        mostrarDisponibleEnTabla();
        
        
        
    }
    public void mostrarDisponibleEnTabla(){
        List<Disponibles> lDisp = obtenerDisponibles();
        DefaultTableModel modelo = (DefaultTableModel) JTable_Disponibles.getModel();
        modelo.setRowCount(0);
        Object[] fila = new Object[4];
        for(int i=0; i<lDisp.size();i++){
            fila[0] = lDisp.get(i).getId();
            fila[1] = lDisp.get(i).getPlaca();
            fila[2] = lDisp.get(i).getTipoVehiculo();
            fila[3] = lDisp.get(i).getHoraEntrada();
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

        jLabel1 = new javax.swing.JLabel();
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
        jLabel10 = new javax.swing.JLabel();
        txt_salida = new javax.swing.JTextField();
        boton_generar = new javax.swing.JButton();
        boton_buscar = new javax.swing.JButton();
        botonRegistrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        botonCerrar = new javax.swing.JButton();
        label_status = new javax.swing.JLabel();
        txt_horas = new javax.swing.JTextField();
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
        jLabel16 = new javax.swing.JLabel();
        boton_atras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP2.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        jLabel6.setText("Registro");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/parkingCP.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel4.setText("ARQUEADERO");

        jLabel5.setText("Modelo:");

        jLabel8.setText("Color:");

        jLabel9.setText("Placa:");

        jLabel7.setText("Hora de Entrada:");

        txt_entrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_entrada.setEnabled(false);

        jLabel10.setText("Hora de Salida:");

        txt_salida.setBackground(new java.awt.Color(204, 204, 204));
        txt_salida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_salida.setEnabled(false);
        txt_salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salidaActionPerformed(evt);
            }
        });

        boton_generar.setText("Generar ticket");
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

        txt_horas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_horas.setEnabled(false);

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
                "FACTURA #", "PLACA", "T. VEHICULO", "ENTRADA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cambio2.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP2.png"))); // NOI18N

        boton_atras.setText("<|");
        boton_atras.setEnabled(false);
        boton_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_generar)
                                .addGap(18, 18, 18)
                                .addComponent(boton_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33)
                                .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(txt_horas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txt_minutos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(label_status, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(boton_atras))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(138, 138, 138)
                                        .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(label_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(label_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6)
                            .addComponent(labelUsuario)))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_fecha)
                                    .addComponent(label_hora))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(boton_buscar)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_status, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_atras)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(txt_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11))
                            .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_horas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_minutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(botonRegistrar)
                                .addComponent(boton_generar))
                            .addComponent(boton_limpiar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
    if(comboBox1.getSelectedItem().toString().equals("") || txt_placa.getText().trim().equals("")){
        JOptionPane.showMessageDialog(null,"Por favor ingresa los campos obligatorios.");
    }else{    
        
        String SQL_INSERT = "INSERT INTO parq (ID,Placa,TipoVehiculo,Modelo,Color,HoraEntrada,"
                + "HoraSalida,Horas,ValorPagar,Estado,Responsable) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        fechaHora = dateFormat.format(date);
        
        
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
            pst.setString(7,"0000-00-00 00:00:00");
            pst.setString(8,"0");
            pst.setString(9,"0");
            //pst.setString(7, txt_entrada.getText().trim() + " " + tipoHoraE.getSelectedItem().toString());
            //System.out.println("**" + cadenaHoraEntrada.trim() + " "+tipoHoraE.getSelectedItem().toString());
            
            pst.setString(10,"Disponible");
            //pst.setString(9,txt_salida.getText().trim() + " "+tipoHoraS.getSelectedItem().toString());
            //
            //pst.setString(10,txt_horas.getText().trim());
            //pst.setString(11,txt_valor.getText().trim());
            pst.setString(11,labelUsuario.getText().trim());
            pst.executeUpdate();
            
            
            
        }catch(Exception e){

        }
        //
            //CREARCION DEL RECIBO
            
            //recibo r = new recibo();
            
            String idRecuperada ="";
            String HoraEntradaR ="";            
            try{
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
                PreparedStatement pst = cn.prepareStatement("select * from parq where Placa like '%"+txt_placa.getText()+"%'"
                        +"AND HoraEntrada like '%"+fechaHora+"%'");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    idRecuperada = rs.getString("ID");
                    HoraEntradaR = rs.getString("HoraEntrada");                    
                }
            }catch(SQLException e){}
            
            String hE = txt_entrada.getText()+ " "+tipoHoraE.getSelectedItem().toString();
                String hEX = HoraEntradaR.substring(0,11);
                recibo r = new recibo(idRecuperada,hE,hEX,
                        comboBox1.getSelectedItem().toString(), txt_placa.getText(), 
                        txt_modelo.getText());
                r.generarRecibo();
                
            txt_placa.setText("");
            comboBox1.setSelectedItem("");
            txt_modelo.setText("");
            txt_color.setText("");
            txt_entrada.setText("");
            txt_salida.setText("0");
            txt_horas.setText("0");
            txt_valor.setText("0");
            label_status.setText("Registro Exitoso");
    }
        //actualizar la tabla
        mostrarDisponibleEnTabla();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        
        he.suspend();
        eap.suspend();
        hs.resume();
        sap.resume();
        //txt_entrada.setText("funciona");
        
        try{
            
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
            PreparedStatement pst = cn.prepareStatement("select * from parq where ID = ? AND Estado like '%Disponible%'");
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
                
                hs.suspend();                
                sap.suspend();
                txt_salida.setText("0");
                banderaBuscar = false;
            }
        }catch(Exception e){}
        
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_generarActionPerformed
        //System.out.println("**"+rutaFoto);
        if(!banderaBuscar){
            JOptionPane.showMessageDialog(null,"Por favor busca el parqueo a Generar");
        }else{    
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
                //nueva factura PDF
                String hE = txt_entrada.getText()+ " "+tipoHoraE.getSelectedItem().toString();
                String hS = txt_salida.getText()+ " "+tipoHoraS.getSelectedItem().toString();
                FacturaRegistro fr = new FacturaRegistro(txt_numero.getText(),hE,recordarFechaE,hS,label_fecha.getText(),
                        comboBox1.getSelectedItem().toString(), txt_placa.getText(), 
                        txt_modelo.getText(), txt_valor.getText());
                fr.generarFactura();



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
            mostrarDisponibleEnTabla();   
            ReportesDiarios rp = new ReportesDiarios(recordarFechaE); 
            rp.GenerarReporteComun();
        }
    }//GEN-LAST:event_boton_generarActionPerformed

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
          
                
             
                txt_placa.setText("");
                comboBox1.setSelectedItem("");
                txt_modelo.setText("");
                txt_color.setText("");
                //txt_entrada.setText("");
                txt_salida.setText("0");
                txt_horas.setText("0");
                txt_valor.setText("0");
            
                txtBuscar.setText("");
                txt_numero.setText("");
                txt_minutos.setText("0");
                //HoraEntrada he2 = new HoraEntrada(txt_entrada);
                
                //EntradaAmPm eap2 = new EntradaAmPm(tipoHoraE);
                
                he.resume();
                eap.resume();
                
                hs.suspend();
                sap.suspend();
                tipoHoraS.setSelectedItem("AM");
                
                //he = he2;
                //eap = eap2;
           
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void txt_minutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_minutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_minutosActionPerformed

    private void txt_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_salidaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String pImagen = recordarFechaE+"_"+txt_placa.getText()+txt_entrada.getText().replaceAll(":", "_");
        WebcamViewerExample1 wve = new WebcamViewerExample1(pImagen);
        //wve.setBounds(0,0,400,400);
        wve.setVisible(true);
        //wve.setResizable(false);
        wve.run();
        //wve.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ParqueaderoRegistroUsuarios2 pru = new ParqueaderoRegistroUsuarios2();
        pru.setBounds(0, 0, 800, 573);
        pru.setVisible(true);
        pru.setResizable(false);
        pru.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void boton_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_atrasActionPerformed
        parqueaderoBienvenida pb = new parqueaderoBienvenida();
        pb.setBounds(0,0,598,313);
        pb.setVisible(true);
        pb.setResizable(false);
        pb.setLocationRelativeTo(null);
        
        
        this.setVisible(false);
    }//GEN-LAST:event_boton_atrasActionPerformed

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
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParqueaderoRegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParqueaderoRegistroUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Disponibles;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton boton_atras;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_generar;
    private javax.swing.JButton boton_limpiar;
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
    private javax.swing.JTextField txt_entrada;
    private javax.swing.JTextField txt_horas;
    private javax.swing.JTextField txt_minutos;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JLabel txt_numero;
    private javax.swing.JTextField txt_placa;
    private javax.swing.JTextField txt_salida;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("interfaces/parqueadero/images/parkingCP.png"));
        return retValue;
    }
    
    public void generarValor(){
    /*
    //las horas
    int hora = 0;
    int hora_ent = 0;
    int hora_sal = 0;
    
    //valor de la hora a minutos
    int minutosH = 0;
    
    //los minutos
    int minuto = 0;
    int minuto_ent = 0;
    int minuto_sal = 0;
    
    hora_ent = Integer.parseInt(txt_entrada.getText().substring(0,2));
    hora_sal = Integer.parseInt(txt_salida.getText().substring(0,2));
    
    //LA CONVERSION DE HORA MILITAR A HORA AM Y PM. 
    int horaEnt = 0;
    int horaSal = 0;
    
    if(tipoHoraE.getSelectedItem().toString().equals("AM")){
        horaEnt = hora_ent;
        if(hora_ent ==12){
            horaEnt = 0;
        }
    
    }else if(tipoHoraE.getSelectedItem().toString().equals("PM")){
        if(hora_ent == 1){
            horaEnt = 13;
        }else if(hora_ent == 2){
            horaEnt = 14;
        }else if(hora_ent == 3){
            horaEnt = 15;
        }else if(hora_ent == 4){
            horaEnt = 16;
        }else if(hora_ent == 5){
            horaEnt = 17;
        }else if(hora_ent == 6){
            horaEnt = 18;
        }else if(hora_ent == 7){
            horaEnt = 19;
        }else if(hora_ent == 8){
            horaEnt = 20;
        }else if(hora_ent == 9){
            horaEnt = 21;
        }else if(hora_ent == 10){
            horaEnt = 22;
        }else if(hora_ent == 11){
            horaEnt = 23;
        }else if(hora_ent == 12){
            horaEnt = 12;
        }
    }
    
    //la de la salida
    if(tipoHoraS.getSelectedItem().toString().equals("AM")){
        horaSal = hora_sal;
        if(hora_sal ==12){
            horaSal = 0;
        }
    }else if(tipoHoraS.getSelectedItem().toString().equals("PM")){
        if(hora_sal == 1){
            horaSal = 13;
        }else if(hora_sal == 2){
            horaSal = 14;
        }else if(hora_sal == 3){
            horaSal = 15;
        }else if(hora_sal == 4){
            horaSal = 16;
        }else if(hora_sal == 5){
            horaSal = 17;
        }else if(hora_sal == 6){
            horaSal = 18;
        }else if(hora_sal == 7){
            horaSal = 19;
        }else if(hora_sal == 8){
            horaSal = 20;
        }else if(hora_sal == 9){
            horaSal = 21;
        }else if(hora_sal == 10){
            horaSal = 22;
        }else if(hora_sal == 11){
            horaSal = 23;
        }else if(hora_sal == 12){
            horaSal = 12;
        }
    }
    hora_sal = horaSal;
    hora_ent = horaEnt;
    
    ///
    
    minuto_ent = Integer.parseInt(txt_entrada.getText().substring(3,5));
    minuto_sal = Integer.parseInt(txt_salida.getText().substring(3,5));
        //System.out.println("**"+recordarFechaE+"--"+label_fecha.getText());
        //**********************************************************************
        //HALLAR SI EL PARQUEO ES MAYOR A 24 HORAS
        //**********************************************************************
    
        if(hora_ent < hora_sal){
            hora = hora_sal - hora_ent;

        }else if(hora_ent > hora_sal){
            hora = 24 - hora_ent + hora_sal; 

        }else if(hora_ent == hora_sal){
            if(minuto_ent == minuto_sal){
                hora = 0;

            }else{
                hora = 0;
            }
        }
        //
        if(!recordarFechaE.equals(label_fecha.getText()) && hora_ent == hora_sal){
            hora = 24;
        }

        minutosH = hora * 60; 



        if(minuto_ent < minuto_sal){
            minuto = minuto_sal - minuto_ent;

        }else if(minuto_ent > minuto_sal && hora_ent != hora_sal){
            minuto = minuto_sal - minuto_ent;   
        }else if(minuto_ent == minuto_sal){
            minuto = 0;
        }
        
        if(!recordarFechaE.equals(label_fecha.getText()) && minuto_ent > minuto_sal && hora_ent == hora_sal){
            minuto = minuto_sal - minuto_ent;  
    
        }
    
        //la misma hora dia diferente
        if(!recordarFechaE.equals(label_fecha.getText()) && hora_ent == hora_sal && minuto_ent == minuto_sal){
            minutosH = 24*60;
            minuto =0;
        }
        
        int minutosEnTotal = minutosH + minuto;
        */
    /////////////////////NUEVA FORMA
        double ValorAPagar = 0.0;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String fechaHora = dateFormat.format(date);
        //lo que recibo
        Date horasalida = null;
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
            PreparedStatement pst = cn.prepareStatement("SELECT HoraEntrada FROM parq WHERE ID=" +txt_numero.getText());
            ResultSet rs = pst.executeQuery();
            rs.first();
            String horaSalida = rs.getString(1);
            horasalida = dateFormat.parse(horaSalida);
            //
            //txt_salida.setText(fechaHora);
            horaFechaS = fechaHora;
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(ParqueaderoRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        //la operacion para determinar los minutos en el parqueadero
        //la operacion para determinar los minutos en el parqueadero
        //la operacion para determinar los minutos en el parqueadero
        //la operacion para determinar los minutos en el parqueadero
        
        int minutosEnTotal = (int) (date.getTime() - horasalida.getTime())/60000;
        //System.out.println("**" + minutosEnTotal);
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
    int horaFinal = minutosEnTotal / 60;
    
    String valorCadena = Integer.toString(horaFinal);
    txt_horas.setText(valorCadena);
    
    int minutosRestantes = minutosEnTotal - (horaFinal * 60);
    String valorCadena2 = Integer.toString(minutosRestantes);
    txt_minutos.setText(valorCadena2);
    }
    
    public List<Disponibles> obtenerDisponibles(){
        Disponibles disponibles = null;
        List<Disponibles> listaDisponibles = new ArrayList();
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","");
            String filtrado = "select * from parq where Estado like '%"+"Disponible"+"%'"; 
                    
            PreparedStatement pst = cn.prepareStatement(filtrado);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String pId = rs.getString("ID");
                String pPlaca = rs.getString(2);
                String pTV = rs.getString(3);
                String pHE = rs.getString("HoraEntrada");
                
                disponibles = new Disponibles();
                disponibles.setId(pId);
                disponibles.setPlaca(pPlaca);
                disponibles.setTipoVehiculo(pTV);
                disponibles.setHoraEntrada(pHE);
                
                listaDisponibles.add(disponibles);
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return listaDisponibles;
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
    
       

    

