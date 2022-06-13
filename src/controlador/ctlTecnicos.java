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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.departamentos;
import modelo.generos;
import modelo.tecnicos;
import vista.frmTecnicos;

/**
 *
 * @author admin
 */
public class ctlTecnicos implements ActionListener {
    private tecnicos tTecnicos;
    private consulta consulta;
    private frmTecnicos frmTecnicos;
    
    
    public ctlTecnicos(tecnicos tTecnicos, consulta consulta, frmTecnicos frmTecnicos) {
        this.tTecnicos = tTecnicos;
        this.consulta = consulta;
        this.frmTecnicos = frmTecnicos;
        
        cargarGeneros();
        cargarDepartamentos();
        
        this.frmTecnicos.btnInsertar.addActionListener(this);
        this.frmTecnicos.btnModificar.addActionListener(this);
        this.frmTecnicos.btnBuscar.addActionListener(this);
       // this.frmTecnicos.btnEliminar.addActionListener(this);
    }

    public void iniciar() {
        // this.deshabilitar_botones();
        frmTecnicos.setTitle("Mantenimiento de Tecnicos");
        frmTecnicos.setLocationRelativeTo(null);
        frmTecnicos.txtID_Tecnico.setEnabled(false);
        frmTecnicos.txtCedula.requestFocus(true);
        limpiar();
        
    }
    
    public void limpiar(){
    
        frmTecnicos.txtID_Tecnico.setText(null);
        frmTecnicos.txtCedula.setText(null);
        frmTecnicos.txtNombre.setText(null);
        frmTecnicos.cboGenero.setSelectedItem(null);
        frmTecnicos.cboDepartamento.setSelectedItem(null);
    }
    
    public void cargarGeneros() {
        generos tGeneros = new generos();
        ArrayList genero = null;
        ArrayList id_genero = null;
        try {
            genero = consulta.buscarGenerosCbo(tGeneros);
           // codPaises = contD.ObtieneIdPais(dpto);
        } catch (SQLException ex) {
            Logger.getLogger(ctlClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<String> i = genero.iterator();
        while (i.hasNext()) {
            frmTecnicos.cboGenero.addItem(i.next());
          //  cod=
        }
    }
    
   
        public void cargarDepartamentos() {
        departamentos tDepartamentos = new departamentos();
        ArrayList departamento = null;
        ArrayList id_departamento = null;
        try {
            departamento = consulta.buscarDepartamentosCbo(tDepartamentos);
           // codPaises = contD.ObtieneIdPais(dpto);
        } catch (SQLException ex) {
            Logger.getLogger(ctlClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<String> i = departamento.iterator();
        while (i.hasNext()) {
            frmTecnicos.cboDepartamento.addItem(i.next());
          //  cod=
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (e.getSource() == frmTecnicos.btnBuscar) {
            
            if (frmTecnicos.txtCedula.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            

                tTecnicos.setCedula(frmTecnicos.txtCedula.getText()); 

                if (consulta.buscarTecnicos(tTecnicos)) {
                    this.tTecnicos.setCedula(frmTecnicos.txtCedula.getText());
                 //acá seteamos todo en el formulario
                    frmTecnicos.txtID_Tecnico.setText(String.valueOf(tTecnicos.getId_tecnico()));
                    frmTecnicos.txtCedula.setText(tTecnicos.getCedula());
                    frmTecnicos.txtNombre.setText(tTecnicos.getNombre());
                    frmTecnicos.cboDepartamento.setSelectedIndex(tTecnicos.getId_departamento());
                    frmTecnicos.cboGenero.setSelectedIndex(tTecnicos.getId_genero());


                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                    limpiar();
                }
           }
        }
        
        
        
        if (e.getSource() == frmTecnicos.btnModificar) {
            
            if (frmTecnicos.txtCedula.getText().equals("") || frmTecnicos.txtNombre.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
            
            
                tTecnicos.setId_tecnico(Integer.parseInt(frmTecnicos.txtID_Tecnico.getText()));
                tTecnicos.setCedula(frmTecnicos.txtCedula.getText());
                tTecnicos.setNombre(frmTecnicos.txtNombre.getText());
                tTecnicos.setId_departamento(frmTecnicos.cboDepartamento.getSelectedIndex()+1);
                tTecnicos.setId_genero(frmTecnicos.cboGenero.getSelectedIndex()+1);


                if (consulta.modificarTecnicos(tTecnicos, Integer.parseInt(frmTecnicos.txtID_Tecnico.getText()), frmTecnicos.txtCedula.getText(), frmTecnicos.txtNombre.getText(), tTecnicos.getId_departamento(), tTecnicos.getId_genero() ) ) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                    limpiar();
                }
            }
        }
        
        
        
        if (e.getSource() == frmTecnicos.btnInsertar) {
            
            if (frmTecnicos.txtCedula.getText().equals("") || frmTecnicos.txtNombre.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tTecnicos.setId_tecnico(0);

                tTecnicos.setCedula(frmTecnicos.txtCedula.getText());
                tTecnicos.setNombre(frmTecnicos.txtNombre.getText());
                tTecnicos.setId_departamento(frmTecnicos.cboDepartamento.getSelectedIndex()+1);
                tTecnicos.setId_genero(frmTecnicos.cboGenero.getSelectedIndex()+1);

                if (consulta.registrarTecnicos(tTecnicos)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                   limpiar();
                }
          }
        }
        
        
        
        
    }
    
    
    
}
