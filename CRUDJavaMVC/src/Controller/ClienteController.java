/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.ClienteDAO;
import View.ClientesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ferrer
 */
public class ClienteController implements ActionListener {

    ClienteDAO dao = new ClienteDAO();
    Cliente cliente = new Cliente();
    ClientesView view = new ClientesView();
    DefaultTableModel model = new DefaultTableModel();

    public ClienteController(ClientesView view) {
        this.view = view;
        this.view.btnListar.addActionListener(this);
        this.view.btnAgregar.addActionListener(this);
        this.view.btnEditar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnNuevo.addActionListener(this);
    }

    public void listar(JTable TablaDetalles) {
        model = (DefaultTableModel) TablaDetalles.getModel();
        ArrayList<Cliente> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getName();
            object[2] = lista.get(i).getNumberPhone();
            object[3] = lista.get(i).getAddress();
            model.addRow(object);
        }
        view.TablaDetalles.setModel(model);
    }

    public void agregar() {
        String name = view.txtName.getText();
        String numberPhone = view.txtNumberPhone.getText();
        String address = view.txtAddress.getText();
        cliente.setName(name);
        cliente.setNumberPhone(numberPhone);
        cliente.setAddress(address);
        if (name.equals("") || numberPhone.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        } else {
            int respuesta = dao.agregar(cliente);
            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "Usuario Agregado con exito...");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar agregar el usuario...");
            }
        }
    }

    public void actualizar() {
        int id = Integer.parseInt(view.txtID.getText());
        String name = view.txtName.getText();
        String numberPhone = view.txtNumberPhone.getText();
        String address = view.txtAddress.getText();
        cliente.setId(id);
        cliente.setName(name);
        cliente.setNumberPhone(numberPhone);
        cliente.setAddress(address);
        int r = dao.actualizar(cliente);
        if (r == 1) {
            JOptionPane.showMessageDialog(null, "Usuario Actualizado...");
        } else {
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar el usuario...");
        }
    }

    void limpiarRegistros() {
        for (int i = 0; i < view.TablaDetalles.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void eliminar() {
        int row = view.TablaDetalles.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
        } else {
            int id = Integer.parseInt((String) view.TablaDetalles.getValueAt(row, 0).toString());
            dao.eliminar(row);
            JOptionPane.showMessageDialog(null, "Usuario elmininado");
        }
    }

    public void editar(){
        int row = view.TablaDetalles.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt((String) view.TablaDetalles.getValueAt(row, 0).toString());
                String name = (String) view.TablaDetalles.getValueAt(row, 1);
                String numberPhone = (String) view.TablaDetalles.getValueAt(row, 2);
                String address = (String) view.TablaDetalles.getValueAt(row, 3);
                view.txtID.setText("" + id);
                view.txtName.setText(name);
                view.txtNumberPhone.setText(numberPhone);
                view.txtAddress.setText(address);
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == view.btnListar) {
            limpiarRegistros();
            listar(view.TablaDetalles);
        }
        if (evento.getSource() == view.btnAgregar) {
            agregar();
            limpiarRegistros();
            listar(view.TablaDetalles);
        }
        if (evento.getSource() == view.btnEditar) {
            editar();
            limpiarRegistros();
            listar(view.TablaDetalles);
        }
        if (evento.getSource() == view.btnActualizar) {
            actualizar();
            limpiarRegistros();
            listar(view.TablaDetalles);
        }
        if (evento.getSource() == view.btnEliminar) {
            eliminar();
            limpiarRegistros();
            listar(view.TablaDetalles);
        }
    }

}
