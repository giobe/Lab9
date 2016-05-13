package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.polito.tdp.porto.model.Articolo;


public class ArticoloDAO {

Connection con = DBConnect.getConnection();
	
	public ArrayList<Articolo> caricaArticoliAll(){
		
		ArrayList<Articolo> articoli = new ArrayList<Articolo>();
		try {
			String sql = "select * from article";
			Statement st = con.prepareStatement(sql);
	         ResultSet res= st.executeQuery(sql);
	         while(res.next()){
	        	 Articolo f1 = new Articolo(res.getInt("eprintid"),res.getInt("year"),res.getString("title"));
	        	
	        	 articoli.add(f1);
	         }
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articoli;
		
	}
	
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
