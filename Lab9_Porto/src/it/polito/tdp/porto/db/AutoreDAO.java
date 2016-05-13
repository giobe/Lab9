package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import it.polito.tdp.porto.model.Autore;

public class AutoreDAO {

	Connection con = DBConnect.getConnection();
	
	public ArrayList<Autore> caricaAutoriAll(){
		
		ArrayList<Autore> autori = new ArrayList<Autore>();
		try {
			String sql = "select * from creator";
			Statement st = con.prepareStatement(sql);
	         ResultSet res= st.executeQuery(sql);
	         while(res.next()){
	        	 Autore f1 = new Autore(res.getInt("id_creator"),res.getString("family_name"),res.getString("given_name"));
	        	
	        	 autori.add(f1);
	         }
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autori;
		
	}
	
	public Integer articoliDiAutori(Autore a, Autore a1){
		//ArrayList<Integer> articoli = new ArrayList<Integer>();
		
		try {
			String sql = "select distinct A1.eprintid  from authorship A1, authorship A2  where A1.id_creator='"+a.getIdCreator()+"' and A2.id_creator='"+a1.getIdCreator()+"' and A1.eprintid=A2.eprintid";
			Statement st = con.prepareStatement(sql);
	         ResultSet res= st.executeQuery(sql);
	         if(res.next()){
	        	 int art = res.getInt("eprintid");
	        	
	        	 return art;
	         }
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
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
