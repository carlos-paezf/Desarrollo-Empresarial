/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ferrer
 */
public class Main extends javax.swing.JFrame {

    Conexion connnection = new Conexion();
    Connection conn;
    Statement statement;
    ResultSet resultSet;
    DefaultTableModel model;
    int id;

    public Main() throws SQLException {
        initComponents();
        setLocationRelativeTo(null); //Centrar la aplicación
        listar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tittleLBL = new javax.swing.JLabel();
        datosPnl = new javax.swing.JPanel();
        addressClient = new javax.swing.JLabel();
        numberPhoneClient = new javax.swing.JLabel();
        clientIDTxfld = new javax.swing.JTextField();
        clientNumberPhoneTxfld = new javax.swing.JTextField();
        clientNameTxfld = new javax.swing.JTextField();
        clientAddressTxfld = new javax.swing.JTextField();
        clientID = new javax.swing.JLabel();
        nameClient = new javax.swing.JLabel();
        datosLbl = new javax.swing.JLabel();
        operacionesPnl = new javax.swing.JPanel();
        accionesLbl = new javax.swing.JLabel();
        agregarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        nuevoBtn = new javax.swing.JButton();
        listaPnl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientListTable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tittleLBL.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tittleLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittleLBL.setText("CRUD - JAVA");

        datosPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        addressClient.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addressClient.setText("Dirección Cliente:");

        numberPhoneClient.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        numberPhoneClient.setText("Numero Celular Cliente:");

        clientIDTxfld.setEditable(false);
        clientIDTxfld.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clientIDTxfld.setToolTipText("ID Client");
        clientIDTxfld.setEnabled(false);
        clientIDTxfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientIDTxfldActionPerformed(evt);
            }
        });

        clientNumberPhoneTxfld.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clientNumberPhoneTxfld.setToolTipText("Number Phone client");
        clientNumberPhoneTxfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientNumberPhoneTxfldActionPerformed(evt);
            }
        });

        clientNameTxfld.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clientNameTxfld.setToolTipText("Name client");

        clientAddressTxfld.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        clientAddressTxfld.setToolTipText("Address client");

        clientID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        clientID.setText("ID Cliente:");

        nameClient.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nameClient.setText("Nombre Cliente:");

        datosLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        datosLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        datosLbl.setText("Formulario de Datos");

        javax.swing.GroupLayout datosPnlLayout = new javax.swing.GroupLayout(datosPnl);
        datosPnl.setLayout(datosPnlLayout);
        datosPnlLayout.setHorizontalGroup(
            datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosPnlLayout.createSequentialGroup()
                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(datosPnlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datosLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(datosPnlLayout.createSequentialGroup()
                        .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(datosPnlLayout.createSequentialGroup()
                                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(datosPnlLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(numberPhoneClient))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosPnlLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameClient, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(clientID, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clientNumberPhoneTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clientNameTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clientIDTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(datosPnlLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(addressClient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clientAddressTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        datosPnlLayout.setVerticalGroup(
            datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datosLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientIDTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientID))
                .addGap(18, 18, 18)
                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientNameTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameClient))
                .addGap(18, 18, 18)
                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientNumberPhoneTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberPhoneClient))
                .addGap(18, 18, 18)
                .addGroup(datosPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientAddressTxfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressClient))
                .addGap(18, 18, 18))
        );

        operacionesPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        accionesLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        accionesLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accionesLbl.setText("Acciones");

        agregarBtn.setText("AGREGAR");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        modificarBtn.setText("MODIFICAR");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("ELIMINAR");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        nuevoBtn.setText("NUEVO");
        nuevoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout operacionesPnlLayout = new javax.swing.GroupLayout(operacionesPnl);
        operacionesPnl.setLayout(operacionesPnlLayout);
        operacionesPnlLayout.setHorizontalGroup(
            operacionesPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operacionesPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operacionesPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(operacionesPnlLayout.createSequentialGroup()
                        .addComponent(accionesLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(operacionesPnlLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(agregarBtn)
                        .addGap(26, 26, 26)
                        .addComponent(modificarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(eliminarBtn)
                        .addGap(26, 26, 26)
                        .addComponent(nuevoBtn)
                        .addGap(19, 19, 19))))
        );
        operacionesPnlLayout.setVerticalGroup(
            operacionesPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operacionesPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accionesLbl)
                .addGap(18, 18, 18)
                .addGroup(operacionesPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBtn)
                    .addComponent(modificarBtn)
                    .addComponent(eliminarBtn)
                    .addComponent(nuevoBtn))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        listaPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        clientListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Numero_Celular", "Direccion"
            }
        ));
        clientListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientListTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(clientListTable);
        if (clientListTable.getColumnModel().getColumnCount() > 0) {
            clientListTable.getColumnModel().getColumn(0).setMinWidth(50);
            clientListTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            clientListTable.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout listaPnlLayout = new javax.swing.GroupLayout(listaPnl);
        listaPnl.setLayout(listaPnlLayout);
        listaPnlLayout.setHorizontalGroup(
            listaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        listaPnlLayout.setVerticalGroup(
            listaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listaPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datosPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tittleLBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(operacionesPnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tittleLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datosPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(operacionesPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listaPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientIDTxfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientIDTxfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientIDTxfldActionPerformed

    private void clientNumberPhoneTxfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientNumberPhoneTxfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientNumberPhoneTxfldActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        eliminar();
        try {
            listar();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevo();
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        agregar(); 
        try {
            listar();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevo();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void clientListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientListTableMouseClicked
        int row = clientListTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
        } else {
            id = Integer.parseInt((String)clientListTable.getValueAt(row, 0).toString());
            String name = (String)clientListTable.getValueAt(row, 1);
            String numberPhone = (String)clientListTable.getValueAt(row, 2);
            String address = (String)clientListTable.getValueAt(row, 3);
            clientIDTxfld.setText(""+id);
            clientNameTxfld.setText(name);
            clientNumberPhoneTxfld.setText(numberPhone);
            clientAddressTxfld.setText(address);
        }
    }//GEN-LAST:event_clientListTableMouseClicked

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        modificar();
        try {
            listar();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevo();
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void nuevoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBtnActionPerformed
        nuevo();
    }//GEN-LAST:event_nuevoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void listar() throws SQLException {
        String sql = "SELECT * FROM clientes";
        try {
            conn = connnection.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            Object[] client = new Object[4]; //Numero de columnas de la tabla 
            model = (DefaultTableModel) clientListTable.getModel();
            while (resultSet.next()) {
                client[0] = resultSet.getInt("ID");
                client[1] = resultSet.getString("Nombre");
                client[2] = resultSet.getString("Numero_Celular");
                client[3] = resultSet.getString("Direccion");
                model.addRow(client); //Se agregan todas las filas a la tabla modelo
            }
            clientListTable.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los clientes "+ e.getMessage());
        }
    }

    public void agregar() {
        String name = clientNameTxfld.getText();
        String numberPhone = clientNumberPhoneTxfld.getText();
        String address = clientAddressTxfld.getText();
        if (name.equals("") || numberPhone.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos de texto estan en blanco...");
        } else {
            String sql = "INSERT INTO clientes (Nombre,Numero_Celular,Direccion) VALUES ('"+name+"','"+numberPhone+"','"+address+"')";
            try {
                conn = connnection.getConnection();
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario Agregado con exito....");
                limpiarRegistros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al agregar "+ e.getMessage());
            }
        }
    }
    
    public void limpiarRegistros(){
        for(int i=0; i<=clientListTable.getRowCount(); i++){
            model.removeRow(i);
            i=i-1;
        }
    }
    
    public void modificar(){
        String name = clientNameTxfld.getText();
        String numberPhone = clientNumberPhoneTxfld.getText();
        String address = clientAddressTxfld.getText();
        String sql = "UPDATE clientes SET Nombre='"+name+"', Numero_Celular='"+numberPhone+"', Direccion='"+address+"' WHERE ID="+id+" ";
        if (name.equals("") || numberPhone.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos de texto estan en blanco...");
        } else {
            try {
                conn =  connnection.getConnection();
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario Actualizado...");
                limpiarRegistros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el usuario "+ e.getMessage());
            }
        }
    }
    
    public void eliminar(){
        int seleccionado = clientListTable.getSelectedRow();
        if(seleccionado == -1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila...");
        } else {
            String sql = "DELETE FROM clientes WHERE ID="+id+" ";
            try {
                conn = connnection.getConnection();
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "El usuario fue eliminado");
                limpiarRegistros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario "+ e.getMessage());
            }
        }
    }
    
    public void nuevo(){
        clientID.setText("");
        clientNameTxfld.setText("");
        clientNumberPhoneTxfld.setText("");
        clientAddressTxfld.setText("");
        clientNameTxfld.requestFocus(); //EL puntero del mouse se ubica en dicha caja de texto
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accionesLbl;
    private javax.swing.JLabel addressClient;
    private javax.swing.JButton agregarBtn;
    private javax.swing.JTextField clientAddressTxfld;
    private javax.swing.JLabel clientID;
    private javax.swing.JTextField clientIDTxfld;
    private javax.swing.JTable clientListTable;
    private javax.swing.JTextField clientNameTxfld;
    private javax.swing.JTextField clientNumberPhoneTxfld;
    private javax.swing.JLabel datosLbl;
    private javax.swing.JPanel datosPnl;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel listaPnl;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel nameClient;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JLabel numberPhoneClient;
    private javax.swing.JPanel operacionesPnl;
    private javax.swing.JLabel tittleLBL;
    // End of variables declaration//GEN-END:variables
}