package br.com.empresa.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabelaProduto {
	private Produto produto;
	private IntegerProperty codigo;
	private StringProperty nome;
	private DoubleProperty preco;
	
	public TabelaProduto(Produto produto) {
		this.produto = produto;
		codigo = new SimpleIntegerProperty();
		codigo.set(produto.getCodigo());
		
		nome = new SimpleStringProperty();
		nome.set(produto.getNome());
		
		preco = new SimpleDoubleProperty();
		preco.set(produto.getPreco());
	}
	
	public Produto getProduto() {
		return produto;
	}
	public IntegerProperty codigoProperty() {
		return codigo;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public DoubleProperty precoProperty() {
		return preco;
	}
}
