package com.autogenerado.DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public String url = "jdbc:sqlserver://localhost;database=ventas;trustServerCertificate=true";
	public String username = "sa";
	public String pass = "Athousandyear$1";
	public Connection sqlConnect() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,pass);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
