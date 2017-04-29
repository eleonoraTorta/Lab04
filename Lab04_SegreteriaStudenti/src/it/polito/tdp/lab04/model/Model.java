package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	private StudenteDAO s_dao;
	private CorsoDAO c_dao;
	
	
	public Model(){  
		s_dao = new StudenteDAO();
		c_dao = new CorsoDAO();
	}
	
	public List<Corso> getCorsi(){
		return c_dao.getTuttiICorsi();	
	}
	
	public Studente cercaStudentePerMatricola(int matricola){
		return s_dao.cercaStudente(matricola);
	}
	
	public Corso cercaCorso(String codice){
		Corso corso = new Corso(codice,0,null,0);
		c_dao.getCorso(corso);
		return corso;
	}
	
	
	public List <Studente> cercaStudentiIscritti(Corso corso){					
	    c_dao.getStudentiIscrittiAlCorso(corso);
  //    return corso.getStudentiBis();										opzione bis
	    List <Studente> elenco = new LinkedList <Studente>(); 
	    for(Studente s : corso.getIscritti()){
	    	elenco.add(this.cercaStudentePerMatricola(s.getMatricola()));
	    }
		return elenco;				
	}
	
	public List<Corso> cercaCorsiDatoStudente(Studente s) {
		s_dao.cercaCorsi(s);
	//	return studente.getCorsiBis();
		LinkedList <Corso> corsi = new LinkedList <Corso>();
		for(Corso c : s.getCorsi()){
			corsi.add( this.cercaCorso(c.getCodins()));
		}
		return corsi;
	
	}


	public boolean isIscritto(Studente s, Corso corso) {
		List <Studente> iscritti = this.cercaStudentiIscritti(corso);
		if(iscritti.contains(s)){
			return true;
		}
		return false;
	}

	public boolean iscrivi(Studente s, Corso corso) {
		return c_dao.iscrivi(s, corso);
	}
	
	
}
