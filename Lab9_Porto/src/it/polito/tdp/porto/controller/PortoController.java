package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Autore;
import it.polito.tdp.porto.model.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Registro model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Autore> cbAutori;

    @FXML
    private ComboBox<Autore> cbAutori1;

    @FXML
    private Button btnCoautori;

    @FXML
    private Button btnCluster;

    @FXML
    private Button btnArticoli;

    void setModel(Registro model){
    	this.model=model;
    	model.caricaArticoli();
    	model.GeneraGrafo();
    	cbAutori.getItems().addAll(model.getAutori());
    	cbAutori1.getItems().addAll(model.getAutori());
    }
    
    @FXML
    void diTrovaCluster(ActionEvent event) {

    	Autore a = cbAutori.getValue();
    	Autore a2= cbAutori1.getValue();
    	
    	/*if(a!=null || a2!=null){
    		txtResult.setText("non devono esser selezionati autori per cercare i cluster");
    	}*/
    	//else{
    		String res = model.trovaCluster();
    		txtResult.setText(res);
    	//}
    }

    @FXML
    void doTrovaArticoli(ActionEvent event) {

    	Autore a = cbAutori.getValue();
    	Autore a2= cbAutori1.getValue();
    	if(a!=null && a2!=null){
    		String res = model.articoliCheCollegano(a, a2);
    		txtResult.setText(res);
    	}
    	else
    		txtResult.setText("selezionare due autori per poter veder percorso");
    	
    }

    @FXML
    void doTrovaCoautori(ActionEvent event) {
    	
    	Autore a = cbAutori.getValue();
    	if(a!=null){
    	String res = model.coautori(a);
    	txtResult.setText(res);
    	}
    	else
    		txtResult.setText("nessun autore selezionato in comboBox di sinistra");

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert cbAutori != null : "fx:id=\"cbAutori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert cbAutori1 != null : "fx:id=\"cbAutori1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticoli != null : "fx:id=\"btnArticoli\" was not injected: check your FXML file 'Porto.fxml'.";

    }
}