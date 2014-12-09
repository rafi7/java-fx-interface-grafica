package br.com.empresa.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import br.com.empresa.app.Principal;

public class PrincipalController {

	@FXML
	private Button btCadProduto;

	@FXML
	private MenuItem miClientes;

	@FXML
	private Button btCadCliente;

	@FXML
	private MenuItem miSobre;

	@FXML
	private Button btCadFornecedor;

	@FXML
	private Menu mnAjuda;

	@FXML
	private MenuItem miProdutos;

	@FXML
	private Label desenvolvidoPor;

	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu mnCadastros;

	@FXML
	private Label lbMensagem;

	@FXML
	private MenuItem miSair;

	@FXML
	private MenuItem miFornecedores;

	@FXML
	private Menu mnArquivo;

	@FXML
	void cadastrarFornecedor(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/br/com/empresa/view/fornecedor.fxml"));
			Principal.SCENE.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void cadastrarCliente(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/br/com/empresa/view/cliente.fxml"));
			Principal.SCENE.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void cadastrarProduto(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/br/com/empresa/view/produto.fxml"));
			Principal.SCENE.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
