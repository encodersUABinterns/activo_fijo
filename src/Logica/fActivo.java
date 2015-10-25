/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

//import java.sql.Connection;

import Datos.vActivo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author Blady
 */
public class fActivo {
   // private conexion mysql=new conexion();
   // private Connection cn=mysql.conectar();
  private conexion conexi =new conexion();
  private Connection cn=null;
  private String sSQL="";
  public Integer totalregistro;
  
  
  
public DefaultTableModel mostrar(String buscar) throws SQLException
{
    DefaultTableModel modelo;
    Statement consulta = null;
    
    String [] titulos = {"id_activo","cuenta","codigo","descripcion","cant","fecha_adq","fecha_registro","costo_adq","depresiacion","id_cuenta"};
    String [] registro =new String [10];
    totalregistro = 0;
    modelo= new DefaultTableModel(null,titulos);
    sSQL=("Select* from t_activos where cuenta like '%"+ buscar + "%' order by id_activo desc");
    try
    {
        Statement st= cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next())
            {
                registro [0] = rs.getString("id_activo");
                registro [1] = rs.getString("cuenta");
                registro [2] = rs.getString("codigo");
                registro [3] = rs.getString("descripcion");
                registro [4] = rs.getString("cant");
                registro [5] = rs.getString("fecha_adq");
                registro [6] = rs.getString("fecha_registro");
                registro [7] = rs.getString("id_activo");
                registro [8] = rs.getString("id_activo");
                registro [9] = rs.getString("id_activo");
                
                totalregistro=totalregistro+1;
                modelo.addRow(registro);
            }
        return modelo;
        
    }catch(Exception e)
    {
        JOptionPane.showConfirmDialog(null, e);
        return null;
    }
    
    
}
public boolean insertar (vActivo dts){
       sSQL="insert into t_activos (cuenta,codigo,descripcion,cant,fecha_adq,fecha_registro,costo_adq,depresiacion,id_cuenta)" +
               "values (?,?,?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(2, dts.getCuenta());
           pst.setString(3, dts.getCodigo());
           pst.setString(4, dts.getDescripcion());
           pst.setInt(5, dts.getCant());
           pst.setDate(6, (Date) dts.getFecha_adq());
           pst.setDate(7, (Date)dts.getFecha_registro());
           pst.setDouble(8, dts.getCosto_adq());
           pst.setDouble(9, dts.getDepresiacion());
           pst.setInt(10, dts.getId_cuenta());
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
    
}
