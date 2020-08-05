package SistemaDeBecas;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.SelectQuery;
import com.stardog.stark.query.SelectQueryResult;

public class Controller {
	Connection connection = ConnectionConfiguration
			.to("http://localhost:5820/DBPrueba")
			.reasoning(true)
			.connect()
			.as(Connection.class);
	
	
	SelectQuery query = connection.select("");
    SelectQueryResult tupleQueryResult = query.execute();
    
}
