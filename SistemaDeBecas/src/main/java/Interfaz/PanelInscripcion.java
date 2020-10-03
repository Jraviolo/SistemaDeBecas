package Interfaz;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import clases.Familiar;
import clases.Postulante;
import respository.AlumnosRepository;

public class PanelInscripcion extends JPanel {

	private JLabel lblLogo;
	private JLabel lblTitulo;
	
	private JTextField txtLegajo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox cmbCarrera;
	private JLabel lblFamiliaresACargo;
	private JTextField txtFamiliaresACargo;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField txtFechaDeNaciminto;
	private JTextField txtNacionalidad;
	private JTextField txtLocalidad;
	private JTextField txtProvincia;
	private JTextField txtCodigoPostal;
	private JTextField txtSituacionSocial;
	private JTextField txtDomicilio;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTable tabla;
	private TablaFamiliares tablaFamiliares = new TablaFamiliares();
	private JTextField txtObs;

	private AlumnosRepository bdalumnos= new AlumnosRepository();
	private Controller controller = new Controller();

	private int seleccion = -1;
	private List<Familiar> familiares=new ArrayList();
	private float puntaje;
	
	private Frame framePadre;
	
	public PanelInscripcion() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {

		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblLogo = new JLabel("Sistema de becas");
		lblLogo.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblLogo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;
		lblTitulo = new JLabel("Inscripcion a beca");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);

		gridConst.anchor = GridBagConstraints.FIRST_LINE_END;
		gridConst.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblLegajo = new JLabel("Legajo: ");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblLegajo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;
		txtLegajo = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtLegajo, gridConst);
		
		JLabel lblCarrera = new JLabel("Carrera: ");
		gridConst.gridx = 2;
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblCarrera, gridConst);
		
		String [] carreras = { "Sistemas", "Civil", "Industrial", "Eléctrica", "Mecánica" };
		cmbCarrera = new JComboBox(carreras);
		cmbCarrera.setEditable(false);
		cmbCarrera.setPreferredSize(new Dimension(100, 20));
		gridConst.gridx = 3;
		gridConst.gridwidth = 2;
		this.add(cmbCarrera, gridConst);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblNombre, gridConst);

		txtNombre = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtNombre, gridConst);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblApellido, gridConst);

		txtApellido = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtApellido, gridConst);
			

		JLabel lblIngresosamiliares = new JLabel("Ingresos Familiares: ");
		gridConst.gridx = 0;
		gridConst.gridy = 5;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblIngresosamiliares, gridConst);
		//gridConst.anchor = GridBagConstraints.LINE_START;
		
		btnAgregar = new JButton("+ Agregar");
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnAgregar.addActionListener(e -> {
			
			String name = JOptionPane.showInputDialog("Ingrese su nombre");
			String sueldo = JOptionPane.showInputDialog("Ingrese su sueldo");
			if(true) {
				try {
					Familiar familiar = new Familiar();
					familiar.setIngresos(Integer.valueOf(sueldo));
					familiar.setNombre(name);
					JOptionPane.showMessageDialog(null, "Agregado "+name+" $"+sueldo);
					familiares.add(familiar);
					actualizarTabla();
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.OK_OPTION);
				}
				
			}
	
			//agregar A LA LISTA
		});
		this.add(btnAgregar, gridConst);
		
		btnEliminar = new JButton("- Eliminar");
		gridConst.gridx = 3;
		gridConst.gridwidth = 1;
		btnEliminar.addActionListener(e -> {
			if(seleccion<0) {
				JOptionPane.showMessageDialog(null, "Seleccione una persona de la tabla", "Error", JOptionPane.OK_OPTION);
			}
			else {
				familiares.remove(seleccion);
				actualizarTabla();
				seleccion=-1;
				}
			
		});
		this.add(btnEliminar, gridConst);
		
		tabla = new JTable(tablaFamiliares);
		gridConst.gridy = 6;
		gridConst.gridx = 0;
		gridConst.gridwidth = 4;
		tabla.setFillsViewportHeight(true);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension (500,50));
		JScrollPane scrollPane = new JScrollPane(tabla);
		this.add(scrollPane, gridConst);

		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tabla.rowAtPoint(e.getPoint());
				seleccion = r;
			}
		});
		
		lblFamiliaresACargo = new JLabel("Familiares a cargo: ");
		gridConst.gridx = 0;
		gridConst.gridy = 7;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblFamiliaresACargo, gridConst);
		//gridConst.anchor = GridBagConstraints.LINE_START;

		txtFamiliaresACargo = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtFamiliaresACargo, gridConst);

	/*	JLabel lblFechaDeNaciminto = new JLabel("Fecha de nacimiento: ");
		gridConst.gridx = 0;
		gridConst.gridy = 7;
		gridConst.gridwidth=1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblFechaDeNaciminto, gridConst);
	 
		txtFechaDeNaciminto = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtFechaDeNaciminto, gridConst);
	*/
		JLabel lblNacionalidad = new JLabel("Nacionalidad: ");
		gridConst.gridx = 0;
		gridConst.gridy = 8;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblNacionalidad, gridConst);

		txtNacionalidad = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtNacionalidad, gridConst);
		
		JLabel lblLocalidad = new JLabel("Localidad: ");
		gridConst.gridx = 2;
		gridConst.gridy = 8;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblLocalidad, gridConst);

		txtLocalidad = new JTextField(10);
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtLocalidad, gridConst);
		
		JLabel lblProvincia = new JLabel("Provincia: ");
		gridConst.gridx = 0;
		gridConst.gridy = 9;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblProvincia, gridConst);

		txtProvincia = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtProvincia, gridConst);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal: ");
		gridConst.gridx = 2;
		gridConst.gridy = 9;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblCodigoPostal, gridConst);


		txtCodigoPostal = new JTextField(10);
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtCodigoPostal, gridConst);
		
		JLabel lblSituacionSocial = new JLabel("Sictuacion social: ");
		gridConst.gridx = 0;
		gridConst.gridy = 12;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblSituacionSocial, gridConst);


		txtSituacionSocial = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtSituacionSocial, gridConst);

		
		JLabel lblDomicilio = new JLabel("Domicilio: ");
		gridConst.gridx = 0;
		gridConst.gridy = 13;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDomicilio, gridConst);


		txtDomicilio = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtDomicilio, gridConst);
		
		JLabel lblEmail = new JLabel("Email: ");
		gridConst.gridx = 0;
		gridConst.gridy = 14;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblEmail, gridConst);


		txtEmail = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtEmail, gridConst);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		gridConst.gridx = 0;
		gridConst.gridy = 15;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTelefono, gridConst);


		txtTelefono = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtTelefono, gridConst);
		

		gridConst.anchor = GridBagConstraints.LINE_END;
		btnAceptar = new JButton("Postularse");
		gridConst.gridy = 16;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnAceptar.addActionListener(e -> {
			postularse();
		});
		this.add(btnAceptar, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_END;
		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 16;
		gridConst.gridx = 3;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
			frame.dispose();
		});
		this.add(btnCancelar, gridConst);
		

	}



