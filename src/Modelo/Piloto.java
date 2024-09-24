/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.claseConexion;
import Vistas.frmPilotos;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Piloto {


    
    public Piloto(){
        
    }
    /**
     * @return the Nombre_Piloto
     */
    public String getNombre_Piloto() {
        return Nombre_Piloto;
    }

    /**
     * @param Nombre_Piloto the Nombre_Piloto to set
     */
    public void setNombre_Piloto(String Nombre_Piloto) {
        this.Nombre_Piloto = Nombre_Piloto;
    }

    /**
     * @return the Edad_Piloto
     */
    public int getEdad_Piloto() {
        return Edad_Piloto;
    }

    /**
     * @param Edad_Piloto the Edad_Piloto to set
     */
    public void setEdad_Piloto(int Edad_Piloto) {
        this.Edad_Piloto = Edad_Piloto;
    }

    /**
     * @return the Peso_Piloto
     */
    public Double getPeso_Piloto() {
        return Peso_Piloto;
    }

    /**
     * @param Peso_Piloto the Peso_Piloto to set
     */
    public void setPeso_Piloto(Double Peso_Piloto) {
        this.Peso_Piloto = Peso_Piloto;
    }

    /**
     * @return the Correo_Piloto
     */
    public String getCorreo_Piloto() {
        return Correo_Piloto;
    }

    /**
     * @param Correo_Piloto the Correo_Piloto to set
     */
    public void setCorreo_Piloto(String Correo_Piloto) {
        this.Correo_Piloto = Correo_Piloto;
    }
    
  
    private String Nombre_Piloto;
    private int Edad_Piloto;
    private Double Peso_Piloto;
    private String Correo_Piloto;
  
    
     public Piloto( String Nombre_Piloto, int Edad_Piloto, Double Peso_Piloto, String Correo_Piloto) {
        this.Nombre_Piloto = Nombre_Piloto;
        this. Edad_Piloto = Edad_Piloto;
        this.Peso_Piloto = Peso_Piloto;
        this.Correo_Piloto = Correo_Piloto;
        
        
        
    }
     
      public void Insertar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbPiloto(Nombre_Piloto, Edad_Piloto, Peso_Piloto, Correo_Piloto) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, getNombre_Piloto());
            addProducto.setInt(2, getEdad_Piloto());
            addProducto.setDouble(3, getPeso_Piloto());
            addProducto.setString(4, getCorreo_Piloto());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
     
     
          public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = claseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Piloto", "Nombre_Piloto",  "Edad_Piloto",  "Peso_Piloto",  "Correo_Piloto"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbPiloto");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getInt("UUID_Piloto"),
                    rs.getString("Nombre_Piloto"),
                    rs.getInt("Edad_Piloto"), 
                    rs.getDouble("Peso_Piloto"), 
                    rs.getString("Correo_Piloto")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
        
        
    }
        
        
       public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbPiloto where UUID_piloto = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }   
    }
       
          public void cargarDatosTabla(frmPilotos vista) {
    // Obtén la fila seleccionada 
    int filaSeleccionada = vista.jtPilotos.getSelectedRow();

    // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
    if (filaSeleccionada != -1) {
        // Obtener valores de la tabla y hacer el casting adecuado
        String UUIDDeTb = vista.jtPilotos.getValueAt(filaSeleccionada, 0).toString();
        String NombreDeTB = vista.jtPilotos.getValueAt(filaSeleccionada, 1).toString();
        int EdadDeTB = (Integer) vista.jtPilotos.getValueAt(filaSeleccionada, 2);
        Double PesoDeTB = (Double) vista.jtPilotos.getValueAt(filaSeleccionada, 3);
        String CorreoDeTB = vista.jtPilotos.getValueAt(filaSeleccionada, 4).toString();

        // Establecer los valores en los campos de texto
        vista.txtNombre.setText(NombreDeTB);
        vista.txtEdad.setText(String.valueOf(EdadDeTB)); // Convertir edad a String
        vista.txtPeso.setText(String.valueOf(PesoDeTB)); // Convertir peso a String
        vista.txtCorreo.setText(CorreoDeTB);
    }

    }
          
    public void Actualizar(JTable tabla) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = claseConexion.getConexion();
    // Obtenemos qué fila seleccionó el usuario
    int filaSeleccionada = tabla.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        // Obtenemos el id de la fila seleccionada
        String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            // Ejecutamos la query
            PreparedStatement updateUser = conexion.prepareStatement("UPDATE tbPiloto SET nombre_piloto = ?, edad_piloto = ?, peso_piloto = ?, correo_piloto = ? WHERE UUID_piloto = ?");
            updateUser.setString(1, getNombre_Piloto());
            updateUser.setInt(2, getEdad_Piloto());
            updateUser.setDouble(3, getPeso_Piloto());
            updateUser.setString(4, getCorreo_Piloto());
            updateUser.setString(5, miUUId);
            
            updateUser.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}

     
     
     
     
     
}


