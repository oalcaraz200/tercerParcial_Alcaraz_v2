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
import modelo.generos;
import vista.frmGeneros;
/**
 *
 * @author admin
 */
public class ctlGeneros implements ActionListener{
    
    private generos tGeneros;
    private consulta consulta;
    private frmGeneros frmGeneros;
    
    public ctlGeneros(generos tGeneros, consulta consulta, frmGeneros frmGeneros) {
        this.tGeneros = tGeneros;
        this.consulta = consulta;
        this.frmGeneros = frmGeneros;
        this.frmGeneros.btnInsertar.addActionListener(this);
        this.frmGeneros.btnModificar.addActionListener(this);
        this.frmGeneros.btnBuscar.addActionListener(this);
    }
    
        public void iniciar() {
        // this.deshabilitar_botones();
        frmGeneros.setTitle("Mantenimiento de Generos");
        frmGeneros.setLocationRelativeTo(null);
        frmGeneros.txtIdGeneros.setEnabled(false);
        frmGeneros.txtDescripcion.requestFocus(true);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmGeneros.btnBuscar) {
            
            if (frmGeneros.txtDescripcion.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tGeneros.setDescripcion(frmGeneros.txtDescripcion.getText()); 

                if (consulta.buscarGeneros(tGeneros)) {
                  //  this.tDepartamentos.setId_departamento(Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()));
                 //acá seteamos todo en el formulario
                    frmGeneros.txtIdGeneros.setText(String.valueOf(tGeneros.getId_genero()));
                    frmGeneros.txtDescripcion.setText(tGeneros.getDescripcion());



                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");

                }
            }
        }
        
        
        
        if (e.getSource() == frmGeneros.btnModificar) {
            if (frmGeneros.txtDescripcion.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tGeneros.setId_genero(Integer.parseInt(frmGeneros.txtIdGeneros.getText()));
                tGeneros.setDescripcion(frmGeneros.txtDescripcion.getText());


                if (consulta.modificarGeneros(tGeneros, Integer.parseInt(frmGeneros.txtIdGeneros.getText()), frmGeneros.txtDescripcion.getText() ) ) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                  //  limpiar();
                }
            }
        }
        
        
        if (e.getSource() == frmGeneros.btnInsertar) {
           
            if (frmGeneros.txtDescripcion.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tGeneros.setId_genero(0);

                tGeneros.setDescripcion(frmGeneros.txtDescripcion.getText());


                if (consulta.registrarGeneros(tGeneros)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                  //  limpiar();
                }
            
            }
        }

        
        
    }
    
    
}
