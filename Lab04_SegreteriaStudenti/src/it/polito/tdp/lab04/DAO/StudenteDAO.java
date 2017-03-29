package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class StudenteDAO {

	public Studente cercaStudente(int matricola){
		
		String sql = "SELECT cognome, nome, CDS "+
					"FROM studente "+
					"WHERE matricola =?";
		
		Studente result= null;
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
				result = s;
			}else{
				result = null;
			}
			conn.close();
			return result;
			

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
				CorsoDAO corsoD = new CorsoDAO();
				corsoD.getCorso(c);
				s.addCorso(c);
			}
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
}
