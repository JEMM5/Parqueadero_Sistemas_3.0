package interfaces.parqueadero;
import java.util.ArrayList;
import java.util.List;
import com.parqueaderosistemas.beans.*;
import com.parqueaderosistemas.jdbc.*;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class TablaUsuarios extends javax.swing.JFrame {
    UsuariosJDBC usuariosJDBC;
    int id;
    String nombre;
    String usuario;
    String contrasena;
    String tipoUsuario;
    /**
     * Creates new form TablaUsuarios
     */
    public TablaUsuarios() {
        initComponents();
        this.setBounds(0,0,650,563);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        usuariosJDBC = new UsuariosJDBC();
        mostrarUsuariosEnTabla();
    }
    
    public void mostrarUsuariosEnTabla(){
        
        List<Usuario> lista = usuariosJDBC.select();
        DefaultTableModel modelo = (DefaultTableModel)JTable_Usuarios.getModel();
        //limpia
        modelo.setRowCount(0);
        Object[] fila = new Object[5];
        for(int i=0;i<lista.size();i++){
            fila[0] = lista.get(i).getId();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getUsuario();
            fila[3] = lista.get(i).getContrasena();
            fila[4] = lista.get(i).getTipoUsuario();
            modelo.addRow(fila);
        }
    }
    
    public void obtenerUsuario(int index){
        
        id = usuariosJDBC.select().get(index).getId();
        nombre = usuariosJDBC.select().get(index).getNombre();
        usuario = usuariosJDBC.select().get(index).getUsuario();
        contrasena = usuariosJDBC.select().get(index).getContrasena();
        tipoUsuario = usuariosJDBC.select().get(index).getTipoUsuario();
        
        //System.out.println("**"+id+nombre+usuario+contrasena+tipoUsuario);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Usuarios = new javax.swing.JTable();
        botonAnadir = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/parkingCP.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel4.setText("ARQUEADERO");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/parqueadero/images/cintaP.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        jLabel7.setText("Usuarios Registrados");

        JTable_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Usuario", "Contraseña", "Tipo de Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTable_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Usuarios);
        if (JTable_Usuarios.getColumnModel().getColumnCount() > 0) {
            JTable_Usuarios.getColumnModel().getColumn(0).setResizable(false);
        }

        botonAnadir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonAnadir.setText("Añadir");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonAtras.setText("<|");
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 645, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel7)))
                                .addGap(175, 175, 175))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botonAnadir)
                                .addGap(71, 71, 71)
                                .addComponent(botonModificar)
                                .addGap(64, 64, 64)
                                .addComponent(botonEliminar)
                                .addGap(116, 116, 116))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAtras)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnadir)
                    .addComponent(botonModificar)
                    .addComponent(botonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAtras)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTable_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_UsuariosMouseClicked
        int indice = JTable_Usuarios.getSelectedRow();
        
        obtenerUsuario(indice);
    }//GEN-LAST:event_JTable_UsuariosMouseClicked

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        ///////////////////////
        Usuario us = new Usuario();
        us.setId(id);
        us.setNombre(nombre);
        us.setUsuario(usuario);
        us.setContrasena(contrasena);
        us.setTipoUsuario(tipoUsuario);
        
        RegUsuarios ru = new RegUsuarios();
        ru.recibirParModi(us);        
        
        ru.setBounds(0,0,532,480);
        ru.setVisible(true);
        ru.setResizable(false);
        ru.setLocationRelativeTo(null);
        
        this.setVisible(false);
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        RegUsuarios ru = new RegUsuarios();
        ru.setBounds(0,0,532,480);
        ru.setVisible(true);
        ru.setResizable(false);
        ru.setLocationRelativeTo(null);
        
        this.setVisible(false);
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        usuariosJDBC.delete(id);
        JOptionPane.showMessageDialog(null,"Usuario Eliminado");
        mostrarUsuariosEnTabla();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        parqueaderoBienvenida pb = new parqueaderoBienvenida();
        pb.setBounds(0,0,598,313);
        pb.setVisible(true);
        pb.setResizable(false);
        pb.setLocationRelativeTo(null);
        
        this.setVisible(false);
    }//GEN-LAST:event_botonAtrasActionPerformed

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
            java.util.logging.Logger.getLogger(TablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Usuarios;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonAtras;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("interfaces/parqueadero/images/parkingC.png"));
        return retValue;
    }
}
