/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import consulta.consulta;
import modelo.usuarios;
import vista.frmUsuarios;
/**
 *
 * @author admin
 */
public class ctlUsuarios implements ActionListener {
    
    private usuarios tUsuarios;
    private consulta consulta;
    private frmUsuarios frmUsuarios;

    public ctlUsuarios(usuarios tUsuarios, consulta consulta, frmUsuarios frmUsuarios) {
        this.tUsuarios = tUsuarios;
        this.consulta = consulta;
        this.frmUsuarios = frmUsuarios;
        this.frmUsuarios.btnInsertar.addActionListener(this);
        this.frmUsuarios.btnModificar.addActionListener(this);
        this.frmUsuarios.btnEliminar.addActionListener(this);
        this.frmUsuarios.btnBuscar.addActionListener(this);
    }
    
    public void iniciar() {
        // this.deshabilitar_botones();
        frmUsuarios.setTitle("Mantenimiento de Usuarios");
        frmUsuarios.setLocationRelativeTo(null);
        frmUsuarios.txtID_Usuario.setEnabled(false);
        frmUsuarios.txtUser.requestFocus(true);
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmUsuarios.btnBuscar) {
            
            if (frmUsuarios.txtUser.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
            tUsuarios.setUser(frmUsuarios.txtUser.getText()); 

                if (consulta.buscarUsuarios(tUsuarios)) {
                    this.tUsuarios.setUser(frmUsuarios.txtUser.getText());
                  //acá seteamos todo en el formulario
                    frmUsuarios.txtID_Usuario.setText(String.valueOf(tUsuarios.getId_usuarios()));
                    frmUsuarios.txtUser.setText(tUsuarios.getUser());
                    frmUsuarios.txtPass.setText(tUsuarios.getPass());

                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");

                }
            }
        }
        
        
        
        
        if (e.getSource() == frmUsuarios.btnModificar) {
            
            if (frmUsuarios.txtUser.getText().equals("") || frmUsuarios.txtPass.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tUsuarios.setId_usuarios(Integer.parseInt(frmUsuarios.txtID_Usuario.getText()));
                tUsuarios.setUser(frmUsuarios.txtUser.getText());
                tUsuarios.setPass(frmUsuarios.txtPass.getText());



                if (consulta.modificarUsuarios(tUsuarios, Integer.parseInt(frmUsuarios.txtID_Usuario.getText()), frmUsuarios.txtUser.getText(), frmUsuarios.txtPass.getText() ) ) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                  //  limpiar();
                }
            
            }
        }
        
        
        
        
        if (e.getSource() == frmUsuarios.btnEliminar) {
            if (frmUsuarios.txtUser.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {


                tUsuarios.setId_usuarios(Integer.parseInt(frmUsuarios.txtID_Usuario.getText()));

                if (consulta.eliminarUsuarios(tUsuarios)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    frmUsuarios.txtID_Usuario.setText(null);
                    frmUsuarios.txtUser.setText(null);
                    frmUsuarios.txtPass.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                   // limpiar();
                }
            
            }
        }
        
        
        
        if (e.getSource() == frmUsuarios.btnInsertar) {
            
            if (frmUsuarios.txtUser.getText().equals("") || frmUsuarios.txtPass.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tUsuarios.setId_usuarios(0);

                tUsuarios.setUser(frmUsuarios.txtUser.getText());
                tUsuarios.setPass(frmUsuarios.txtPass.getText());


                if (consulta.registrarUsuarios(tUsuarios)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                  //  limpiar();
                }
          }
        }
    }
}
