package it.polito.tdp.lab04.model;

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

}
