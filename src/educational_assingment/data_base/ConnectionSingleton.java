package educational_assingment.data_base;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSingleton {
	private static Connection conn = null;
	
	public static Connection getInstance(){
		if(conn==null){
			try {
				String userName = "root";
				String password = "28051994";
				String url = "jdbc:mysql://localhost/educationalassigment";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("Database connection established");
			} catch (Exception e) {
				System.err.println("Cannot connect to database server");
				e.printStackTrace();
			}
			return conn;
		}else
			return conn;
	}
}