package Interfaz;


import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import SistemaDeBecas.Controller;
import clases.Alumno;

public class PanelAlumnos extends JPanel {

	
	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblDNI;
	private JLabel lblPromedio;
	private JLabel lblResultado;
	private JTextField txtMaterias;
	private JTextField txtPromedio;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTable tabla;
	private TablaAlumnos tablaAlumnos = new TablaAlumnos();
	private JTextField txtObs;
	private String resultado = new String();


	private Controller controller = new Controller();
	
	private Frame framePadre;
	
	public PanelAlumnos() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {

		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblNombre = new JLabel("Sistema de becas");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		lblTitulo = new JLabel("Lista de alumnos");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_END;

		lblDNI = new JLabel("Cantidad de materias aprobadas: ");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);

		//gridConst.anchor = GridBagConstraints.LINE_START;

		txtMaterias = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtMaterias, gridConst);
		

		lblPromedio = new JLabel("Promedio minimo: ");
		gridConst.gridx = 0;
		gridConst.gridy = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblPromedio, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_START;

		txtPromedio = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtPromedio, gridConst);



		

		/*
		* Comento la tabla vieja y la cambio por un JLabel con la tabla html que 
		* da el resultado de la query.
		
		
		tabla = new JTable(tablaAlumnos);
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.gridwidth = 3;
		tabla.setFillsViewportHeight(true);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tabla);

		this.add(scrollPane, gridConst);

		*/
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		lblResultado = new JLabel(resultado);
		gridConst.gridy = 8;
		gridConst.gridx = 0;
		//this.setBounds(100, 550, 800,800);
		//gridConst.gridwidth = 4;
		//lblResultado.setVerticalAlignment(JLabel.TOP);
		//lblResultado.setHorizontalAlignment(JLabel.CENTER);
        this.add(lblResultado,gridConst);
		
/*		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//int r = tabla.rowAtPoint(e.getPoint());
				//seleccion = r;
			}
		});

		*/
		
		
		gridConst.anchor = GridBagConstraints.LINE_END;
		
		btnBuscar = new JButton("Buscar");
		gridConst.gridy = 6;
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		btnBuscar.addActionListener(e -> {
			this.buscar();
		});
		this.add(btnBuscar, gridConst);
		
/*
		txtObs = new JTextField(24);
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		//gridConst.insets = new Insets(0, 5, 15, 5);	
		this.add(txtObs, gridConst);
*/


		gridConst.anchor = GridBagConstraints.LINE_END;
		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 6;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
			PanelInicio nuevo = new PanelInicio();
			nuevo.setFramePadre(framePadre);
			framePadre.pack();
			((JFrame) framePadre).setContentPane(new PanelInicio());
		});
		this.add(btnCancelar, gridConst);

	}



//	Setea el resultado de la busqueda en la tabla
	public void setResultadoBusqueda(List<Alumno> listaResultado, boolean actualizar) {
		
		this.tablaAlumnos.setContribuyentes(listaResultado);
		if(actualizar) {
			this.tablaAlumnos.fireTableDataChanged();
		}
	}

//	Obtiene el parametro de busqueda, realiza la busqueda a traves del gestor de BD y por ultimo actualiza la tabla
	public void buscar() {

		//Validacion para que el texto ingresado no sea vacio

		if (this.txtMaterias.getText().equals("") || this.txtPromedio.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Llene los campos.", "Error", JOptionPane.OK_OPTION);
			return;
		}
		else try {
			
			int materiasAprobadas = Integer.valueOf(this.txtMaterias.getText());
			int promedio = Integer.valueOf(this.txtPromedio.getText());
			int anio = 2020; //TODO Hacer validación año de la beca.

			resultado = controller.buscar(promedio, materiasAprobadas, anio);
			//List<Alumno> resultados = bdalumnos.getAlumnos(materiasAprobadas, promedio);
			//this.setResultadoBusqueda(resultados, true);
			
			lblResultado.setText(resultado);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.OK_OPTION);
		}
	}
	

	public Frame getFramePadre() {
		return framePadre;
	}

	public void setFramePadre(Frame framePadre) {
		this.framePadre = framePadre;
	}


	
}
