package SistemaDeBecas;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openrdf.query.QueryEvaluationException;

import com.complexible.common.rdf.query.resultio.HTMLQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.UpdateQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.IRI;
import com.stardog.stark.Values;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.SelectQueryResultHandler;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.ResultWritingFailed;
import com.stardog.stark.query.io.SelectQueryResultWriter;

import clases.Postulante;

public class Controller {
	
	public void postular(Postulante postulante, int puntaje) {
		
		Connection connection = ConnectionConfiguration
		        .to("")
		        .server("http://localhost:5820")
		        .database("B")
		        .reasoning(true)
		        .credentials("admin", "admin")
		        .connect()
		        .as(Connection.class);
		
		int anioConv = 2020;
		String nombre = postulante.getNombre();
		String convocatoria = "Convocatoria"+anioConv;
		String carrera = postulante.getCarrera();
		
		
		UpdateQuery aQuery = connection.update("INSERT { \r\n" +  
				"   :"+ nombre +" rdf:type :Alumno . \r\n" + 
				"   :"+ nombre +" :nombre '"+nombre+"' . \r\n" +
				"   :"+ nombre +" :apellido '"+postulante.getApellido()+"' . \r\n" +
				"   :"+ nombre +" :legajo "+postulante.getLegajo()+" . \r\n" + 
				"   :"+ nombre +" :matAprobadas "+postulante.getMatAprobadas()+" . \r\n" + 
				"   :"+ nombre +" :promedioGeneral "+postulante.getPromedio()+" . \r\n" + 
				"   :"+ nombre +" :puntaje "+puntaje+" . \r\n" + 
				"   :"+ nombre +" :inscriptoACarrera :"+ carrera +" . \r\n" + 
				"   :"+ carrera +" :nombre '"+ carrera +"' . \r\n" + 
				"   :"+ nombre +" :inscriptoABeca :"+convocatoria+" .\r\n" + 
				"   :"+convocatoria+" :anioBeca "+ anioConv +".}\r\n" +
				"WHERE { \r\n" + 
				"   FILTER NOT EXISTS \r\n" + 
				"   { \r\n" + 
				"     :"+ nombre +" rdf:type :Alumno . \r\n" + 
				"   } \r\n" + 
				"} ");
		
		//Ejecuto la query y cierro la conexión.
		aQuery.execute();
		
		connection.close();

	}
	
	public String buscar(int promedio, int matAprobadas, int anioBeca) {
		 Connection connection = ConnectionConfiguration
			        .to("")
			        .server("http://localhost:5820")
			        .database("B")
			        .reasoning(true)
			        .credentials("admin", "admin")
			        .connect()
			        .as(Connection.class);
		 
	SelectQuery aQuery = connection.select("SELECT DISTINCT ?Nombre ?Apellido ?Legajo ?Carrera ?Puntaje \r\n" + 
				"        WHERE{ \r\n" + 
				"          ?Alumno a :Alumno. \r\n" + 
				"          ?Alumno :nombre ?Nombre.\r\n" +
				"          ?Alumno :apellido ?Apellido.\r\n" + 
				"          ?Alumno :legajo ?Legajo.\r\n" + 
				"          ?Alumno :matAprobadas ?matAprobadas. \r\n" + 
				"          ?Alumno :promedioGeneral ?promedio. \r\n" + 
				"          ?Alumno :inscriptoACarrera ?C. \r\n" + 
				"          ?C :nombre ?Carrera. \r\n" + 
				"          ?Alumno :inscriptoABeca ?convBeca.\r\n" + 
				"          ?convBeca :anioBeca ?anioBeca.\r\n" +
				"          ?Alumno :puntaje ?Puntaje.\r\n" + 
				"          FILTER (?promedio >= "+promedio+").\r\n" + 
				"          FILTER (?anioBeca = "+anioBeca+").\r\n" + 
				"          FILTER (?matAprobadas >= "+matAprobadas+").}\r\n" + 
				"          ORDER BY DESC (?Puntaje)");
	        		
		
		
	                SelectQueryResult selectQueryResult = aQuery.execute();
	                ByteArrayOutputStream stream = new ByteArrayOutputStream();
       
	                try{
	                    QueryResultWriters.write(selectQueryResult, stream, HTMLQueryResultWriter.FORMAT);
	                } catch (IOException e) {
	                    System.out.println("ERROR");
	                }

	               
	                String finalString = new String(stream.toByteArray()).replaceAll("\\^\\^&lt;","");
	                finalString.replaceAll("\\http://www.semanticweb.org/joser/ontologies/2020/7/untitled-ontology-14#float&gt", "");
	                finalString.replaceAll("\\&quot", ""); //no anda
	                finalString.replaceFirst("\\border=1", "border-collapse = collapse;"); //no anda

	                
	                connection.close();
	                
	                return finalString;
	       
		 
	 }
	 
}