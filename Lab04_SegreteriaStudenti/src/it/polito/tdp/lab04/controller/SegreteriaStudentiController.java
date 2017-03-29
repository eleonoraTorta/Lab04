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
		
		LinkedList <Corso> corsi = new LinkedList <Corso>(model.getCorsi());
		Corso c = new Corso(null,0,null,0);
		corsi.addFirst(c);
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
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		String m = txtMatricola.getText();
		int matricola = Integer.parseInt(m);
		Studente s = model.cercaStudentePerMatricola(matricola);
		txtCognome.setText(s.getCognome());
		txtNome.setText(s.getNome());
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		Corso corso = comboCorso.getValue();
		Corso corsoNull = new Corso(null,0,null,0);
		if( corso.equals(corsoNull)){
			txtResult.appendText("ERRORE, nessun corso selezionato!");
		}
		else{
		LinkedList <Studente> lista = new LinkedList <Studente>(model.cercaStudentiIscritti(corso));
		String elenco="";
		for(Studente s : lista){
			elenco += s.toString() + "\n";
		}
		txtResult.appendText(elenco);
		}

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		String m = txtMatricola.getText();
		int matricola = Integer.parseInt(m);
		Studente s = model.cercaStudentePerMatricola(matricola);
		if( model.esiste(s)==false){
			txtResult.appendText("ERRORE, studente non esistente!");
		}else{
			LinkedList <Corso> corsi = new LinkedList <Corso>(model.cercaCorsiDatoStudente(s));
			String elenco="";
			for(Corso c : corsi){
				elenco += c.toString() + "\n";
			}
			txtResult.appendText(elenco);
			}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		Corso corso = comboCorso.getValue();
		String m = txtMatricola.getText();
		int matricola = Integer.parseInt(m);
		Studente s = model.cercaStudentePerMatricola(matricola);
		if(model.isIscritto(s,corso)==true){
			txtResult.setText("Studente gia` iscritto a questo corso");
		}else{
			if(model.iscrivi(s,corso)==true){
			txtResult.setText("Studente iscritto al corso!");
			}
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
