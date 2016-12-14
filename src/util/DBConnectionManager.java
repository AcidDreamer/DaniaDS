package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnectionManager {
		//Φορτώνουμε το jdbc 
        private Connection connection;
        public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection(dbURL, user, pwd);
        }
        //και παίρνουμε την σύνδεση
        public Connection getConnection() {
                return this.connection;
        }
}
