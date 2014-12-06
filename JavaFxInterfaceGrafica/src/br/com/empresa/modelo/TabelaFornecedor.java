package br.com.empresa.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabelaFornecedor {
	private Fornecedor fornecedor;
	private IntegerProperty codigo;
	private StringProperty nomeFantasia;
	private StringProperty telefone;
	private StringProperty cnpj;
	public TabelaFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		codigo = new SimpleIntegerProperty();
		codigo.set(fornecedor.getCodigo());
		
		nomeFantasia = new SimpleStringProperty();
		nomeFantasia.set(fornecedor.getNomeFantasia());
		
		telefone = new SimpleStringProperty();
		telefone.set(fornecedor.getTelefone());
		
		cnpj = new SimpleStringProperty();
		cnpj.set(fornecedor.getCnpj());
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public IntegerProperty codigoProperty() {
		return codigo;
	}
	public StringProperty nomeFantasiaProperty() {
		return nomeFantasia;
	}
	public StringProperty telefoneProperty() {
		return telefone;
	}
	public StringProperty cnpjProperty() {
		return cnpj;
	}
}
