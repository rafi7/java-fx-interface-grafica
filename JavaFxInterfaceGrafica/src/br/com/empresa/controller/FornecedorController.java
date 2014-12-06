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
import br.com.empresa.dao.FornecedorDAO;
import br.com.empresa.modelo.Fornecedor;
import br.com.empresa.modelo.TabelaFornecedor;

public class FornecedorController implements Initializable{
	@FXML
	private TableView<TabelaFornecedor> tabelaFornecedor;
	@FXML
	private TableColumn clNomeFantasia;
	@FXML
	private TableColumn clCnpj;
	@FXML
	private TableColumn clTelefone;
	@FXML
	private TableColumn clCodigo;
	
	@FXML
	private TextField txCodigo;
	@FXML
	private TextField txNomeFantasia;
	@FXML
	private TextField txInscricaoEstadual;
	@FXML
	private TextField txCNPJ;
	@FXML
	private TextField txRazaoSocial;
	@FXML
	private TextField txTelefone;
    @FXML
    private TextField txPesquisa;
	
	@FXML
	private Button btExcluir;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btLimpar;
	@FXML
	private Button btEditar;
    @FXML
    private Button btVoltar;
	
    @FXML
    private Label lbMensagem;

	@FXML
	void limpar(ActionEvent event) {
		txCodigo.setText("");
		txNomeFantasia.setText("");
		txRazaoSocial.setText("");
		txInscricaoEstadual.setText("");
		txCNPJ.setText("");
		txTelefone.setText("");
	}

	@FXML
	void editar(ActionEvent event) {
		// Busca o valor da coluna "codigo" para ter o código do Fornecedor
		IntegerProperty cdFornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem().codigoProperty();

		// Instancia o DAO
		FornecedorDAO dao = new FornecedorDAO();

		Fornecedor fornecedorSalvo = dao.getById(cdFornecedor.intValue());
		atualizaCampos(fornecedorSalvo);		
	}
	
	public void atualizaCampos(Fornecedor f){
		txCodigo.setText(String.valueOf(f.getCodigo()));
		txNomeFantasia.setText(f.getNomeFantasia());
		txRazaoSocial.setText(f.getRazaSocial());
		txInscricaoEstadual.setText(f.getInscricaoEstadual());
		txCNPJ.setText(f.getCnpj());
		txTelefone.setText(f.getTelefone());
	}

	@FXML
	void excluir(ActionEvent event) {
		// busca a linha selecionada
		int linhaSelecionada = tabelaFornecedor.getSelectionModel().getSelectedIndex();
		// Busca o valor da coluna "codigo" para ter o código do Fornecedor
		IntegerProperty cdFornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem().codigoProperty();
		// Instancia o DAO
		FornecedorDAO dao = new FornecedorDAO();
		// Excliu o Fornecedor do banco
		dao.excluir(cdFornecedor.intValue());
		// Exclui a referência da tabela
		tabelaFornecedor.getItems().remove(linhaSelecionada);
	}

	@FXML
	void salvar(ActionEvent event) {
		Fornecedor f = new Fornecedor();
		f.setNomeFantasia(txNomeFantasia.getText());
		f.setRazaSocial(txRazaoSocial.getText());
		f.setInscricaoEstadual(txInscricaoEstadual.getText());
		f.setCnpj(txCNPJ.getText());
		f.setTelefone(txTelefone.getText());

		FornecedorDAO dao = new FornecedorDAO();
		if(txCodigo.getText().trim().equals("")){
			dao.inserir(f);
		}else{
			f.setCodigo(Integer.parseInt(txCodigo.getText()));
			dao.editar(f);
		}
		
		lbMensagem.setText("Salvo Com Sucesso!!!");
		listar();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void carregarTabela(ObservableList<TabelaFornecedor> dados){
		clCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		clNomeFantasia.setCellValueFactory(new PropertyValueFactory("nomeFantasia"));
		clCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
		clTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
		
		// Adiciona os dados na tabela
		tabelaFornecedor.setItems(dados);
	}
	
    @FXML
    void pesquisarPorNome(ActionEvent event) {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listarNome(txPesquisa.getText());
		ObservableList<TabelaFornecedor> dados = FXCollections.observableArrayList();
		
		// Adiciona os fornecedores no objeto do JavaFX "ObservableList" 
		for (Fornecedor f: fornecedores) {
			dados.add(new TabelaFornecedor(f));
		}
		carregarTabela(dados);
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
    	FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listar();
		ObservableList<TabelaFornecedor> dados = FXCollections.observableArrayList();
		
		// Adiciona os fornecedores no objeto do JavaFX "ObservableList" 
		for (Fornecedor f: fornecedores) {
			dados.add(new TabelaFornecedor(f));
		}
		carregarTabela(dados);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		listar();
	}
}
