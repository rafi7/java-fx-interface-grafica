package br.com.empresa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import br.com.empresa.app.Principal;
import br.com.empresa.dao.ClienteDAO;
import br.com.empresa.modelo.Cliente;
import br.com.empresa.modelo.TabelaCliente;

public class ClienteController implements Initializable{
	
    @FXML
    private TextField txNome;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txTelefone;
    
	@FXML
    private TextField txPesquisa;

    @FXML
    private TableColumn clNome;

    @FXML
    private Button btExcluir;


    @FXML
    private TextField txCodigo;

    @FXML
    private Button btSalvar;


    @FXML
    private Label desenvolvidoPor;

    @FXML
    private Button btLimpar;

    @FXML
    private Label lbMensagem;

    @FXML
    private TableColumn clCpf;

    @FXML
    private TableColumn clCodigo;

    @FXML
    private Button btEditar;

    @FXML
    private TableColumn clTelefone;

    @FXML
    private TableView<TabelaCliente> tabelaCliente;
    
    @FXML
    private Button btVoltar;

    @FXML
    void limpar(ActionEvent event) {
		txCodigo.setText("");
		txNome.setText("");
		txCpf.setText("");
		txTelefone.setText("");
    }

    @FXML
    void salvar(ActionEvent event) {
		Cliente c = new Cliente();
		c.setNome(txNome.getText());
		c.setCpf(txCpf.getText());
		c.setTelefone(txTelefone.getText());

		ClienteDAO dao = new ClienteDAO();
		if(txCodigo.getText().trim().equals("")){
			dao.inserir(c);
		}else{
			c.setCodigo(Integer.parseInt(txCodigo.getText()));
			dao.editar(c);
		}
		
		lbMensagem.setText("Salvo Com Sucesso!!!");
		listar();
    }

    @FXML
    void editar(ActionEvent event) {
		IntegerProperty cdCliente = tabelaCliente.getSelectionModel().getSelectedItem().codigoProperty();

		ClienteDAO dao = new ClienteDAO();

		Cliente clienteSalvo = dao.getById(cdCliente.intValue());
		atualizaCampos(clienteSalvo);	
    }

    @FXML
    void excluir(ActionEvent event) {
		int linhaSelecionada = tabelaCliente.getSelectionModel().getSelectedIndex();
		IntegerProperty cdCliente = tabelaCliente.getSelectionModel().getSelectedItem().codigoProperty();
		ClienteDAO dao = new ClienteDAO();
		dao.excluir(cdCliente.intValue());
		tabelaCliente.getItems().remove(linhaSelecionada);
    }

    @FXML
    void pesquisarPorNome(ActionEvent event) {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listarNome(txPesquisa.getText());
		ObservableList<TabelaCliente> dados = FXCollections.observableArrayList();
		for (Cliente c: clientes) {
			dados.add(new TabelaCliente(c));
		}
		carregarTabela(dados);
    }
    
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void carregarTabela(ObservableList<TabelaCliente> dados){
		
		clCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		clNome.setCellValueFactory(new PropertyValueFactory("nome"));
		clCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
		clTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
		
		// Adiciona os dados na tabela
		tabelaCliente.setItems(dados);
	}
	
    @FXML
    void voltar(ActionEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/br/com/empresa/view/principal.fxml"));
			Principal.SCENE.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void listar(){
    	ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listar();
		ObservableList<TabelaCliente> dados = FXCollections.observableArrayList();
		 
		for (Cliente c: clientes) {
			dados.add(new TabelaCliente(c));
		}
		carregarTabela(dados);
    }
    
	public void atualizaCampos(Cliente c){
		txCodigo.setText(String.valueOf(c.getCodigo()));
		txNome.setText(c.getNome());
		txCpf.setText(c.getCpf());
		txTelefone.setText(c.getTelefone());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		listar();
	}
}
