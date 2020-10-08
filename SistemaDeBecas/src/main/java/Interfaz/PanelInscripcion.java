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
	private JTextField txtNacionalidad;
	private JTextField txtLocalidad;
	private JTextField txtProvincia;
	private JTextField txtCodigoPostal;
	private JTextField txtSituacionSocial;
	private JTextField txtDomicilio;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JComboBox<String> cmbVivienda;
	private JComboBox<String> cmbSalud;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTable tabla;
	private TablaFamiliares tablaFamiliares = new TablaFamiliares();
	private JTextField txtObs;

	private Controller controller = new Controller();

	private int seleccion = -1;
	private List<Familiar> familiares=new ArrayList();
	private int puntaje;
	
	private Frame framePadre;
	
	public PanelInscripcion() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}
	
	//Datos para puntaje
	int salarioMinimo = 16875;
	int canastaBasicaPromedio = 11930;

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
		

		txtFamiliaresACargo = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtFamiliaresACargo, gridConst);

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
		
	/*	JLabel lblSituacionSocial = new JLabel("Situacion social: ");
		gridConst.gridx = 0;
		gridConst.gridy = 12;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblSituacionSocial, gridConst);


		txtSituacionSocial = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtSituacionSocial, gridConst);
*/
		
		JLabel lblDomicilio = new JLabel("Domicilio: ");
		gridConst.gridx = 0;
		gridConst.gridy = 12;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDomicilio, gridConst);


		txtDomicilio = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtDomicilio, gridConst);
		
		JLabel lblEmail = new JLabel("Email: ");
		gridConst.gridx = 0;
		gridConst.gridy = 13;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblEmail, gridConst);


		txtEmail = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtEmail, gridConst);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		gridConst.gridx = 0;
		gridConst.gridy = 14;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTelefono, gridConst);


		txtTelefono = new JTextField(10);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtTelefono, gridConst);
		
		JLabel lblVivienda = new JLabel("Vivienda: ");
		gridConst.gridx = 0;
		gridConst.gridy = 15;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblVivienda, gridConst);
		
		String [] vivienda = { "Propietario sin deuda", "Propietario con deuda", "Inquilino u ocupante" };
		cmbVivienda = new JComboBox<String>(vivienda);
		cmbVivienda.setEditable(false);
		cmbVivienda.setPreferredSize(new Dimension(100, 20));
		gridConst.gridx = 1;
		this.add(cmbVivienda, gridConst);
				
		JLabel lblSalud = new JLabel("Salud: ");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblSalud, gridConst);
		
		String [] salud = { "Tiene obra social", "Cobertura parcial", "No tiene obra social" };
		cmbSalud = new JComboBox<String>(salud);
		cmbSalud.setEditable(false);
		cmbSalud.setPreferredSize(new Dimension(100, 20));
		gridConst.gridx = 3;
		this.add(cmbSalud, gridConst);
		
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
			this.setVisible(false);
			PanelInicio nuevo = new PanelInicio();
			nuevo.setFramePadre(framePadre);
			framePadre.pack();
			((JFrame) framePadre).setContentPane(nuevo);
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
			//postulante.setSituacionSocial(this.txtSituacionSocial.getText());
			postulante.setDomicilio(this.txtDomicilio.getText());
			postulante.setCodigoPostal(this.txtCodigoPostal.getText());
			postulante.setEmail(this.txtEmail.getText());
			postulante.setTelefono(Long.valueOf(this.txtTelefono.getText()));
			
			
			int ingresos=0, miembrosEconomicamenteActivos = 0;
			for(Familiar familiar: familiares) {
				ingresos+=familiar.getIngresos();
				if(ingresos > 0) {
					miembrosEconomicamenteActivos++;
				}
			}
			
			postulante.setCantidadDeFamiliares(familiares.size());
			double promedio = Math.random() * (61) + 30 ; 
			postulante.setPromedio((int) promedio);
			
			
			int pIngresos, pActLaboral = 0, pVivienda = 0, pSalud = 0, pDependencia, pPromedio, pRendimiento, pDesarrollo;
			
			//Puntaje Ingresos
			int canastaBasicaPostulante = familiares.size() * canastaBasicaPromedio; 
			if(ingresos <= canastaBasicaPostulante) {
				pIngresos = 30;
			}
			else if(ingresos >= (2*canastaBasicaPostulante)) {
				pIngresos = 0;
			}
			else {
				pIngresos = (int) (30 - 0.15 * (familiares.size() - ingresos));
			}
			
			//Puntaje Condicion Actividad Laboral
			
			
			int puntajeMiembros = 0;
			
			for (Familiar familiar: familiares) {
					if(familiar.getIngresos() == 0) {
						puntajeMiembros = puntajeMiembros + 5;
					}
					else if(familiar.getIngresos() < salarioMinimo) {
						puntajeMiembros = puntajeMiembros + 3;
					}
			}
			
			if(miembrosEconomicamenteActivos>0) {
			pActLaboral = puntajeMiembros/miembrosEconomicamenteActivos;
			}
			
			//Puntaje Vivienda - Extrae de cmbVivienda: 0 sin deudas - 1 con deudas - 2 sin propiedad
			int condicionVivienda = this.cmbVivienda.getSelectedIndex();
			switch (condicionVivienda) {
			case 0:
				pVivienda = 0;
				break;
			case 1:
				pVivienda = 3;
				break;
			case 2:
				pVivienda = 5;
				break;
			}
			
			//Puntaje Salud - Extrae de cmbSalud: 0 tiene obra - 1 cobertura parcial - 2 no tiene
			int condicionSalud = this.cmbSalud.getSelectedIndex();
			switch (condicionSalud) {
			case 0:
				pSalud = 0;
				break;
			case 1:
				pSalud = 3;
				break;
			case 2:
				pSalud = 5;
				break;
			}
			
			//Puntaje Dependencia
			if (familiares.size() - miembrosEconomicamenteActivos == 0) {
				pDependencia = 0;
			}
			else if(familiares.size() - miembrosEconomicamenteActivos > 2) {
				pDependencia = 5;
			}
			else{
				pDependencia = 3;				
			}
			
			//Puntaje Rendimiento académico
			int materiasAprobadas =  (int) (Math.random() * (11));
			if(materiasAprobadas == 0) {
				pRendimiento = 4;
				pPromedio = 14;
			}
			else {
				int materiasRegularizadas = (int) (Math.random() * (41 - materiasAprobadas));
				pRendimiento = materiasAprobadas/materiasRegularizadas * 10;
			//Puntaje Promedio: Se recuperaria del sysacad
				pPromedio = (int) (promedio/10 * 3.5);
			}
			
			//Puntaje Plan Desarrollo: Evaluado por comisión local
			pDesarrollo = (int) (Math.random() * 5 + 1);


			puntaje = pIngresos + pActLaboral + pVivienda + pSalud + pDependencia + pPromedio + pRendimiento + pDesarrollo;
			postulante.setMatAprobadas(materiasAprobadas);
			
			try{ 
				controller.postular(postulante,puntaje); 
				JOptionPane.showMessageDialog(null, "Se postuló al alumno "+postulante.getNombre()+" "+postulante.getApellido()+".", "Postulación exitosa.", JOptionPane.OK_OPTION);
				limpiarCampos();
			} 
			catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se pudo postular el alumno", "Error", JOptionPane.OK_OPTION);
			}

			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.OK_OPTION);
		}
		
	}
	
	private void limpiarCampos(){
		txtLegajo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		lblFamiliaresACargo.setText("");
		txtFamiliaresACargo.setText("");
		txtNacionalidad.setText("");
		txtLocalidad.setText("");
		txtProvincia.setText("");
		txtCodigoPostal.setText("");
		txtDomicilio.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		for(int i=(familiares.size()-1); i>0; i--) {
			familiares.remove(i);
		}
		actualizarTabla();
	}
	

	public Frame getFramePadre() {
		return framePadre;
	}

	public void setFramePadre(Frame framePadre) {
		this.framePadre = framePadre;
	}


	
}
