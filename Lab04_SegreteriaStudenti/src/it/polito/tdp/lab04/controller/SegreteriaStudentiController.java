package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private CheckBox btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model = model;
		
		// ottengo tutti i corsi dal model
		corsi = model.getCorsi();
		
		
		// aggiungo i corsi alla ComboBox
		Collections.sort((corsi));
		
		Corso c = new Corso(null,0,null,0);
		((LinkedList<Corso>) corsi).addFirst(c);
		
		comboCorso.getItems().addAll(corsi);
		
		if(comboCorso.getItems().size() >0){
	        comboCorso.setValue(comboCorso.getItems().get(0));  
	        }
	
	}

	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
		txtMatricola.clear();
		comboCorso.getSelectionModel().clearSelection();  //
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		
		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
		
		try{
			String m = txtMatricola.getText();
			int matricola = Integer.parseInt(m);
			Studente s = model.cercaStudentePerMatricola(matricola);
		
			if( s == null){
				txtResult.appendText("Nessun risultato: matricola inesistente");
				return;
			}
			txtCognome.setText(s.getCognome());
			txtNome.setText(s.getNome());
		
		} catch (NumberFormatException e){
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e){
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		
		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
		
		try{
			Corso corso = comboCorso.getValue();
			Corso corsoNull = new Corso(null,0,null,0);
			if( corso.equals(corsoNull)){
				txtResult.setText("ERRORE, nessun corso selezionato!");
				return;
			}
		
			List <Studente> lista = model.cercaStudentiIscritti(corso);
			
			//Stampo ordinatamente gli studenti
			for(Studente s : lista){
				txtResult.appendText(String.format("%-10s ", s.getMatricola()) + 
									String.format("%-20s ", s.getCognome()) +
									String.format("%-20s ", s.getNome()) +
									String.format("%-10s ", s.getCds()) +
									"\n");
			}
		
		} catch (RuntimeException e){
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
		txtResult.clear();
		
		try{
			String m = txtMatricola.getText();
			int matricola = Integer.parseInt(m);
			Studente s = model.cercaStudentePerMatricola(matricola);
		
			if( s == null){
				txtResult.appendText("Nessun risulato, matricola non esistente");
				return;
			}
			
			List <Corso> corsi = model.cercaCorsiDatoStudente(s);
			
			//Stampo ordinatamente i corsi
			for(Corso c : corsi){
				txtResult.appendText(String.format("%-12s ", c.getCodins()) + 
									String.format("%-4s ", c.getCrediti()) +
									String.format("%-45s ", c.getNome()) +
									String.format("%-4s ", c.getPd()) +
									"\n");
			}
			
		} catch (NumberFormatException e){
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e){
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		
		txtResult.clear();
		
		try{
			Corso corso = comboCorso.getValue();
			Corso corsoNull = new Corso(null,0,null,0);
			if( corso.equals(corsoNull)){
				txtResult.setText("ERRORE, nessun corso selezionato!");
				return;
			}
			
			String m = txtMatricola.getText();
			if( txtMatricola.getText().isEmpty()){
				txtResult.setText("ERRORE, nessuna matricola inserita!");
			}
			int matricola = Integer.parseInt(m);
			
			Studente s = model.cercaStudentePerMatricola(matricola);
			if(s ==null){
				txtResult.setText("Nessun risultato: matricola inesistente");
				return;
			}
			
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
			
			//controllo se lo studente e` gia iscritto al corso
			
			if(model.isIscritto(s,corso)==true){
				txtResult.setText("Studente gia` iscritto a questo corso");
				return;
			}
			
			//Iscrivo al corso
			
			if(!model.iscrivi(s,corso)){
					txtResult.setText("Errore durante iscrizione al corso");
					return;
			}else{
				txtResult.setText("Studente iscritto al corso!");
			}
				
			}catch (NumberFormatException e){
				txtResult.setText("Inserire una matricola nel formato corretto.");
			} catch (RuntimeException e){
				txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
			}

	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		
		
	}

}
