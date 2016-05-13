package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.porto.db.ArticoloDAO;
import it.polito.tdp.porto.db.AutoreDAO;

public class Registro {

	List<DefaultEdge> passaggi;
	ArrayList<Autore> autori = new ArrayList<Autore>();
	ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	
	SimpleGraph<Autore,DefaultEdge> grafo = new SimpleGraph<Autore,DefaultEdge>(DefaultEdge.class);
	
	public void caricaArticoli(){
		ArticoloDAO dao = new ArticoloDAO();
		articoli = dao.caricaArticoliAll();
		dao.close();
	}
	
	public void GeneraGrafo(){
		//creo vertici
		AutoreDAO dao = new AutoreDAO();
		this.autori = dao.caricaAutoriAll();
		for(Autore a : autori){
			grafo.addVertex(a);
		}
		
		//creo archi
		for(Autore a1 : autori){
			for(Autore a2 : autori){
				if(a1.equals(a2)==false){
				int arco = dao.articoliDiAutori(a1, a2);
				if(arco!=0)
					Graphs.addEdgeWithVertices(grafo, a1, a2);
			
				}
			}
		}
		
		dao.close();
	}
	
	public ArrayList<Autore> getAutori(){
		Collections.sort(autori);
		return autori;
	}
	
	public Articolo getArticolo(int cod){
		for(Articolo a : articoli){
			if(a.getIdA()==cod)
				return a;
		}
		return null;
	}
	
	
	public String coautori(Autore a){
		String tot="";
		ArrayList<Autore> coautori1 = new ArrayList<Autore>();
		ArrayList<Autore> coautori =  (ArrayList<Autore>) Graphs.neighborListOf(grafo, a);
		for(Autore aut : coautori){
			
			if(coautori1.contains(aut)==false){
			tot+=aut.getNome()+" "+aut.getCognome()+" ; ";
			coautori1.add(aut);
			}
		}
		return tot;
		
	}
	
	public String articoliCheCollegano(Autore a,Autore a1){
		//1- faccio BreadthFirstIterator,, ovvero visita in ampiezza, mi salvo vertici fino a che arrivo ad a1
		//(uso .next=="") a quel punto faccio getEdges tra i vertici che ho (da a ad a1), quindi dalla lista di edges
		// prendo il peso di ciascuno e lo passo alla funzione getArticolo(int) per avere nomeArtiolo
		
		//2-Faccio dijstra tra a ed a1 lui si calcola percorso minimo in base a peso archi che è id_aticolo
		// a sto punto ho connessione e faccio sul Path un get Edges tra vertici sequenziali, cosi ho lista Edges
		// prendo peso di ciascun Edge e è int che uso per fare getArticolo(int).
		
		String tot="";
		if(!a.equals(a1)){
			AutoreDAO dao = new AutoreDAO();
		DijkstraShortestPath<Autore, DefaultEdge> dijkstra = new DijkstraShortestPath<Autore, DefaultEdge>(grafo, a, a1);
		passaggi = dijkstra.getPathEdgeList();
		if(passaggi==null){
			tot="non esiste collegamento tra i due autori";
		}
		else if(passaggi.size()==1){
			int art = dao.articoliDiAutori(a, a1);
			tot=" i due autori selezionati son coautori di ( "+this.getArticolo(art).getTitolo()+" )";
		}
		else if(passaggi.size()>1){
		for(DefaultEdge e : passaggi){
			Autore a2 = grafo.getEdgeSource(e);
			Autore a3 = grafo.getEdgeTarget(e);
			int art = dao.articoliDiAutori(a2, a3);
			Articolo pub = this.getArticolo(art);
			tot+=pub.getTitolo()+" ; ";
		}
		}
		dao.close();
		return tot;
		}
		else
			return "non ha senso metter stessa partenza e destinazione";
	}
	
	public String trovaCluster(){
		List<Autore> autorini =new ArrayList<Autore>();
		String tot="";
		int i=1;
		
		for(Autore a : autori){
			if(!autorini.contains(a)){
		DepthFirstIterator<Autore, DefaultEdge> bfs = new DepthFirstIterator<Autore,DefaultEdge>(grafo, a);
		
		while(bfs.hasNext()){
			if(bfs.next()!=null){
			autorini.add(bfs.next());
			System.out.println(bfs.next().getNome()+" "+bfs.next().getCognome()+" ;");
			tot+=""+bfs.next().getNome()+" "+bfs.next().getCognome()+" ;";
			}
		       }
		i++;
		System.out.println("uscito da "+i);
		tot+="\n inizio cluster "+i+"\n";
			}
		}
		return tot;
	}
	
	
	public static void main(String args[]){
		
		Autore a = new Autore(1200,"Prinetto","Paolo Ernesto");
		Autore a1 = new Autore(2904,"Benso","Alfredo");
		Registro r = new Registro();
		//AutoreDAO d = new AutoreDAO();
		//d.articoliDiAutori(a, a1);
		
	}
	
}
