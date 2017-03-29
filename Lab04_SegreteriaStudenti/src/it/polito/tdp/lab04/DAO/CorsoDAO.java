package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();           //stringa connessione
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(rs.getString("codins"),
							   	rs.getInt("crediti"),
							   	rs.getString("nome"),
							   	rs.getInt("pd")	);
				// Aggiungi il nuovo Corso alla lista
				corsi.add(c);
			}
			
			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		String sql = "SELECT * "+
					 "FROM corso "+
					 "WHERE codins =?";
		try {
			Connection conn = ConnectDB.getConnection();           
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){							
	
				corso.setCrediti(rs.getInt("crediti"));
				corso.setNome(rs.getString("nome"));
				corso.setPd(rs.getInt("pd"));
			}
			conn.close();
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql = "SELECT matricola "+
					"FROM iscrizione "+
					"WHERE codins =?";
		
	try {
		Connection conn = ConnectDB.getConnection();           
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, corso.getCodins());
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()){
			Studente s= new Studente(rs.getInt("matricola"), null,null,null);
			corso.addStudente(s);
		}
		conn.close();
		

	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	

	public boolean iscrivi(Studente s, Corso c) {
		String sql ="INSERT INTO 'iscritticorsi'.'iscrizione' ('matricola', 'codins') VALUES (?,?);";
		try {
			Connection conn = ConnectDB.getConnection();           
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins() );
			
			int result = st.executeUpdate();
			conn.close();
			
			if(result ==1){
				return true;
			}else{
				return false;
			}

			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
	}
}
