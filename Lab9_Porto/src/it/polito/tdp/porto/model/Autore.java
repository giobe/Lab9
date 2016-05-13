package it.polito.tdp.porto.model;

public class Autore implements Comparable {
	
	int idCreator;
	String cognome;
	String nome;
	
	public Autore(int idCreator, String cognome, String nome) {
		this.idCreator = idCreator;
		this.cognome = cognome;
		this.nome = nome;
	}
	public Autore() {
	}
	public int getIdCreator() {
		return idCreator;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNome() {
		return nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCreator;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		if (idCreator != other.idCreator)
			return false;
		return true;
	}
	
	public String toString(){
		return nome+" "+cognome;
	}
	@Override
	public int compareTo(Object arg0) {
		Autore aut = (Autore) arg0;
		if(this.getNome().compareTo(aut.getNome())==0)
			return this.getCognome().compareTo(aut.getCognome());
		return this.getNome().compareTo(aut.getNome());
	}

}
