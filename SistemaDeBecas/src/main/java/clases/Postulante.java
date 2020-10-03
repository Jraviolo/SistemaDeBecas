package clases;

import java.util.ArrayList;
import java.util.Date;

public class Postulante {
	private int legajo;
	private String carrera;
	private int cantidadDeFamiliares;
	private int ingresoFamiliar;
	private int familiaresACargo;
	private String nombre;
	private String apellido;
	private int matAprobadas;
	private int promedio;
	private int puntaje;
	private Date fechaDeNacimiento;
	
	
	private String nacionalidad;
	private String localidad;
	private String provincia;
	private String situacionSocial;
	private String domicilio;
	private String codigoPostal;
	private String email;
	private long telefono;
	
	/*
	 * Get and sets
	 * */
	
	public int getLegajo() {
		return legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getMatAprobadas() {
		return matAprobadas;
	}
	public void setMatAprobadas(int matAprobadas) {
		this.matAprobadas = matAprobadas;
	}
	public int getPromedio() {
		return promedio;
	}
	public void setPromedio(int promedio) {
		this.promedio = promedio;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public int getCantidadDeFamiliares() {
		return cantidadDeFamiliares;
	}
	public void setCantidadDeFamiliares(int cantidadDeFamiliares) {
		this.cantidadDeFamiliares = cantidadDeFamiliares;
	}
	public int getIngresoFamiliar() {
		return ingresoFamiliar;
	}
	public void setIngresoFamiliar(int ingresoFamiliar) {
		this.ingresoFamiliar = ingresoFamiliar;
	}
	public int getFamiliaresACargo() {
		return familiaresACargo;
	}
	public void setFamiliaresACargo(int familiaresACargo) {
		this.familiaresACargo = familiaresACargo;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getSituacionSocial() {
		return situacionSocial;
	}
	public void setSituacionSocial(String situacionSocial) {
		this.situacionSocial = situacionSocial;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	
	
}
