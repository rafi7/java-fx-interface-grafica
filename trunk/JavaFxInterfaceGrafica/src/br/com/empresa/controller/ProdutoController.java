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
import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.modelo.Produto;
import br.com.empresa.modelo.TabelaProduto;

public class ProdutoController implements Initializable{
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
    private TextField txNome;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btLimpar;

    @FXML
    private Label lbMensagem;

    @FXML
    private TableColumn clPreco;

    @FXML
    private TableColumn clCodigo;

    @FXML
    private Button btEditar;

    @FXML
    private TextField txPreco;

    @FXML
    private TableView<TabelaProduto> tabelaProduto;

    @FXML
	void limpar(ActionEvent event) {
		txCodigo.setText("");
		txNome.setText("");
		txPreco.setText("");
	}

	@FXML
	void editar(ActionEvent event) {
		// Busca o valor da coluna "codigo" para ter o código do Produto
		IntegerProperty cdProduto = tabelaProduto.getSelectionModel().getSelectedItem().codigoProperty();

		// Instancia o DAO
		ProdutoDAO dao = new ProdutoDAO();

		Produto produtoSalvo = dao.getById(cdProduto.intValue());
		atualizaCampos(produtoSalvo);		
	}
	
	public void atualizaCampos(Produto p){
		txCodigo.setText(String.valueOf(p.getCodigo()));
		txNome.setText(p.getNome());
		txPreco.setText(String.valueOf(p.getPreco()));
	}

	@FXML
	void excluir(ActionEvent event) {
		// busca a linha selecionada
		int linhaSelecionada = tabelaProduto.getSelectionModel().getSelectedIndex();
		// Busca o valor da coluna "codigo" para ter o código do Produto
		IntegerProperty cdProduto = tabelaProduto.getSelectionModel().getSelectedItem().codigoProperty();
		// Instancia o DAO
		ProdutoDAO dao = new ProdutoDAO();
		// Excliu o Produto do banco
		dao.excluir(cdProduto.intValue());
		// Exclui a referência da tabela
		tabelaProduto.getItems().remove(linhaSelecionada);
	}

	@FXML
	void salvar(ActionEvent event) {
		Produto p = new Produto();
		p.setNome(txNome.getText());
		p.setPreco(Double.parseDouble(txPreco.getText()));

		ProdutoDAO dao = new ProdutoDAO();
		if(txCodigo.getText().trim().equals("")){
			dao.inserir(p);
		}else{
			p.setCodigo(Integer.parseInt(txCodigo.getText()));
			dao.editar(p);
		}
		
		lbMensagem.setText("Salvo Com Sucesso!!!");
		listar();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void carregarTabela(ObservableList<TabelaProduto> dados){
		clCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
		clNome.setCellValueFactory(new PropertyValueFactory("nome"));
		clPreco.setCellValueFactory(new PropertyValueFactory("preco"));
		
		// Adiciona os dados na tabela
		tabelaProduto.setItems(dados);
	}
	
    @FXML
    void pesquisarPorNome(ActionEvent event) {
    	ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produto = dao.listarNome(txPesquisa.getText());
		ObservableList<TabelaProduto> dados = FXCollections.observableArrayList();
		
		// Adiciona os Produtos no objeto do JavaFX "ObservableList" 
		for (Produto p: produto) {
			dados.add(new TabelaProduto(p));
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
    	ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		ObservableList<TabelaProduto> dados = FXCollections.observableArrayList();
		
		// Adiciona os Produtos no objeto do JavaFX "ObservableList" 
		for (Produto p: produtos) {
			dados.add(new TabelaProduto(p));
		}
		carregarTabela(dados);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		listar();
	}
}
