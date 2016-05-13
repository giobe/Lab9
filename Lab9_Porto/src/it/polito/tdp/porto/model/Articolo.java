package it.polito.tdp.porto.model;

import java.util.ArrayList;

public class Articolo {

	int idA;
	int anno;
	String titolo;
	public Articolo() {
	}
	public Articolo(int idA, int anno, String titolo) {
		this.idA = idA;
		this.anno = anno;
		this.titolo = titolo;
	}
	public int getIdA() {
		return idA;
	}
	public int getAnno() {
		return anno;
	}
	public String getTitolo() {
		return titolo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idA;
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
		Articolo other = (Articolo) obj;
		if (idA != other.idA)
			return false;
		return true;
	}
	
	
	
}
