package Logica;

/**
 *
 * @author Augustito
 */
import com.toedter.calendar.JDateChooser;
import java.sql.*; //importamos java.sql para poder trabajar con SQL :P
import java.text.SimpleDateFormat;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class conexion {
//String driver = "org.postgresql.Driver"; 
//String connectString = "jdbc:postgresql://10.35.9.201:5432/devel/"; // ACA PONE DONDE ESTA TU BASE DE DATOS 
//String user = "USUARIO"; 
//String password = "PASSWORD"; 
    public Connection conexion = null;
    
    public void conectar()
    {
        if (conexion != null)
        {
            return;
        }
        String cadena = "jdbc:postgresql://192.168.1.125:5432/Activo_Fijo";
        String password = "DTICSW15.";
        String user = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(cadena,user,password);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
public void cierraConexion()
    {
        try
        {
            conexion.close();
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }
     
     public ResultSet dameNombre(String sentencia)
    {
        ResultSet rs = null;
        Statement s = null;
        try
        {  
            s = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            rs = s.executeQuery(sentencia);
            
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return rs;
    }
      public  void registrar_Rubro(JTextField orden, JTextField descripcion,JTextField sigla)
    {
        ResultSet rs = null;
        Statement consulta = null;
        try
        {  
            consulta = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            consulta.executeUpdate("insert into t_cuentas(orden, descripcion,sigla) values('" + orden.getText() + "','" + descripcion.getText() +"','" + sigla.getText() + "')");
            JOptionPane.showMessageDialog(null, "Se ha ingresado correctamente");
            orden.setText(null);
            descripcion.setText(null);
             sigla.setText(null);
           
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
      public  void registrar_Area1(JTextField nombre, JTextField sigla,int id_cuent)
    {
        ResultSet rs = null;
        try
        {  
          Statement consulta = (Statement)conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            consulta.executeUpdate("insert into t_area1(nombre, sigla, id_cuenta) values('" + nombre.getText() + "','" + sigla.getText() +"','" + id_cuent + "')");
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
      public  void registrar_Area2(JTextField nombre, JTextField sigla, JTextField provincia, JTextField carrera,int id_area1)
    {
        ResultSet rs = null;
        Statement consulta = null;
        try
        {  
            consulta = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            consulta.executeUpdate("insert into t_area2(nombre, sigla,provincia,carrera,id_area1) values('" + nombre.getText() + "','" + sigla.getText() +"','" + provincia.getText() +"','" + carrera.getText() +"','" + id_area1 + "')");
                   
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
      public  void registrar_subareas(JTextField nombre,JTextField codigo, int id_area2)
    {
        ResultSet rs = null;
        Statement consulta = null;
        try
        {  
            consulta = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            consulta.executeUpdate("insert into t_subareas(nombre,cod,id_area2) values('" + nombre.getText() + "','" + codigo.getText()+"','" +  id_area2 + "')");
                        
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
       public  void registrar_Activo(JComboBox cuenta, String serie,JTextField descrip, JTextField cant, JDateChooser fech_adq, JDateChooser fech_reg,JTextField costo_adq,Double depre,int id_cuenta)
    {
        ResultSet rs = null;
        Statement consulta = null;
        Double de=0.0;
        
        try
        {  
            consulta = conexion.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date fecha= fech_adq.getDate();
        java.util.Date fecha2= fech_reg.getDate();
        String fechaadq = formato.format(fecha);
        String fecharegistro = formato.format(fecha);
        de= Double.parseDouble(costo_adq.getText())*depre;
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            consulta.executeUpdate("insert into t_activo(cuenta, codigo,descripcion,cant,fecha_adq,fecha_registro,costo_adq,depresiacion,id_cuenta) values('" +cuenta.getSelectedItem() + "','" + serie +"','" + descrip.getText() +"','" +cant.getText() +"','" + fechaadq +"','"+fecharegistro+"','" + costo_adq.getText() +"','" + de +"','"+ id_cuenta + "')");
            JOptionPane.showMessageDialog(null, "Se ha ingresado correctamente");
                                           
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
