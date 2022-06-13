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
import modelo.clientes;
import modelo.estados;
import modelo.generos;
import modelo.tecnicos;
import modelo.tickets;
import vista.frmTickets;
/**
 *
 * @author admin
 */
public class ctlTickets implements ActionListener {
    private tickets tTickets;
    private consulta consulta;
    private frmTickets frmTickets;
    
    
    public ctlTickets(tickets tTickets, consulta consulta, frmTickets frmTickets) {
        this.tTickets = tTickets;
        this.consulta = consulta;
        this.frmTickets = frmTickets;
        
        cargarEstados();
        cargarTecnicos();
        cargarClientes();
        
        this.frmTickets.btnInsertar.addActionListener(this);
        this.frmTickets.btnModificar.addActionListener(this);
        this.frmTickets.btnBuscar.addActionListener(this);

    }
    
    public void iniciar() {
        // this.deshabilitar_botones();
        frmTickets.setTitle("Tickets");
        frmTickets.setLocationRelativeTo(null);
        frmTickets.txtId_ticket.requestFocus(true);
        
    }
    
    public void limpiar(){
    
        frmTickets.txtId_ticket.setText(null);
        frmTickets.txtTitulo.setText(null);
        frmTickets.txtDescripcion.setText(null);
        frmTickets.cboEstado.setSelectedItem(null);
        frmTickets.cboTecnico.setSelectedItem(null);
        frmTickets.cboCliente.setSelectedItem(null);
    }
    
    
    
    public void cargarEstados() {
        estados tEstados = new estados();
        ArrayList estado = null;
        ArrayList id_estado = null;
        try {
            estado = consulta.buscarEstadosCbo(tEstados);
           // codPaises = contD.ObtieneIdPais(dpto);
        } catch (SQLException ex) {
            Logger.getLogger(ctlTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<String> i = estado.iterator();
        while (i.hasNext()) {
            frmTickets.cboEstado.addItem(i.next());
          //  cod=
        }
        limpiar();
    }
    
   
    public void cargarTecnicos() {
        tecnicos tTecnicos = new tecnicos();
        ArrayList tecnico = null;
        ArrayList id_tecnico = null;
        try {
            tecnico = consulta.buscarTecnicosCbo(tTecnicos);
           // codPaises = contD.ObtieneIdPais(dpto);
        } catch (SQLException ex) {
            Logger.getLogger(ctlTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<String> i = tecnico.iterator();
        while (i.hasNext()) {
            frmTickets.cboTecnico.addItem(i.next());
          //  cod=
        }
        limpiar();
    }

    
    
    
    public void cargarClientes() {
        clientes tClientes = new clientes();
        ArrayList cliente = null;
        ArrayList id_cliente = null;
        try {
            cliente = consulta.buscarClientesCbo(tClientes);
           // codPaises = contD.ObtieneIdPais(dpto);
        } catch (SQLException ex) {
            Logger.getLogger(ctlTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<String> i = cliente.iterator();
        while (i.hasNext()) {
            frmTickets.cboCliente.addItem(i.next());
          //  cod=
        }
        limpiar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmTickets.btnBuscar) {
            
            if (frmTickets.txtId_ticket.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "Sólo se puede buscar por ID TICKET. \n El campo no puede estar vacio");
            } else {
            
            
                tTickets.setId_ticket(Integer.parseInt(frmTickets.txtId_ticket.getText())); 

                if (consulta.buscarTickets(tTickets)) {
                 //   this.tTecnicos.setCedula(frmTecnicos.txtCedula.getText());
                 //acá seteamos todo en el formulario
                    frmTickets.txtId_ticket.setText(String.valueOf(tTickets.getId_ticket()));
                    frmTickets.txtTitulo.setText(tTickets.getTitulo());
                    frmTickets.txtDescripcion.setText(tTickets.getDescripcion());
                    frmTickets.cboEstado.setSelectedIndex(tTickets.getEstado_id());
                    frmTickets.cboTecnico.setSelectedIndex(tTickets.getId_tecnico());
                    frmTickets.cboCliente.setSelectedIndex(tTickets.getId_cliente());


                    JOptionPane.showMessageDialog(null, "Registro Encontrado");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                    limpiar();
                }
            
            }
        }
        
        
        
        
        
        if (e.getSource() == frmTickets.btnModificar) {
            
            if (frmTickets.txtTitulo.getText().equals("") || frmTickets.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
            
                tTickets.setTitulo(frmTickets.txtTitulo.getText());
                tTickets.setDescripcion(frmTickets.txtDescripcion.getText());
                tTickets.setEstado_id(frmTickets.cboEstado.getSelectedIndex()+1);
                tTickets.setId_cliente(frmTickets.cboCliente.getSelectedIndex()+1);
                tTickets.setId_tecnico(frmTickets.cboTecnico.getSelectedIndex()+1);

                if (consulta.modificarTickets(tTickets, tTickets.getTitulo(), tTickets.getDescripcion(), tTickets.getEstado_id(), tTickets.getId_tecnico(), tTickets.getId_cliente(), tTickets.getId_ticket() )) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                   limpiar();
                }
            }
        }
        
        
        if (e.getSource() == frmTickets.btnInsertar) {
            if (frmTickets.txtTitulo.getText().equals("") || frmTickets.txtDescripcion.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else {
            
                tickets tTickets = new tickets();
                tTickets.setId_ticket(0);
                tTickets.setTitulo(frmTickets.txtTitulo.getText());
                tTickets.setDescripcion(frmTickets.txtDescripcion.getText());
                tTickets.setEstado_id(frmTickets.cboEstado.getSelectedIndex()+1);
                tTickets.setId_cliente(frmTickets.cboCliente.getSelectedIndex()+1);
                tTickets.setId_tecnico(frmTickets.cboTecnico.getSelectedIndex()+1);

                if (consulta.registrarTickets(tTickets)) {
                    JOptionPane.showMessageDialog(null, "Registrado");
                    limpiar();
                    if(consulta.buscarTicketsAlIngresar(tTickets)){
                        JOptionPane.showMessageDialog(null, "Su numero de Ticket es: "+tTickets.getId_ticket());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    //System.out.println(sql);
                    limpiar();
                }
            
            }
        }
        
        
        
    }
}
