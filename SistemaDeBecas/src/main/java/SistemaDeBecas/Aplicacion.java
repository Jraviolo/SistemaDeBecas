package SistemaDeBecas;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.Values;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.ResultWritingFailed;
import com.stardog.stark.vocabs.RDF;
import com.stardog.stark.vocabs.RDFS;

public class Aplicacion {
	 public static void main(String[] args) {
		 
		 createAdminConnection();
		 ConnectionConfiguration connection = ConnectionConfiguration
			        .to("SistemaBecas")
			        .server("http://localhost:5820")
			        .reasoning(true)
			        .credentials("admin", "admin");

		 ConnectionPool connectionPool = createConnectionPool(connection);

		 System.out.println("Hola Mundooooo ahre");
		 
		 /*SelectQuery alumnos = connection.select("SELECT ?persona WHERE { ?persona inscriptoACarrera: ?CarreraUTNFRSF .}");
			SelectQuery resultadoAlumnos = alumnos.execute();
			stringResultadoAlumnos = resultadoAlumnos.toString();
			
		 SelectQuery query = connection.select("PREFIX foaf:<http://xmlns.com/foaf/0.1/>" + "select * { ?s rdf:type foaf:Person }");
				TupleQueryResult tupleQueryResult = query.execute();
				QueryResultIO.writeTuple(tupleQueryResult,
				                         TextTableQueryResultWriter.FORMAT, System.out);
		*/
			
		//	SelectQuery query = connection.select("");
		 //   SelectQueryResult tupleQueryResult = query.execute();
		  try (Connection connection1 = getConnection(connectionPool)) { // obtains a Stardog connection from the pool

	            try {
	                // first start a transaction. This will generate the contents of the databse from the N3 file.
	                connection1.begin();
	                // declare the transaction
	                
	                SelectQuery alumnos = connection1.select("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "SELECT ?persona WHERE { ?persona rdf:type :Alumno}");
	    			SelectQueryResult resultadoAlumnos = alumnos.execute();
	    			String stringResultadoAlumnos = resultadoAlumnos.toString();
	    			System.out.print("Resultado: " + stringResultadoAlumnos);
	    			try {
						QueryResultWriters.write(resultadoAlumnos, System.out, TextTableQueryResultWriter.FORMAT);
					} catch (ResultWritingFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	                // Query the database to get our list of Marvel superheroes and print the results to the console
	 //               SelectQuery query = connection1.select("");
	      //          SelectQueryResult tupleQueryResult = query.execute();
		//    System.out.println("Resultado SelectQuery: "+ tupleQueryResult.toString());
		    }finally {
                
                connectionPool.shutdown();
            }
		  }
	 }
	 public static void createAdminConnection() {
	        try (final AdminConnection aConn = AdminConnectionConfiguration.toServer("http://localhost:5820")
	                .credentials("admin", "admin")
	                .connect()) {

	            // A look at what databses are currently in Stardog - needed api and http
	            aConn.list().forEach(item -> System.out.println(item+ " item "));

	            // Checks to see if the 'myNewDB' is in Stardog. If it is, we are going to drop it so we are
	            // starting fresh
	        /**    if (aConn.list().contains("DBPrueba")) {
	                aConn.drop("DBPrueba");
	            }

	            // Convenience function for creating a non-persistent in-memory database with all the default settings.
	            aConn.disk("DBPrueba").create();
	            aConn.close();
	        **/}
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
	 	
}
