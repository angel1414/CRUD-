/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Piloto;
import Vistas.frmPilotos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ctrlPilotos implements MouseListener {
    
    
  
   
    private Piloto modelo;
    private frmPilotos vista;
 
 
        
    public ctrlPilotos(Piloto modelo, frmPilotos vista){
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnInsertar.addMouseListener(this);
        modelo.Mostrar(vista.jtPilotos);
        vista.btnEliminar.addMouseListener(this);
        vista.jtPilotos.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
       
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    if (e.getSource() == vista.btnInsertar) {
        try {
            modelo.setNombre_Piloto(vista.txtNombre.getText());
            modelo.setEdad_Piloto(Integer.parseInt(vista.txtEdad.getText())); 
            modelo.setPeso_Piloto(Double.parseDouble(vista.txtPeso.getText())); // Validar double
            modelo.setCorreo_Piloto(vista.txtCorreo.getText());
            
            modelo.Insertar();

            modelo.Mostrar(vista.jtPilotos);
        } catch (NumberFormatException ex) {
            System.out.println("Error: Ingresa valores numéricos válidos para la edad y el peso.");
        }
    }
  
    else if (e.getSource() == vista.btnEliminar) {
        modelo.Eliminar(vista.jtPilotos);
        modelo.Mostrar(vista.jtPilotos);
    }
   
    else if (e.getSource() == vista.jtPilotos) {
        modelo.cargarDatosTabla(vista);
    }
   
    else if (e.getSource() == vista.btnActualizar) {
        try {
            modelo.setNombre_Piloto(vista.txtNombre.getText());
            modelo.setEdad_Piloto(Integer.parseInt(vista.txtEdad.getText())); // Validar entero
            modelo.setPeso_Piloto(Double.parseDouble(vista.txtPeso.getText())); // Validar double
            modelo.setCorreo_Piloto(vista.txtCorreo.getText());

            modelo.Actualizar(vista.jtPilotos);
            modelo.Mostrar(vista.jtPilotos);
        } catch (NumberFormatException ex) {
            System.out.println("Error: Ingresa valores numéricos válidos para la edad y el peso.");
        }
    }
    if(e.getSource() == vista.btnLimpiar) {
        vista.txtCorreo.setText("");
        vista.txtPeso.setText("");
        vista.txtEdad.setText("");
        vista.txtNombre.setText("");



                
        
    }
}

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
