package Interfaz;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import clases.Alumno;

public class TablaAlumnos extends AbstractTableModel{


	private static SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Alumno> alumnos=new ArrayList();
	private String[] columnas = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio"};
	
//	Getters y Setters
	
	public List<Alumno> getContribuyentes() {
		return alumnos;
	}
	
	public void setContribuyentes(List<Alumno> personas) {
		this.alumnos = personas;
	}
	
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	@Override 
	public int getColumnCount() {
		return this.columnas.length;
	}

	@Override
	public int getRowCount() {
		return this.alumnos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.alumnos.get(rowIndex).getDocumento();
			break;
		case 1:
			valor = this.alumnos.get(rowIndex).getNombre();
			break;
		case 2:
			valor = this.alumnos.get(rowIndex).getApellido();
			break;
		case 3:
			//String fechaNacimiento = formatFecha.format(this.alumnos.get(rowIndex).getFecha_de_nacimiento());
			//valor = fechaNacimiento;
			break;
		case 4:
			//valor = this.alumnos.get(rowIndex).getDireccion();
			break;
		default:
			break;
		}
		return valor;
	}

}
