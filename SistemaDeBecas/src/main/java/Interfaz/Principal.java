package Interfaz;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class Principal {
	URL iconURL = getClass().getResource("/imagenes/icono.png");
	// iconURL is null when not found
	ImageIcon icon = new ImageIcon(iconURL);
	Image i = icon.getImage();
	//frame.setIconImage(icon.getImage());
	//static ImageIcon img = new ImageIcon("/imagenes/iconFrame.png");

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		/*
		 * CARGA EL ESTILO POR DEFECTO DEL SISTEMA
		 * + INFO https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 * */
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				
			}
		});
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Sistema de Becas");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 800);
		//frame.setIconImage(i);

		//PanelInicio inicio = new PanelInicio();
		//PanelTareas tareas = new PanelTareas();
		PanelAlumnos nueva = new PanelAlumnos();
		//PanelDarAltaTitular altaTitular = new PanelDarAltaTitular();
		frame.setContentPane(nueva);
		frame.pack();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2- frame.getSize().width/2, dim.height/2- frame.getSize().height/2);
        
        ImageIcon img = new ImageIcon("imagenes/icono.png");
        frame.setIconImage(img.getImage());
        
	}
}

