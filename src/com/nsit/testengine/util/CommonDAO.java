package com.nsit.testengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface CommonDAO {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
		String dbUrl = resourceBundle.getString("dbUrl");
		String userId = resourceBundle.getString("userid");
		String password = resourceBundle.getString("password");
		Connection connection = DriverManager.getConnection(dbUrl,userId,password);
		return connection;
	}
}
