package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
static Connection con =null;
	
	public static Connection connect(){
		try {
		Class.forName("com.mysql.jdbc.Driver");//1st step
		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorydb","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (con);
	}
}
