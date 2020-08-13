package Interfaz;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import clases.Alumno;
import clases.Familiar;

public class TablaFamiliares extends AbstractTableModel{


	private static SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Familiar> familiares=new ArrayList();
	private String[] columnas = {"Nombre","Ingresos"};
	
//	Getters y Setters
	
	public List<Familiar> getContribuyentes() {
		return familiares;
	}
	
	public void setContribuyentes(List<Familiar> personas) {
		this.familiares = personas;
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
		return this.familiares.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.familiares.get(rowIndex).getNombre();
			break;
		case 1:
			valor = this.familiares.get(rowIndex).getIngresos();
			break;
		default:
			break;
		}
		return valor;
	}

}
