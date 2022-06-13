/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alber
 */
public class conexion {

    String base = "mydb";
    private final String user = "oalcaraz"; //root???
    private final String password = "ua2022.py";
    private final String url = "jdbc:mysql://localhost/" + base;
    private Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);

        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    
    
    
    public int Login (String user, String pass){
        Integer resultado=0;
    
        try {
            Statement ejecutor = con.createStatement();
            ResultSet rs = ejecutor.executeQuery("Select * from usuarios where user= '"+user+"' and pass = '"+pass+"'");    
            
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"Bienvenido");
                resultado=1;
            } else {
                JOptionPane.showMessageDialog(null,"Usuario o Contraseña invalidos");
                resultado=0;
                
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al conectar " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } 
        return resultado;
    }
  
}
