package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnect {

private static final String jdbcUrl="jdbc:mysql://localhost/porto?user=root&password=";

private static ComboPooledDataSource dataSource = null ;
	
public static Connection getConnection() {
	
	Connection conn;
	try {
		
		if(dataSource==null) {
			// creare ed attivare il Connection Pool
			dataSource = new ComboPooledDataSource() ;
			dataSource.setJdbcUrl(jdbcUrl);
		}
		
		return dataSource.getConnection();
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Errore nella connessione", e) ;
	}
}
	
}
