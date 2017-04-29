package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Studente {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String cds;
	private LinkedList <Corso> corsi = new LinkedList <Corso>();
//	private List <Corso> corsi;
	
	/**
	 * @param matricola
	 * @param cognome
	 * @param nome
	 * @param cds
	 */
	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cds
	 */
	public String getCds() {
		return cds;
	}

	/**
	 * @param cds the cds to set
	 */
	public void setCds(String cds) {
		this.cds = cds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cds == null) ? 0 : cds.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + matricola;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (cds == null) {
			if (other.cds != null)
				return false;
		} else if (!cds.equals(other.cds))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (matricola != other.matricola)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return matricola + " " + cognome + " " + nome + " " + cds;
	}

	public void addCorso(Corso c) {
		if(!corsi.contains(c)){
			corsi.add(c);
		}	
	}

	/**
	 * @return the corsi
	 */
	public LinkedList<Corso> getCorsi() {
		return corsi;
	}
	
	/*
	public List<Corso> getCorsiBis(){
		if(corsi == null){
			return new ArrayList <Corso>();
		}
		return corsi;
	}
	
	public void setCorsiBis (List <Corso> corsi){
		this.corsi = corsi;
	}
	*/
	
	
	
}