//	Setea el resultado de la busqueda en la tabla
	public void setResultadoBusqueda(List<Familiar> listaResultado, boolean actualizar) {
		this.tablaFamiliares.setContribuyentes(listaResultado);
		if(actualizar) {
			this.tablaFamiliares.fireTableDataChanged();
		}
	}
	
	public void actualizarTabla() {
		this.tablaFamiliares.setContribuyentes(familiares);
		this.tablaFamiliares.fireTableDataChanged();
	}

//	Obtiene el parametro de busqueda, realiza la busqueda a traves del gestor de BD y por ultimo actualiza la tabla
	public void postularse() {
		

		try {
			
			

			Postulante postulante= new Postulante();
			postulante.setLegajo(Integer.valueOf(this.txtLegajo.getText()));
			postulante.setFamiliaresACargo(Integer.valueOf(this.txtFamiliaresACargo.getText()));
			postulante.setNombre(txtNombre.getText());
			postulante.setApellido(txtApellido.getText());
			//postulante.setFechaDeNacimiento(this.txtFechaDeNaciminto.getText());
			postulante.setCarrera(this.cmbCarrera.getSelectedItem().toString());
			postulante.setNacionalidad(this.txtNacionalidad.getText());
			postulante.setLocalidad(this.txtLocalidad.getText());
			postulante.setProvincia(this.txtProvincia.getText());
			postulante.setSituacionSocial(this.txtSituacionSocial.getText());
			postulante.setDomicilio(this.txtDomicilio.getText());
			postulante.setCodigoPostal(this.txtCodigoPostal.getText());
			postulante.setEmail(this.txtEmail.getText());
			postulante.setTelefono(Long.valueOf(this.txtTelefono.getText()));
			
			
			int ingresos=0;
			for(Familiar familiar: familiares) {
				ingresos+=familiar.getIngresos();
			}
			
			postulante.setCantidadDeFamiliares(familiares.size());
			double promedio = 60; //= ingresos/familiares.size();
			System.out.print("INGRESOS: " + ingresos+" PROMEDIOS: "+promedio);
			
			//TODO Calcular puntaje con la fórmula
			//TODO Método postular en Controller
			//promedio = 60;
			
			puntaje = 89;
			postulante.setMatAprobadas(6);
			postulante.setCarrera("Civil");
			
			controller.postular(postulante,puntaje);
	
			/*
	ESTO NO SE SI ES PROMEDIO O TOTAL
	private int ingresoFamiliar; -----------*/
			///llamar a bd y guardar 
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.OK_OPTION);
		}
		
	}
	

	public Frame getFramePadre() {
		return framePadre;
	}

	public void setFramePadre(Frame framePadre) {
		this.framePadre = framePadre;
	}


	
}
