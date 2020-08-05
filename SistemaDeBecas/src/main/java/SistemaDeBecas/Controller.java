package SistemaDeBecas;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.SelectQuery;
import com.stardog.stark.query.SelectQueryResult;

public class Controller {

	Connection connection = ConnectionConfiguration
			.to("http://localhost:5820/DBPrueba")
			.reasoning(true)
			.database("SistemaBecasDB")
			.credentials("admin","admin")
			.connect()
			.as(Connection.class);

	SelectQuery alumnos = connection.select("SELECT ?persona WHERE { ?persona inscriptoACarrera: ?CarreraUTNFRSF .}");
	SelectQuery resultadoAlumnos = alumnos.execute();
	stringResultadoAlumnos = resultadoAlumnos.toString();

	SelectQuery postulantes = connection.select("SELECT ?persona WHERE { ?persona inscriptoACarrera: ?CarreraUTNFRSF . ?persona tieneGrupoFamiliar: ."
			+ "?persona tienePromedioGeneral: . ?persona inscriptoABeca: . FILTER NOT EXISTS{ ?persona tieneCargoUTNFRSF . ?persona tieneTitulo . } }");
	SelectQuery resultadoPostulantes = postulantes.execute();
	stringResultadoPostulantes = resultadoPostulantes.toString();

	SelectQuery postulantesCalificados = connection.select();
	SelectQuery resultadoPostulantesCalificados = postulantesCalificados.execute();
	stringResultadoPostulantesCalificados = resultadoPostulantesCalificados.toString();

	SelectQuery # = connection.select();
	SelectQuery resultado# = #.execute();
	stringResultado# = resultado#.toString();
    
}
