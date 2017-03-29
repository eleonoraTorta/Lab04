package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public Model(){    							
	}
	
	public List<Corso> getCorsi(){
		CorsoDAO c_dao = new CorsoDAO();
		return c_dao.getTuttiICorsi();	
	}
	
	public Studente cercaStudentePerMatricola(int matricola){
		StudenteDAO s_dao = new StudenteDAO();
		return s_dao.cercaStudente(matricola);
	}
	
	public List <Studente> cercaStudentiIscritti(Corso corso){
		CorsoDAO c_dao = new CorsoDAO();					
	    c_dao.getStudentiIscrittiAlCorso(corso);
	    List <Studente> elenco = new LinkedList <Studente>(); 
	    for(Studente s : corso.getIscritti()){
	    	elenco.add(this.cercaStudentePerMatricola(s.getMatricola()));
	    }
		return elenco;				
	}

	public boolean esiste(Studente s) {
		StudenteDAO s_dao = new StudenteDAO();
		Studente studente = s_dao.cercaStudente(s.getMatricola());
		if(studente.equals(null)){
			return false;
		}
		return true;
	}

	public List<Corso> cercaCorsiDatoStudente(Studente s) {
		StudenteDAO s_dao = new StudenteDAO();
		s_dao.cercaCorsi(s);
		return s.getCorsi();
	
	}

	public boolean isIscritto(Studente s, Corso corso) {
		LinkedList <Studente> iscritti = new LinkedList <Studente>(this.cercaStudentiIscritti(corso));
		if(iscritti.contains(s)){
			return true;
		}
		return false;
	}

	public boolean iscrivi(Studente s, Corso corso) {
		CorsoDAO c_dao = new CorsoDAO();
		return c_dao.iscrivi(s, corso);
	}
}
