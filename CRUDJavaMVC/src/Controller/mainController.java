/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.View;

/**
 *
 * @author David Ferrer
 */
public class mainController implements ActionListener {

    ClienteDAO dao = new ClienteDAO();
    Cliente cliente = new Cliente();
    View view = new View();
    DefaultTableModel model = new DefaultTableModel();

    public mainController(View v) {
        this.view = v;
        this.view.listarBtn.addActionListener(this);
        this.view.guardarBtn.addActionListener(this);
        this.view.editarBtn.addActionListener(this);
        this.view.OKBtn.addActionListener(this);
        this.view.elminarBtn.addActionListener(this);
        listar(view.listaTable);
    }

    public void listar(JTable listaTable) {
        model = (DefaultTableModel) listaTable.getModel();
        ArrayList<Cliente> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getName();
            object[2] = lista.get(i).getNumberPhone();
            object[3] = lista.get(i).getAddress();
            model.addRow(object);
        }
        view.listaTable.setModel(model);
    }

    public void agregar() {
        String name = view.clientNameTxfld.getText();
        String numberPhone = view.clientNumberPhoneTxfld.getText();
        String address = view.clientAddressTxfld.getText();
        cliente.setName(name);;
        cliente.setNumberPhone(numberPhone);
        cliente.setAddress(address);
        int r = dao.Agregar(cliente);
        if (name.equals("") || numberPhone.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        } else {
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Usuario Agregado con exito...");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar agregar el usuario...");
            }
        }
    }

    public void Actualizar() {
        int id = Integer.parseInt(view.clientIDTxfld.getText());
        String name = view.clientNameTxfld.getText();
        String numberPhone = view.clientNumberPhoneTxfld.getText();
        String address = view.clientAddressTxfld.getText();
        cliente.setId(id);
        cliente.setName(name);;
        cliente.setNumberPhone(numberPhone);
        cliente.setAddress(address);
        int r = dao.Actualizar(cliente);
        if (r == 1) {
            JOptionPane.showMessageDialog(null, "Usuario Actualizado...");
        } else {
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar el usuario...");
        }
    }

    void limpiarRegistros() {
        for (int i = 0; i < view.listaTable.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void eliminar(){
        int row = view.listaTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            } else {
                int id = Integer.parseInt((String) view.listaTable.getValueAt(row, 0).toString());
                dao.eliminar(row);
                JOptionPane.showMessageDialog(null, "Usuario elmininado");
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.listarBtn) {
            limpiarRegistros();
            listar(view.listaTable);
        }
        if (e.getSource() == view.guardarBtn) {
            agregar();
            limpiarRegistros();
            listar(view.listaTable);
        }
        if (e.getSource() == view.editarBtn) {
            int row = view.listaTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt((String) view.listaTable.getValueAt(row, 0).toString());
                String name = (String) view.listaTable.getValueAt(row, 1);
                String numberPhone = (String) view.listaTable.getValueAt(row, 2);
                String address = (String) view.listaTable.getValueAt(row, 3);
                view.clientIDTxfld.setText("" + id);
                view.clientNameTxfld.setText(name);
                view.clientNumberPhoneTxfld.setText(numberPhone);
                view.clientAddressTxfld.setText(address);
            }
        }
        if (e.getSource() == view.OKBtn) {
            Actualizar();
            limpiarRegistros();
            listar(view.listaTable);
        }
        if (e.getSource() == view.elminarBtn) {
            eliminar();
            limpiarRegistros();
            listar(view.listaTable);
        }
    }

}
