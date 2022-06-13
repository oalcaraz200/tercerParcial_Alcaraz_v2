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
import modelo.departamentos;
import vista.frmDepartamentos;
/**
 *
 * @author admin
 */
public class ctlDepartamentos implements ActionListener{
    private departamentos tDepartamentos;
    private consulta consulta;
    private frmDepartamentos frmDepartamentos;
    
    
    public ctlDepartamentos(departamentos tDepartamentos, consulta consulta, frmDepartamentos frmDepartamentos) {
        this.tDepartamentos = tDepartamentos;
        this.consulta = consulta;
        this.frmDepartamentos = frmDepartamentos;
        this.frmDepartamentos.btnInsertar.addActionListener(this);
        this.frmDepartamentos.btnModificar.addActionListener(this);
        this.frmDepartamentos.btnBuscar.addActionListener(this);
    }
    
    public void iniciar() {
        // this.deshabilitar_botones();
        frmDepartamentos.setTitle("Mantenimiento de Departamentos");
        frmDepartamentos.setLocationRelativeTo(null);
        frmDepartamentos.txtIdDepartamento.setEnabled(false);
        frmDepartamentos.txtDescripcion.requestFocus(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmDepartamentos.btnBuscar) {
            if (frmDepartamentos.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tDepartamentos.setDescripcion(frmDepartamentos.txtDescripcion.getText()); 

                if (consulta.buscarDepartamentos(tDepartamentos)) {
                  //  this.tDepartamentos.setId_departamento(Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()));
                 //acá seteamos todo en el formulario
                    frmDepartamentos.txtIdDepartamento.setText(String.valueOf(tDepartamentos.getId_departamento()));
                    frmDepartamentos.txtDescripcion.setText(tDepartamentos.getDescripcion());



                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");

                }
            }
        }
        
        
        
         if (e.getSource() == frmDepartamentos.btnModificar) {
             
            if (frmDepartamentos.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
                tDepartamentos.setId_departamento(Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()));
                tDepartamentos.setDescripcion(frmDepartamentos.txtDescripcion.getText());


                if (consulta.modificarDepartamentos(tDepartamentos, Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()), frmDepartamentos.txtDescripcion.getText() ) ) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                  //  limpiar();
                }
            
            }
        }
        
         
        if (e.getSource() == frmDepartamentos.btnInsertar) {
             
            if (frmDepartamentos.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
             
                tDepartamentos.setId_departamento(0);

                tDepartamentos.setDescripcion(frmDepartamentos.txtDescripcion.getText());


                if (consulta.registrarDepartamentos(tDepartamentos)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                  //  limpiar();
                }
            
            }
        }
         
         
         
         
         
       
    }
    
    
    
}
