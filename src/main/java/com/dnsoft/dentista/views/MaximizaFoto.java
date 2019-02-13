/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.FotosTratamiento;
import java.awt.Image;
import javax.swing.ImageIcon;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author Diego
 */
public class MaximizaFoto extends javax.swing.JDialog {

    /**
     * Creates new form MaximizaFoto
     */
    public MaximizaFoto(java.awt.Frame parent, boolean modal, FotosTratamiento FotosTratamientoSeleccionado) {
        super(parent, modal);
        initComponents();

        if (FotosTratamientoSeleccionado.getFoto() != null) {
            Image imagen = new ToolkitImage(new ByteArrayImageSource(FotosTratamientoSeleccionado.getFoto()));
            Image imgReDimensionada = imagen.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(imgReDimensionada);

            jlblFoto.setIcon(icon);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jlblFoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jlblFoto.setBackground(new java.awt.Color(204, 204, 204));
        jlblFoto.setOpaque(true);
        jlblFoto.setPreferredSize(new java.awt.Dimension(3, 4));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jlblFoto, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlblFoto;
    // End of variables declaration//GEN-END:variables
}
