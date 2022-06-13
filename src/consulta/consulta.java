/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

import db.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.clientes;
import modelo.departamentos;
import modelo.estados;
import modelo.generos;
import modelo.tecnicos;
import modelo.tickets;
import modelo.usuarios;
import vista.frmClientes;
import vista.frmDepartamentos;
import vista.frmEstados;
import vista.frmGeneros;
import vista.frmUsuarios;
import vista.frmTecnicos;
import vista.frmTickets;
/**
 *
 * @author admin
 */
public class consulta extends conexion{
    frmUsuarios frmUsuarios = new frmUsuarios();
    frmTecnicos frmTecnicos = new frmTecnicos();
    frmTickets  frmTickets  = new frmTickets();
    frmDepartamentos frmDepartamentos = new frmDepartamentos();
    frmEstados frmEstados = new frmEstados();
    frmGeneros frmGeneros = new frmGeneros();
    frmClientes frmClientes = new frmClientes();
    
    
    public boolean buscarUsuarios (usuarios tUsuarios){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM usuarios WHERE user = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            //ps.setInt(0, cont.getPais_codigo());
            ps.setString(1, tUsuarios.getUser());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tUsuarios.setId_usuarios(Integer.parseInt(rs.getString("id_usuarios")));
                tUsuarios.setUser(rs.getString("user"));
                tUsuarios.setPass(rs.getString("pass"));
                
                frmUsuarios.txtID_Usuario.setText(rs.getString("id_usuarios"));
                frmUsuarios.txtUser.setText(rs.getString("user"));
                frmUsuarios.txtPass.setText(rs.getString("pass"));

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    
    public boolean modificarUsuarios(usuarios tUsuarios, int id_usuarios, String user, String pass) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE usuarios SET user='"+user+"', pass='"+pass+"' where id_usuarios = "+id_usuarios+";";
        System.out.println("esto es lo que envía para modificar: "+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean eliminarUsuarios(usuarios tUsuarios) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM usuarios WHERE id_usuarios = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tUsuarios.getId_usuarios());
            ps.execute();
            System.out.println("Esto es lo que envía para borrar: "+ ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean registrarUsuarios(usuarios tUsuarios) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO usuarios (id_usuarios, user, pass) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tUsuarios.getId_usuarios());
            ps.setString(2,tUsuarios.getUser());
            ps.setString(3,tUsuarios.getPass());
            ps.execute();
            System.out.println("esto es lo que envía para insertar: "+ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    

   
    
    
    
        public boolean buscarTecnicos (tecnicos tTecnicos){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM tecnico WHERE cedula = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            //ps.setInt(0, cont.getPais_codigo());
            ps.setString(1, tTecnicos.getCedula());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tTecnicos.setId_tecnico(Integer.parseInt(rs.getString("id_tecnico")));
                tTecnicos.setCedula(rs.getString("cedula"));
                tTecnicos.setNombre(rs.getString("nombre"));
                int indiceDepartamento = Integer.parseInt(rs.getString("id_departamento"))-1;
                tTecnicos.setId_departamento(indiceDepartamento);
                int indiceGenero = Integer.parseInt(rs.getString("id_genero"))-1;
                tTecnicos.setId_genero(indiceGenero);
                
                frmTecnicos.txtID_Tecnico.setText(rs.getString("id_tecnico"));
                frmTecnicos.txtCedula.setText(rs.getString("cedula"));
                frmTecnicos.txtNombre.setText(rs.getString("nombre"));
               // frmTecnicos.cboDepartamento.setSelectedIndex(Integer.parseInt(rs.getString("id_departamento")));
               // frmTecnicos.cboGenero.setSelectedIndex(Integer.parseInt(rs.getString("id_genero")));
                
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
        
        
    public boolean modificarTecnicos(tecnicos tTecnicos, int id_tecnico, String cedula, String nombre, int id_departamento, int id_genero) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE tecnico SET cedula='"+cedula+"', nombre='"+nombre+"', id_departamento="+id_departamento+", id_genero="+id_genero+" where id_tecnico = "+id_tecnico+";";
        System.out.println("esto es lo que envía para modificar: "+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    public boolean registrarTecnicos(tecnicos tTecnicos) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO tecnico (id_tecnico, cedula, nombre, id_departamento, id_genero) VALUES(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tTecnicos.getId_tecnico());
            ps.setString(2,tTecnicos.getCedula());
            ps.setString(3,tTecnicos.getNombre());
            ps.setInt(4,tTecnicos.getId_departamento());
            ps.setInt(5,tTecnicos.getId_genero());
            
            ps.execute();
            System.out.println("esto es lo que envía para insertar: "+ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    
    
    public boolean buscarDepartamentos (departamentos tDepartamentos){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM departamento WHERE descripcion = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tDepartamentos.getDescripcion());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tDepartamentos.setId_departamento(Integer.parseInt(rs.getString("id_departamento")));
                tDepartamentos.setDescripcion(rs.getString("descripcion"));
                
                frmDepartamentos.txtIdDepartamento.setText(rs.getString("id_departamento"));
                frmDepartamentos.txtDescripcion.setText(rs.getString("descripcion"));

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    public boolean modificarDepartamentos(departamentos tDepartamentos, int id_departamento, String descripcion) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE departamento SET descripcion='"+descripcion+"' where id_departamento = "+id_departamento+";";
        System.out.println("esto es lo que envía para modificar: "+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean registrarDepartamentos(departamentos tDepartamentos) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO departamento (id_departamento, descripcion) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tDepartamentos.getId_departamento());
            ps.setString(2,tDepartamentos.getDescripcion());
            ps.execute();
            System.out.println("esto es lo que envía para insertar: "+ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    public boolean buscarGeneros (generos tGeneros){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM genero WHERE descripcion = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tGeneros.getDescripcion());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tGeneros.setId_genero(Integer.parseInt(rs.getString("id_genero")));
                tGeneros.setDescripcion(rs.getString("descripcion"));
                
                frmGeneros.txtIdGeneros.setText(rs.getString("id_genero"));
                frmGeneros.txtDescripcion.setText(rs.getString("descripcion"));

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
     public boolean modificarGeneros(generos tGeneros, int id_genero, String descripcion) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE genero SET descripcion='"+descripcion+"' where id_genero = "+id_genero+";";
        System.out.println("esto es lo que envía para modificar: "+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     
     
     
     
     public boolean registrarGeneros(generos tGeneros) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO genero (id_genero, descripcion) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tGeneros.getId_genero());
            ps.setString(2,tGeneros.getDescripcion());
            ps.execute();
            System.out.println("esto es lo que envía para insertar: "+ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
   
     
     
     public boolean buscarEstados (estados tEstados){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM estado_ticket WHERE descripcion = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tEstados.getDescripcion());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tEstados.setEstado_id(Integer.parseInt(rs.getString("estado_id")));
                tEstados.setDescripcion(rs.getString("descripcion"));
                
                frmEstados.txtEstado_id.setText(rs.getString("estado_id"));
                frmEstados.txtDescripcion.setText(rs.getString("descripcion"));

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     
     
     
     
     public boolean modificarEstados(estados tEstados, int estado_id, String descripcion) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE estado_ticket SET descripcion='"+descripcion+"' where estado_id = "+estado_id+";";
        System.out.println("esto es lo que envía para modificar: "+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     
     
     public boolean registrarEstados(estados tEstados) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO estado_ticket (estado_id, descripcion) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tEstados.getEstado_id());
            ps.setString(2,tEstados.getDescripcion());
            ps.execute();
            System.out.println("esto es lo que envía para insertar: "+ps);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     
     
     
      public boolean buscarClientes (clientes tClientes){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM cliente WHERE cedula = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tClientes.getCedula());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tClientes.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
                tClientes.setNombre(rs.getString("nombre"));
                tClientes.setCedula(rs.getString("cedula"));
                int indiceGenero = Integer.parseInt(rs.getString("id_genero")) - 1;
                tClientes.setId_genero(indiceGenero);
                
                frmClientes.txtIdCliente.setText(rs.getString("id_cliente"));
                frmClientes.txtCedula.setText(rs.getString("cedula"));
                frmClientes.txtNombre.setText(rs.getString("nombre"));


                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     
     
    public ArrayList buscarGenerosCbo(generos tGeneros) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        //com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConexion();
        String sql = "SELECT * FROM genero";
        ArrayList<String> genero = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String lsDescripcion = rs.getString("descripcion");
                genero.add(lsDescripcion);
                System.out.println(genero);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

            con.close();

        }
        return genero;
    }
     
    
    public ArrayList buscarDepartamentosCbo(departamentos tDepartamentos) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        //com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConexion();
        String sql = "SELECT * FROM departamento";
        ArrayList<String> departamento = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String lsDescripcion = rs.getString("descripcion");
                departamento.add(lsDescripcion);
                System.out.println(departamento);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

            con.close();

        }
        return departamento;
    }
    
     
    public ResultSet obtieneIdGenero(generos tGenero){
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        String sql = "select id_genero from genero where descripcion = '"+tGenero.getDescripcion()+"'";
        System.out.println(sql);
        try {
          ps = con.prepareCall(sql);
          rs = ps.executeQuery();
          return rs;
        } catch (SQLException e) {
           System.err.println(e);
           return rs;
        }
     
    }
    
    
    public boolean registrarClientes (clientes tClientes){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (id_cliente, cedula, nombre, id_genero) VALUES(default,?,?,?)";
        System.out.println(sql);
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,tClientes.getCedula()); ///// ojooooooo
            ps.setString(2,tClientes.getNombre());
            ps.setInt(3, tClientes.getId_genero());
            ps.execute();
            
            //System.out.println("Esto es lo que envía si ingresa: "+ps);
            return true;
        }catch (SQLException e){
            System.err.println(e);
          //  System.out.println("Esto es lo que envía cuando no entra: "+ps);
            return false;
        } finally {
            try{
                con.close();
            }catch (SQLException e){
                System.err.println(e);
            }

        }
    }
     
     
    public boolean modificarClientes(clientes tClientes, String cedula, String nombre, int id_genero, int id_cliente) {
        PreparedStatement ps = null;
        Connection con = getConexion();
               
        String sql = "UPDATE cliente SET cedula='"+cedula+"', nombre='"+nombre+"', id_genero="+id_genero+" where id_cliente = "+id_cliente+";";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean eliminarClientes(clientes tClientes) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM cliente WHERE id_cliente = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tClientes.getId_cliente());
            ps.execute();
            System.out.println(ps);
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public ArrayList buscarEstadosCbo(estados tEstados) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        //com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConexion();
        String sql = "SELECT * FROM estado_ticket";
        ArrayList<String> estado = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String lsDescripcion = rs.getString("descripcion");
                estado.add(lsDescripcion);
                System.out.println(estado);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

            con.close();

        }
        return estado;
    }
    
    
    
    
    public ArrayList buscarTecnicosCbo(tecnicos tTecnicos) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        //com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConexion();
        String sql = "SELECT * FROM tecnico";
        ArrayList<String> tecnico = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String lsDescripcion = rs.getString("nombre");
                tecnico.add(lsDescripcion);
                System.out.println(tecnico);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

            con.close();

        }
        return tecnico;
    }
    
    
    public ArrayList buscarClientesCbo(clientes tClientes) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = getConexion();
        //com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConexion();
        String sql = "SELECT * FROM cliente";
        ArrayList<String> cliente = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String lsDescripcion = rs.getString("nombre");
                cliente.add(lsDescripcion);
                System.out.println(cliente);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

            con.close();

        }
        return cliente;
    }
    
    
    
    
    public boolean buscarTickets (tickets tTickets){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM ticket WHERE id_ticket = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            //ps.setInt(0, cont.getPais_codigo());
            ps.setInt(1, tTickets.getId_ticket());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tTickets.setId_ticket(Integer.parseInt(rs.getString("id_ticket")));
                tTickets.setTitulo(rs.getString("titulo"));
                tTickets.setDescripcion(rs.getString("descripcion"));
                              
                int indiceEstado = Integer.parseInt(rs.getString("estado_id"))-1;
                tTickets.setEstado_id(indiceEstado);
                int indiceTecnico = Integer.parseInt(rs.getString("id_tecnico"))-1;
                tTickets.setId_tecnico(indiceTecnico);
                int indiceCliente = Integer.parseInt(rs.getString("id_cliente"))-1;
                tTickets.setId_cliente(indiceCliente);
                
                frmTickets.txtId_ticket.setText(rs.getString("id_ticket"));
                frmTickets.txtTitulo.setText(rs.getString("titulo"));
                frmTickets.txtDescripcion.setText(rs.getString("descripcion"));
               // frmTecnicos.cboDepartamento.setSelectedIndex(Integer.parseInt(rs.getString("id_departamento")));
               // frmTecnicos.cboGenero.setSelectedIndex(Integer.parseInt(rs.getString("id_genero")));
                
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarTickets(tickets tTickets, String titulo, String descripcion, int estado_id, int id_tecnico, int id_cliente, int id_ticket) {
        PreparedStatement ps = null;
        Connection con = getConexion();
               
        String sql = "UPDATE ticket SET titulo='"+titulo+"', descripcion='"+descripcion+"', estado_id="+estado_id+", id_tecnico="+id_tecnico+", id_cliente = "+id_cliente+" where id_ticket="+id_ticket+";";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean registrarTickets (tickets tTickets){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ticket (id_ticket, titulo, descripcion, estado_id, id_tecnico, id_cliente) VALUES(default,?,?,?,?,?)";
        System.out.println(sql);
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,tTickets.getTitulo()); ///// ojooooooo
            ps.setString(2,tTickets.getDescripcion());
            ps.setInt(3, tTickets.getEstado_id());
            ps.setInt(4, tTickets.getId_tecnico());
            ps.setInt(5, tTickets.getId_cliente());
            ps.execute();
            
            //System.out.println("Esto es lo que envía si ingresa: "+ps);
            return true;
        }catch (SQLException e){
            System.err.println(e);
          //  System.out.println("Esto es lo que envía cuando no entra: "+ps);
            return false;
        } finally {
            try{
                con.close();
            }catch (SQLException e){
                System.err.println(e);
            }

        }
    }
    
    
    
    
    public boolean buscarTicketsAlIngresar (tickets tTickets){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM ticket WHERE titulo = ? ";
    
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tTickets.getTitulo());
            rs = ps.executeQuery();
             System.out.println(ps);
            if (rs.next()) {
                tTickets.setId_ticket(Integer.parseInt(rs.getString("id_ticket")));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
}
