package SistemaDeBecas;

import java.util.concurrent.TimeUnit;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.query.SelectQueryResult;

public class Aplicacion {
	 public static void main(String[] args) {
		 System.out.println("Hola Mundooooo ahre");
		 createAdminConnection();
		 ConnectionConfiguration connection = ConnectionConfiguration
			        .to("DBPrueba")
			        .server("http://localhost:5820")
			        .reasoning(true)
			        .credentials("admin", "admin");
		 
		 
		 
			    //    .connect()
				//	.as(Connection.class);;
		 ConnectionPool connectionPool = createConnectionPool(connection);

			
			
		//	SelectQuery query = connection.select("");
		 //   SelectQueryResult tupleQueryResult = query.execute();
		  try (Connection connection1 = getConnection(connectionPool)) { // obtains a Stardog connection from the pool

	            try {
	                // first start a transaction. This will generate the contents of the databse from the N3 file.
	                connection1.begin();
	                // declare the transaction


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
	            aConn.list().forEach(item -> System.out.println(item));

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
