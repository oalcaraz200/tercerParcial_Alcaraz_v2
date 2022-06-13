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
import modelo.estados;
import vista.frmEstados;
/**
 *
 * @author admin
 */
public class ctlEstados implements ActionListener{
    private estados tEstados;
    private consulta consulta;
    private frmEstados frmEstados;
    
    
    public ctlEstados(estados tEstados, consulta consulta, frmEstados frmEstados) {
        this.tEstados = tEstados;
        this.consulta = consulta;
        this.frmEstados = frmEstados;
        this.frmEstados.btnInsertar.addActionListener(this);
        this.frmEstados.btnModificar.addActionListener(this);
        this.frmEstados.btnBuscar.addActionListener(this);
    }
    
    
    public void iniciar() {
        // this.deshabilitar_botones();
        frmEstados.setTitle("Mantenimiento de Departamentos");
        frmEstados.setLocationRelativeTo(null);
        frmEstados.txtEstado_id.setEnabled(false);
        frmEstados.txtDescripcion.requestFocus(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmEstados.btnBuscar) {
            
            if (frmEstados.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tEstados.setDescripcion(frmEstados.txtDescripcion.getText()); 

                if (consulta.buscarEstados(tEstados)) {
                  //  this.tDepartamentos.setId_departamento(Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()));
                 //acá seteamos todo en el formulario
                    frmEstados.txtEstado_id.setText(String.valueOf(tEstados.getEstado_id()));
                    frmEstados.txtDescripcion.setText(tEstados.getDescripcion());



                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");

                }
            }
        }
        
        
        
         if (e.getSource() == frmEstados.btnModificar) {
            if (frmEstados.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
             
             
                tEstados.setEstado_id(Integer.parseInt(frmEstados.txtEstado_id.getText()));
                tEstados.setDescripcion(frmEstados.txtDescripcion.getText());


                if (consulta.modificarEstados(tEstados, Integer.parseInt(frmEstados.txtEstado_id.getText()), frmEstados.txtDescripcion.getText() ) ) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                  //  limpiar();
                }
           }
        }
        
         
        if (e.getSource() == frmEstados.btnInsertar) {
            if (frmEstados.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
             
             
                tEstados.setEstado_id(0);

                tEstados.setDescripcion(frmEstados.txtDescripcion.getText());


                if (consulta.registrarEstados(tEstados)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                  //  limpiar();
                }
            }
        }           
    }
    
}
