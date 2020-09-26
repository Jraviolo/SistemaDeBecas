package Interfaz;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class PanelInicio extends JPanel {

	private JLabel lblTitulo;
	private JButton btnPostular;
	private JButton btnVerRanking;
	private JButton btnSalir;
	
	private Frame framePadre;
	
	
	public PanelInicio() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblTitulo = new JLabel("Sistema de becas");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblTitulo, gridConst);
		
		
		btnPostular = new JButton("Postular alumno");
		gridConst.gridy = 1;
		btnPostular.addActionListener(e -> {
			this.postular();
		});
		this.add(btnPostular, gridConst);
		
		btnVerRanking = new JButton("Ver ranking de alumnos");
		gridConst.gridy = 2;
		btnVerRanking.addActionListener(e -> {
			this.verRanking();
		});
		this.add(btnVerRanking, gridConst);
		
		btnSalir = new JButton("Salir");
		gridConst.gridy = 4;
		btnSalir.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
			frame.dispose();
		});
		this.add(btnSalir, gridConst);
	}
	
	


	private void postular() {
		
		PanelInscripcion nueva = new PanelInscripcion();
		((JFrame) framePadre).setContentPane(nueva);
		framePadre.pack();
		nueva.setFramePadre(framePadre);
		
	}
	
	private void verRanking() {
		
		PanelAlumnos nueva = new PanelAlumnos();
		((JFrame) framePadre).setContentPane(nueva);
		framePadre.pack();
		nueva.setFramePadre(framePadre);
		
	}

	public Frame getFramePadre() {
		return framePadre;
	}

	public void setFramePadre(Frame framePadre) {
		this.framePadre = framePadre;
	}

}
