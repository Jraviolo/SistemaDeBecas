package SistemaDeBecas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultFormat;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;

import com.complexible.common.rdf.query.resultio.HTMLQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.IRI;
import com.stardog.stark.Values;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.ResultWritingFailed;
import com.stardog.stark.vocabs.RDF;
import com.stardog.stark.vocabs.RDFS;

public class Aplicacion {
	 public static void main(String[] args) {
		 
		// createAdminConnection();
		 Connection connection = ConnectionConfiguration
			        .to("")
			        .server("http://localhost:5820")
			        .database("B")
			        .reasoning(true)
			        .credentials("admin", "admin")
			        .connect()
			        .as(Connection.class);
		 
		SelectQuery aQuery = connection.select(" SELECT DISTINCT ?Alumno ?legajo \r\n" + 
				"        WHERE{ \r\n" + 
				"          ?Alumno a :Alumno. \r\n" + 
				"          ?Alumno :tieneMateriaAprobada ?matAprobadas. \r\n" + 
				"          ?Alumno :promedioGeneral ?promedio. \r\n" + 
				"          ?Alumno :inscriptoACarrera ?nroLegajo. \r\n" + 
				"          ?Alumno :inscriptoABeca ?convBeca.\r\n" + 
				"          ?Alumno :legajo ?legajo.\r\n" + 
				"          FILTER (?promedio >= 5).}\r\n" + 
				"          ORDER BY DESC (?promedio)");
	        		

	                ByteArrayOutputStream stream = new ByteArrayOutputStream();

	                //Se ejecuta la consulta a la base de datos.
	                SelectQueryResult selectQueryResult = aQuery.execute();
	                try {
						QueryResultWriters.write(selectQueryResult, System.out, TextTableQueryResultWriter.FORMAT);
					} catch (ResultWritingFailed e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       
		 
	 }
/*
	//	 ConnectionPool connectionPool = createConnectionPool(connection);

		 System.out.println("Hola Mundooooo ahre");
		 
		 /*SelectQuery alumnos = connection.select("SELECT ?persona WHERE { ?persona inscriptoACarrera: ?CarreraUTNFRSF .}");
			SelectQuery resultadoAlumnos = alumnos.execute();
			stringResultadoAlumnos = resultadoAlumnos.toString();
		/
		 SelectQuery query = connection.select("PREFIX foaf:<http://xmlns.com/foaf/0.1/>" + "select * { ?s rdf:type foaf:Person }");
				TupleQueryResult tupleQueryResult = query.execute();
				QueryResultIO.writeTuple(tupleQueryResult,
				                         TextTableQueryResultWriter.FORMAT, System.out);
	*/
			
		//	SelectQuery query = connection.select("");
		 //   SelectQueryResult tupleQueryResult = query.execute();
		//  try (Connection connection1 = getConnection(connectionPool)) { // obtains a Stardog connection from the pool

	      //      try {
	                // first start a transaction. This will generate the contents of the databse from the N3 file.
	        //    	connection1.graph("http://www.semanticweb.org/ignab/ontologies/2020/5/TrabajoPrácticoOntologias#");
	         //       connection1.begin();
	            //    // declare the transaction
	                
	            /*    SelectQuery alumnos = connection1.select("PREFIX : <http://www.semanticweb.org/ignab/ontologies/2020/5/TrabajoPrácticoOntologias#> " + 
	                		"SELECT ?p WHERE {?p rdf:type :Persona}");
	    			SelectQueryResult resultadoAlumnos = alumnos.execute();	    		
	    			String stringResultadoAlumnos = resultadoAlumnos.toString();
	    			System.out.print("Cantidad de postulantes: " + count(resultadoAlumnos));
	    		/
	                
	        //        IRI PREFIX = Values.iri("<http://www.semanticweb.org/ignab/ontologies/2020/5/TrabajoPrácticoOntologias#>");

	       		SelectQuery aQuery = connection.select(" SELECT ?x WHERE { ?x a :inscriptoABeca}");
	        		
	        
		 	//SelectQuery aQuery = connection.select("SELECT ?x WHERE { ?x rdf:type :Persona}");
	                ByteArrayOutputStream stream = new ByteArrayOutputStream();

	                //Se ejecuta la consulta a la base de datos.
	                SelectQueryResult selectQueryResult = aQuery.execute();

	                //Aqui escribimos el resultado de la consulta en la varible ByteArrayOutputStream y con un formato de tabla con codigo HTML
	                try{
	                    //este metodo viene con las librerias de stardog
	                    QueryResultWriters.write(selectQueryResult, stream, HTMLQueryResultWriter.FORMAT);
	                } catch (IOException e) {
	                    System.out.println("ERROR");
	                }

	                //Se crea el String final a partir del ByteArrayOutputStream que se mostrara en el label del ranking de alumno. A dicho string le aplicamos el replaceAll para que quede "lindo" para el usuario y no muestre to do el IRI.
	                //Lo mas dificil fue sacar los ^ y los > ya que HTML los considera caracteres especiales.
	                String finalString = new String(stream.toByteArray()).replaceAll("\\^\\^&lt;http://www.semanticweb.org/joser/ontologies/2020/7/untitled-ontology-14#float&gt;","");
	                System.out.print("Cantidad de postulantes: " + count(selectQueryResult));
	               System.out.println("Resultado: " + finalString);
	               
	               System.out.println("*********      RESULTADO QUERY: " + selectQueryResult);
	               
	               
	               SelectQuery query2 = connection.select(
	            		   "SELECT ?x WHERE { ?x rdf:type :Alumno}"
	            	        );
	               SelectQueryResult resultado2 = query2.execute();
	               try {
						QueryResultWriters.write(resultado2, System.out, TextTableQueryResultWriter.FORMAT);
					} catch (ResultWritingFailed e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	     
	     
	        		IRI PERSONA = Values.iri("http://www.semanticweb.org/joser/ontologies/2020/7/untitled-ontology-14#Persona");

	        		SelectQuery aQuery2 = connection.select("SELECT ?x WHERE { ?x rdf:type :Persona}");

	        		aQuery2.parameter("type", PERSONA);
	        		try (SelectQueryResult aResult = aQuery2.execute()) {
	        			System.out.println("Number of Persons: " + count(aResult));
	        		}
	        		
	        	
	        		SelectQueryResult aResult = aQuery2.execute();
	        		System.out.println("Number of Persons: " + count(aResult));
	                
	                
	                try {
						QueryResultWriters.write(aResult, System.out, TextTableQueryResultWriter.FORMAT);
					} catch (ResultWritingFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
/*
		    }finally {
                
                connectionPool.shutdown();
            }
		  }
		  *
	 } 
*/
	 public static void createAdminConnection() {
	        try (final AdminConnection aConn = AdminConnectionConfiguration.toServer("http://localhost:5820")
	                .credentials("admin", "admin")
	                .connect()) {

	            // A look at what databses are currently in Stardog - needed api and http
	            aConn.list().forEach(item -> System.out.println(item+ " item "));

	            aConn.close();
	        }
	    }
	 
	 private static ConnectionPool createConnectionPool(ConnectionConfiguration connectionConfig) {
		 	ConnectionPoolConfig poolConfig = ConnectionPoolConfig
		 			.using(connectionConfig)
		 			.minPool(0)
		 			.maxPool(10)
		 			.expiration(300, TimeUnit.SECONDS)
		 			.blockAtCapacity(900, TimeUnit.SECONDS);

		 	return poolConfig.create();}

public static Connection getConnection(ConnectionPool connectionPool) {
    return connectionPool.obtain();
}
private static int count(final SelectQueryResult aResult) throws QueryEvaluationException {
	try {
		int count = 0;
		while (aResult.hasNext()) {
			count++;
			aResult.next();
		}

		return count;
	}
	finally {
		aResult.close();
	}
}
}
