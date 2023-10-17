package dbconnection_ds;
// clase que se encarga de la conexion a la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDS {

	static Connection con;
	
	public static Connection ConectarDB() {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/computacion_aj", "root", "17911977");
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos");
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
		return con;
	}
}
			
		
