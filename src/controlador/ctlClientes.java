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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.clientes;
import modelo.generos;
import vista.frmClientes;
/**
 *
 * @author admin
 */
public class ctlClientes implements ActionListener{
    private clientes tClientes;
    private consulta consulta;
    private frmClientes frmClientes;

    
    public ctlClientes(clientes tClientes, consulta consulta, frmClientes frmClientes) {
        this.tClientes = tClientes;
        this.consulta = consulta;
        this.frmClientes = frmClientes;
        
       cargarGeneros();
        
        this.frmClientes.btnInsertar.addActionListener(this);
        this.frmClientes.btnModificar.addActionListener(this);
        this.frmClientes.btnBuscar.addActionListener(this);

    }
    
    
    public void iniciar() {
        // this.deshabilitar_botones();
        frmClientes.setTitle("Mantenimiento de Clientes");
        frmClientes.setLocationRelativeTo(null);
        frmClientes.txtIdCliente.setEnabled(false);
       // frmClientes.btnSeleccionarCliente.setEnabled(false);
        frmClientes.txtCedula.requestFocus(true);
        frmClientes.cboGenero.setSelectedItem(null);
        
    }
    
    
    public void iniciarSeleccionarClientes() {
        // this.deshabilitar_botones();
        frmClientes.setTitle("Seleccionar Cliente");
        frmClientes.setLocationRelativeTo(null);
        frmClientes.txtIdCliente.setEnabled(false);
      //  frmClientes.btnSeleccionarCliente.setEnabled(true);
        frmClientes.txtCedula.requestFocus(true);
       
        
    }
    
    public void limpiar(){
    
        frmClientes.txtIdCliente.setText(null);
        frmClientes.txtCedula.setText(null);
        frmClientes.txtNombre.setText(null);
        frmClientes.cboGenero.setSelectedItem(null);
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
            frmClientes.cboGenero.addItem(i.next());
          //  cod=
        }
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == frmClientes.btnBuscar) {
           
           if (frmClientes.txtCedula.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
                tClientes.setCedula(frmClientes.txtCedula.getText()); 

               if (consulta.buscarClientes(tClientes)) {
                  //  this.tDepartamentos.setId_departamento(Integer.parseInt(frmDepartamentos.txtIdDepartamento.getText()));
                 //acá seteamos todo en el formulario
                    frmClientes.txtIdCliente.setText(String.valueOf(tClientes.getId_cliente()));
                    frmClientes.txtCedula.setText(tClientes.getCedula());
                    frmClientes.txtNombre.setText(tClientes.getNombre());
                    frmClientes.cboGenero.setSelectedIndex(tClientes.getId_genero());

                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");

                }
           }
        }
       
       
       if (e.getSource() == frmClientes.btnInsertar) {
           
           if (frmClientes.txtCedula.getText().equals("") || frmClientes.txtNombre.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
           
                clientes tClientes = new clientes();
                tClientes.setCedula(frmClientes.txtCedula.getText());
                tClientes.setNombre(frmClientes.txtNombre.getText());
                tClientes.setId_genero(frmClientes.cboGenero.getSelectedIndex()+1);

                if (consulta.registrarClientes(tClientes)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                    limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    //System.out.println(sql);
                    limpiar();
                }
           }
        }
       
       
       
       
       
        if (e.getSource() == frmClientes.btnModificar) {
            
            if (frmClientes.txtCedula.getText().equals("") || frmClientes.txtNombre.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tClientes.setCedula(frmClientes.txtCedula.getText());
                tClientes.setNombre(frmClientes.txtNombre.getText());
                tClientes.setId_genero(frmClientes.cboGenero.getSelectedIndex()+1);

                if (consulta.modificarClientes(tClientes, frmClientes.txtCedula.getText(), frmClientes.txtNombre.getText(), tClientes.getId_genero(), tClientes.getId_cliente() )) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                   limpiar();
                }
            
            }
        }
        
        
        
        
        
       
    }
    
    
    
    
    
    
    
}
