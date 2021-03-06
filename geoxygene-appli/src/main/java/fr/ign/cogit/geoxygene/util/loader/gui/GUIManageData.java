/*
 * This file is part of the GeOxygene project source files.
 * 
 * GeOxygene aims at providing an open framework which implements OGC/ISO
 * specifications for the development and deployment of geographic (GIS)
 * applications. It is a open source contribution of the COGIT laboratory at the
 * Institut Géographique National (the French National Mapping Agency).
 * 
 * See: http://oxygene-project.sourceforge.net
 * 
 * Copyright (C) 2005 Institut Géographique National
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library (see file LICENSE if present); if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 */

package fr.ign.cogit.geoxygene.util.loader.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * On demande a l'utilisateur ce qu'il veut faire.
 * 
 * @author Thierry Badard & Arnaud Braun
 * @version 1.0
 * 
 */

/*
 * L'idee est d'avoir differents types de generation des IDs possible : simple,
 * unicite sur toutes les tables geographiques de la base, recopie d'une
 * colonne, un jour calcul d'empreinte numerique ... La parametrisation se fait
 * par passage d'un tableau de bbolean.
 */

public class GUIManageData extends JFrame {

  /**
	 * 
	 */
  private static final long serialVersionUID = 4109632745800160047L;

  private static final String FRAME_TITLE = "GeOxygene Gestion des donnees ";

  boolean genereIds = false;
  boolean genereSimple = false;
  boolean genereUnique = false;
  boolean homogeneise = false;
  boolean spatialIndex = false;
  boolean emprise = false;

  private String genereIdsText = "Generation des COGITID";
  private String homogeneiseText = "Homogeneise les geometries";
  private String spatialIndexText = "Calcul des index spatiaux";
  private String empriseText = "Calcul des emprises (pour ORACLE)";

  private String genereSimpleText = "Generation simple";
  private String genereUniqueText = "Generation avec unicite sur toute la base";

  private boolean[] selectedValues = new boolean[6];

  public GUIManageData() {

  }

  public boolean[] showDialog() {
    final JDialog dialog = this.createDialog(this);
    dialog.setSize(600, 300);
    // dialog.show();
    dialog.setVisible(true);
    dialog.dispose();
    return this.selectedValues;
  }

  void getSelectedValues() {
    this.selectedValues[0] = this.genereIds;
    this.selectedValues[1] = this.homogeneise;
    this.selectedValues[2] = this.spatialIndex;
    this.selectedValues[3] = this.emprise;
    this.selectedValues[4] = this.genereSimple;
    this.selectedValues[5] = this.genereUnique;
  }

  private JDialog createDialog(Frame parent) {

    String title = GUIManageData.FRAME_TITLE;
    final JDialog dialog = new JDialog(parent, title, true);

    Container contentPane = dialog.getContentPane();
    contentPane.setLayout(new GridLayout(6, 1));

    final JCheckBox genereIdsCheckBox = new JCheckBox(this.genereIdsText);
    contentPane.add(genereIdsCheckBox);
    genereIdsCheckBox.setSelected(false);

    final JComboBox typeGenere = new JComboBox(new String[] {
        this.genereSimpleText, this.genereUniqueText });
    typeGenere.setEnabled(false);
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 10));
    panel.add(typeGenere);
    contentPane.add(panel);

    genereIdsCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (genereIdsCheckBox.isSelected()) {
          typeGenere.setEnabled(true);
        } else {
          typeGenere.setEnabled(false);
        }
      }
    });

    final JCheckBox homogeneiseCheckBox = new JCheckBox(this.homogeneiseText);
    contentPane.add(homogeneiseCheckBox);
    homogeneiseCheckBox.setSelected(false);

    final JCheckBox empriseCheckBox = new JCheckBox(this.empriseText);
    contentPane.add(empriseCheckBox);
    empriseCheckBox.setSelected(false);

    final JCheckBox spatialIndexCheckBox = new JCheckBox(this.spatialIndexText);
    contentPane.add(spatialIndexCheckBox);
    spatialIndexCheckBox.setSelected(false);

    JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

    JButton okButton = new JButton("Ok");
    okButton.setActionCommand("Ok");
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GUIManageData.this.genereIds = genereIdsCheckBox.isSelected();
        GUIManageData.this.homogeneise = homogeneiseCheckBox.isSelected();
        GUIManageData.this.spatialIndex = spatialIndexCheckBox.isSelected();
        GUIManageData.this.emprise = empriseCheckBox.isSelected();
        if (typeGenere.getSelectedIndex() == 0) {
          GUIManageData.this.genereSimple = true;
          GUIManageData.this.genereUnique = false;
        } else if (typeGenere.getSelectedIndex() == 1) {
          GUIManageData.this.genereSimple = false;
          GUIManageData.this.genereUnique = true;
        } else {
          GUIManageData.this.genereSimple = false;
          GUIManageData.this.genereUnique = false;
        }
        GUIManageData.this.getSelectedValues();
        dialog.dispose();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GUIManageData.this.genereIds = false;
        GUIManageData.this.genereSimple = false;
        GUIManageData.this.genereUnique = false;
        GUIManageData.this.homogeneise = false;
        GUIManageData.this.spatialIndex = false;
        GUIManageData.this.emprise = false;
        GUIManageData.this.getSelectedValues();
        dialog.dispose();
      }
    });

    controlPanel.add(okButton);
    controlPanel.add(cancelButton);
    contentPane.add(controlPanel);

    dialog.pack();
    dialog.setLocationRelativeTo(parent);

    return dialog;
  }

}
