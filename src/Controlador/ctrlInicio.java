/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vistas.frmInicioPilots;
import Vistas.frmPilotos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ctrlInicio implements MouseListener{
    
    private frmInicioPilots vista;
    
    public ctrlInicio (frmInicioPilots vista){
        
        this.vista = vista;
        vista.btnInicio.addMouseListener(this);
        
    
    
    }
    
    
            

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnInicio){
         frmPilotos.initPilotos();
         vista.dispose();
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
