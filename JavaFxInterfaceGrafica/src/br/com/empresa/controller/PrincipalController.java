package br.com.empresa.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import br.com.empresa.app.Principal;

public class PrincipalController{
	
	 @FXML
	    private Label lbMensagem;

	    @FXML
	    private Button btCadCliente;

	    @FXML
	    private Button btCadFornecedor;

	    @FXML
	    private Label desenvolvidoPor;

	    @FXML
	    void cadastrarFornecedor(ActionEvent event){
	    	Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/br/com/empresa/view/fornecedor.fxml"));
				Principal.SCENE.setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void cadastrarCliente(ActionEvent event){
	    	Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/br/com/empresa/view/cliente.fxml"));
				Principal.SCENE.setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void cadastrarProduto(ActionEvent event){
	    	Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/br/com/empresa/view/produto.fxml"));
				Principal.SCENE.setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
}
