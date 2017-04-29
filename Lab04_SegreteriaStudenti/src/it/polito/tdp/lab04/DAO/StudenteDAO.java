package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class StudenteDAO {

	public Studente cercaStudente(int matricola){
		
		String sql = "SELECT cognome, nome, CDS "+
					"FROM studente "+
					"WHERE matricola =?";
		
		Studente studente= null;
		try {
			Connection conn = ConnectDB.getConnection();           
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, matricola);

			ResultSet rs = st.executeQuery();

			if(rs.next()){							
				Studente s = new Studente(	
						matricola,
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS")
						);
				studente = s;
			}else{
				studente = null;
			}
			conn.close();
			return studente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	public void cercaCorsi(Studente s) {
		String sql = "SELECT codins "+
				"FROM iscrizione "+
				"WHERE matricola =?";
		try {
			Connection conn = ConnectDB.getConnection();           
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, s.getMatricola());

			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Corso c= new Corso(rs.getString("codins"), 0,null,0);
				s.addCorso(c);
			}
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
	
	//OPPURE opzione 2 (bis)
	
	public void cercaCorsiBis(Studente s) {
		String sql = "SELECT * "+
				"FROM iscrizione, corso "+
				"WHERE iscrizoine.codins = corso.codins AND matricola =?";
		
		List <Corso> corsi = new LinkedList <Corso>();
		try {
			Connection conn = ConnectDB.getConnection();           
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, s.getMatricola());

			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}
//			studente.setCorsiBis(corsi);
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
}
