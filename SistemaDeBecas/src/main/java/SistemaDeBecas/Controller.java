package SistemaDeBecas;

public class Controller {

	Connection connection = ConnectionConfiguration
			.to("http://localhost:5820/DBPrueba")
			.reasoning(true)
			.connect()
			.as(Connection.class);
	
}
